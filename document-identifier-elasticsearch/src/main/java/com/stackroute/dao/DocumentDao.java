package com.stackroute.dao;

import au.com.bytecode.opencsv.CSVReader;
import com.stackroute.domain.ExtractedFileData;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.stanford.nlp.simple.Sentence;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.ScoreSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

@Repository
public class DocumentDao {

    private String index = "bookdata";
    private String type = "books";
    private static final String CONTENT= "content";
    private RestHighLevelClient restHighLevelClient;

    private ObjectMapper objectMapper;
    public DocumentDao(ObjectMapper objectMapper, RestHighLevelClient restHighLevelClient){
        this.objectMapper = objectMapper;
        this.restHighLevelClient = restHighLevelClient;
    }

    public ExtractedFileData insertDoc(ExtractedFileData extractedFileData){
        Map<String, Object> dataMap = objectMapper.convertValue(extractedFileData, Map.class);
        IndexRequest indexRequest = new IndexRequest(index, type, extractedFileData.getId())
                .source(dataMap);
        try {
           restHighLevelClient.index(indexRequest);
        } catch(ElasticsearchException e) {
            e.getDetailedMessage();
        } catch (java.io.IOException ex){
            ex.getLocalizedMessage();
        }
        return extractedFileData;
    }
    public float getScore(String content,String id){
        SearchRequest searchRequest=new SearchRequest(index);
        searchRequest.types(type);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        BoolQueryBuilder boolQuery = new BoolQueryBuilder();
        HashMap<String,String> fields=new HashMap();
        fields.put(CONTENT,content);
        fields.put("id",id);
        for (Map.Entry<String, String> entry : fields.entrySet()){
            boolQuery.must(QueryBuilders.matchQuery(entry.getKey(), entry.getValue()));
        }
        sourceBuilder.query(boolQuery);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        sourceBuilder.sort(new ScoreSortBuilder().order(SortOrder.DESC));
        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse=null;
        float score=0;
        try{
            searchResponse=restHighLevelClient.search(searchRequest);
        }
        catch (java.io.IOException e){
            e.getLocalizedMessage();
        }
        if(searchResponse!=null) {
            SearchHit[] hits = searchResponse.getHits().getHits();
            for (SearchHit hit : hits) {
                score = hit.getScore();
            }
        }
        return score;
    }

    List<String> arrayList = new ArrayList<>();
    public Collection<Map> getKeywords() throws IOException {
        String content;
        String id;
        Collection<Map<String, Object>> docs= getAll();
        Collection<Map> result=new LinkedList<>();
        for(Map<String,Object> doc:docs) {
            arrayList.clear();
            content = (String) doc.get(CONTENT);
            id = (String) doc.get("id");
            edu.stanford.nlp.simple.Document document=new edu.stanford.nlp.simple.Document(Arrays.toString(content.toLowerCase().split("\\W+")));
            arrayList=lemmatize(document);
            arrayList=removeStopWords(arrayList);
            Map<String, Float> map = new HashMap<>();
            for (String word : arrayList) {
                map.put(word, getScore(word,id));
            }
            Map objectMap=new HashMap<>();
            objectMap.put("id",doc.get("id"));
            Map<String,Float> map1=sortByValue(map);
            objectMap.put("keywords",checkInDict(map1));
            result.add(objectMap);
        }
        return result;
    }
    public List<String> lemmatize(edu.stanford.nlp.simple.Document document){
        for (Sentence sent : document.sentences()) {
            for(int i=0;i<sent.length();i++){
                arrayList.add(sent.lemma(i));
            }
        }
        return arrayList;
    }
    public List<String> removeStopWords(List<String> arrayList){
        String[] stopwords = {"as", "able", "about", "above", "according", "accordingly", "across", "actually", "after", "afterwards", "again", "against", "aint", "all", "allow", "allows", "almost", "alone", "along", "already", "also", "although", "always", "am", "among", "amongst", "an", "and", "another", "any", "anybody", "anyhow", "anyone", "anything", "anyway", "anyways", "anywhere", "apart", "appear", "appreciate", "appropriate", "are", "arent", "around", "as", "aside", "ask", "asking", "associated", "at", "available", "away", "awfully", "be", "became", "because", "become", "becomes", "becoming", "been", "before", "beforehand", "behind", "being", "believe", "below", "beside", "besides", "best", "better", "between", "beyond", "both", "brief", "but", "by", "cmon", "cs", "came", "can", "cant", "cannot", "cant", "cause", "causes", "certain", "certainly", "changes", "clearly", "co", "com", "come", "comes", "concerning", "consequently", "consider", "considering", "contain", "containing", "contains", "corresponding", "could", "couldnt", "course", "currently", "definitely", "described", "despite", "did", "didnt", "different", "do", "does", "doesnt", "doing", "dont", "done", "down", "downwards", "during", "each", "edu", "eg", "eight", "either", "else", "elsewhere", "enough", "entirely", "especially", "et", "etc", "even", "ever", "every", "everybody", "everyone", "everything", "everywhere", "ex", "exactly", "example", "except", "far", "few", "ff", "fifth", "first", "five", "followed", "following", "follows", "for", "former", "formerly", "forth", "four", "from", "further", "furthermore", "get", "gets", "getting", "given", "gives", "go", "goes", "going", "gone", "got", "gotten", "greetings", "had", "hadnt", "happens", "hardly", "has", "hasnt", "have", "havent", "having", "he", "hes", "hello", "help", "hence", "her", "here", "heres", "hereafter", "hereby", "herein", "hereupon", "hers", "herself", "hi", "him", "himself", "his", "hither", "hopefully", "how", "howbeit", "however", "i", "id", "ill", "im", "ive", "ie", "if", "ignored", "immediate", "in", "inasmuch", "inc", "indeed", "indicate", "indicated", "indicates", "inner", "insofar", "instead", "into", "inward", "is", "isnt", "it", "itd", "itll", "its", "its", "itself", "just", "keep", "keeps", "kept", "know", "knows", "known", "last", "lately", "later", "latter", "latterly", "least", "less", "lest", "let", "lets", "like", "liked", "likely", "little", "look", "looking", "looks", "ltd", "mainly", "many", "may", "maybe", "me", "mean", "meanwhile", "merely", "might", "more", "moreover", "most", "mostly", "much", "must", "my", "myself", "name", "namely", "nd", "near", "nearly", "necessary", "need", "needs", "neither", "never", "nevertheless", "new", "next", "nine", "no", "nobody", "non", "none", "noone", "nor", "normally", "not", "nothing", "novel", "now", "nowhere", "obviously", "of", "off", "often", "oh", "ok", "okay", "old", "on", "once", "one", "ones", "only", "onto", "or", "other", "others", "otherwise", "ought", "our", "ours", "ourselves", "out", "outside", "over", "overall", "own", "particular", "particularly", "per", "perhaps", "placed", "please", "plus", "possible", "presumably", "probably", "provides", "que", "quite", "qv", "rather", "rd", "re", "really", "reasonably", "regarding", "regardless", "regards", "relatively", "respectively", "right", "said", "same", "saw", "say", "saying", "says", "second", "secondly", "see", "seeing", "seem", "seemed", "seeming", "seems", "seen", "self", "selves", "sensible", "sent", "serious", "seriously", "seven", "several", "shall", "she", "should", "shouldnt", "since", "six", "so", "some", "somebody", "somehow", "someone", "something", "sometime", "sometimes", "somewhat", "somewhere", "soon", "sorry", "specified", "specify", "specifying", "still", "sub", "such", "sup", "sure", "ts", "take", "taken", "tell", "tends", "th", "than", "thank", "thanks", "thanx", "that", "thats", "thats", "the", "their", "theirs", "them", "themselves", "then", "thence", "there", "theres", "thereafter", "thereby", "therefore", "therein", "theres", "thereupon", "these", "they", "theyd", "theyll", "theyre", "theyve", "think", "third", "this", "thorough", "thoroughly", "those", "though", "three", "through", "throughout", "thru", "thus", "to", "together", "too", "took", "toward", "towards", "tried", "tries", "truly", "try", "trying", "twice", "two", "un", "under", "unfortunately", "unless", "unlikely", "until", "unto", "up", "upon", "us", "use", "used", "useful", "uses", "using", "usually", "value", "various", "very", "via", "viz", "vs", "want", "wants", "was", "wasnt", "way", "we", "wed", "well", "were", "weve", "welcome", "well", "went", "were", "werent", "what", "whats", "whatever", "when", "whence", "whenever", "where", "wheres", "whereafter", "whereas", "whereby", "wherein", "whereupon", "wherever", "whether", "which", "while", "whither", "who", "whos", "whoever", "whole", "whom", "whose", "why", "will", "willing", "wish", "with", "within", "without", "wont", "wonder", "would", "would", "wouldnt", "yes", "yet", "you", "youd", "youll", "youre", "youve", "your", "yours", "yourself", "yourselves", "zero"};
        Set<String> stopSet = new HashSet<>();
        stopSet.addAll(Arrays.asList(stopwords));
        Pattern constraint = Pattern.compile("[a-zA-Z0-9]{1}");
        Pattern constraint2 = Pattern.compile("[0-9]+");
        for (int i = 0; i < arrayList.size(); i++) {
            String word = arrayList.get(i);
            Matcher m=constraint.matcher(word);
            Matcher m2=constraint2.matcher(word);
            if (stopSet.contains(word)||m.matches()||m2.matches()) {
                arrayList.remove(word);
                i--;
            }
        }
        return arrayList;
    }
    public Map<String,Float> checkInDict(Map<String,Float> map1) throws IOException {
        Map<String,Float> relevantMedicalTerms=new HashMap<>();
        Iterator<Map.Entry<String, Float>> it = map1.entrySet().iterator();
        List<String> diseases = getDiseases();
        List<String> symptoms = getSymptoms();
        while (it.hasNext())
        {
            Map.Entry<String,Float> entry= it.next();
            for(String disease:diseases){
                if(Arrays.asList(disease.split("\\b+")).contains( entry.getKey())){
                    relevantMedicalTerms.put(entry.getKey(),entry.getValue());
                }
            }

            for(String symptom:symptoms){
                if(Arrays.asList(symptom.split("\\b+")).contains(entry.getKey())){
                    relevantMedicalTerms.put(entry.getKey(),entry.getValue());
                }
            }
        }
        return relevantMedicalTerms;
    }
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> unsortMap) {

        List<Map.Entry<K, V>> list =
                new LinkedList<>(unsortMap.entrySet());

        Collections.sort(list, (o1, o2) -> (o2.getValue()).compareTo(o1.getValue()));

        Map<K, V> result = new LinkedHashMap<>();
        int i=0;
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
            i++;
            if(i==20)
                break;
        }
        return result;
    }

    public Collection<Map<String, Object>> search(String content){
        SearchRequest searchRequest=new SearchRequest(index);
        searchRequest.types(type);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.matchQuery(CONTENT, content));
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        sourceBuilder.sort(new ScoreSortBuilder().order(SortOrder.DESC));
        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse=null;
        Collection<Map<String, Object>> contracts = new LinkedList<>();
        try{
            searchResponse=restHighLevelClient.search(searchRequest);
        }
        catch (java.io.IOException e){
            e.getLocalizedMessage();
        }
        if(searchResponse!=null) {
            SearchHit[] hits = searchResponse.getHits().getHits();
            for (SearchHit hit : hits) {
                contracts.add(hit.getSourceAsMap());
            }
        }
        return contracts;

    }
    public Collection<Map<String, Object>> getAll(){

        SearchRequest searchRequest=new SearchRequest(index);
        searchRequest.types(type);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.matchAllQuery());
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        sourceBuilder.sort(new ScoreSortBuilder().order(SortOrder.DESC));
        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse=null;
        Collection<Map<String, Object>> contracts = new LinkedList<>();
        try{
            searchResponse=restHighLevelClient.search(searchRequest);
        }
        catch (java.io.IOException e){
            e.getLocalizedMessage();
        }
        if(searchResponse!=null) {
            SearchHit[] hits = searchResponse.getHits().getHits();
            for (SearchHit hit : hits) {
                contracts.add(hit.getSourceAsMap());
            }
        }
        return contracts;
    }
    public List<String> getDiseases() throws IOException {
        CSVReader reader = new CSVReader(new FileReader("document-identifier-elasticsearch/src/main/Docs/DOID.csv"),',');
        List<String[]> allRows = reader.readAll();
        List<String> diseases=new ArrayList<>();
        for(String[] row : allRows){
            diseases.add(row[1]);
        }
        return diseases;
    }

    public List<String> getSymptoms() throws IOException {
        CSVReader reader = new CSVReader(new FileReader("document-identifier-elasticsearch/src/main/Docs/SYMP.csv"),',');
        List<String[]> allRows = reader.readAll();
        List<String> diseases=new ArrayList<>();
        for(String[] row : allRows){
            diseases.add(row[1]);
        }
        return diseases;
    }

}
