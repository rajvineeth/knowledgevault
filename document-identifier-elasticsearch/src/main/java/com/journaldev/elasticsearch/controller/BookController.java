package com.journaldev.elasticsearch.controller;

import com.journaldev.elasticsearch.bean.Book;
import com.journaldev.elasticsearch.dao.BookDao;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.en.PorterStemFilter;
import org.apache.lucene.analysis.en.PorterStemFilterFactory;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeRequest;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeResponse;
import org.elasticsearch.common.recycler.Recycler;
import org.elasticsearch.index.mapper.ParseContext;
import org.springframework.web.bind.annotation.*;
import org.tartarus.snowball.ext.PorterStemmer;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.simple.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/books")
public class BookController {

    private BookDao bookDao;

    public BookController(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @PostMapping
    public Book insertBook(@RequestBody Book book) throws Exception{
        return bookDao.insertBook(book);
    }

//    @GetMapping("/{id}")
//    public Map<String, Object> getBookById(@PathVariable String id){
//        return bookDao.getBookById(id);
//    }

    @PutMapping("/{id}")
    public Map<String, Object> updateBookById(@RequestBody Book book, @PathVariable String id){
        return bookDao.updateBookById(id, book);
    }
    @GetMapping("/showall")
    public Collection<Map<String, Object>> showAll(){
        return bookDao.getAll();
    }
    @GetMapping("/search")
    public Collection<Map> searchAll() throws IOException {
        String doc1=new String(Files.readAllBytes(Paths.get("document-identifier-elasticsearch/src/main/Docs/document1.txt")));
        String[] stopwords = {"1","2","3","4","5","6","7","8","9","0","a", "as", "able", "about", "above", "according", "accordingly", "across", "actually", "after", "afterwards", "again", "against", "aint", "all", "allow", "allows", "almost", "alone", "along", "already", "also", "although", "always", "am", "among", "amongst", "an", "and", "another", "any", "anybody", "anyhow", "anyone", "anything", "anyway", "anyways", "anywhere", "apart", "appear", "appreciate", "appropriate", "are", "arent", "around", "as", "aside", "ask", "asking", "associated", "at", "available", "away", "awfully", "be", "became", "because", "become", "becomes", "becoming", "been", "before", "beforehand", "behind", "being", "believe", "below", "beside", "besides", "best", "better", "between", "beyond", "both", "brief", "but", "by", "cmon", "cs", "came", "can", "cant", "cannot", "cant", "cause", "causes", "certain", "certainly", "changes", "clearly", "co", "com", "come", "comes", "concerning", "consequently", "consider", "considering", "contain", "containing", "contains", "corresponding", "could", "couldnt", "course", "currently", "definitely", "described", "despite", "did", "didnt", "different", "do", "does", "doesnt", "doing", "dont", "done", "down", "downwards", "during", "each", "edu", "eg", "eight", "either", "else", "elsewhere", "enough", "entirely", "especially", "et", "etc", "even", "ever", "every", "everybody", "everyone", "everything", "everywhere", "ex", "exactly", "example", "except", "far", "few", "ff", "fifth", "first", "five", "followed", "following", "follows", "for", "former", "formerly", "forth", "four", "from", "further", "furthermore", "get", "gets", "getting", "given", "gives", "go", "goes", "going", "gone", "got", "gotten", "greetings", "had", "hadnt", "happens", "hardly", "has", "hasnt", "have", "havent", "having", "he", "hes", "hello", "help", "hence", "her", "here", "heres", "hereafter", "hereby", "herein", "hereupon", "hers", "herself", "hi", "him", "himself", "his", "hither", "hopefully", "how", "howbeit", "however", "i", "id", "ill", "im", "ive", "ie", "if", "ignored", "immediate", "in", "inasmuch", "inc", "indeed", "indicate", "indicated", "indicates", "inner", "insofar", "instead", "into", "inward", "is", "isnt", "it", "itd", "itll", "its", "its", "itself", "just", "keep", "keeps", "kept", "know", "knows", "known", "last", "lately", "later", "latter", "latterly", "least", "less", "lest", "let", "lets", "like", "liked", "likely", "little", "look", "looking", "looks", "ltd", "mainly", "many", "may", "maybe", "me", "mean", "meanwhile", "merely", "might", "more", "moreover", "most", "mostly", "much", "must", "my", "myself", "name", "namely", "nd", "near", "nearly", "necessary", "need", "needs", "neither", "never", "nevertheless", "new", "next", "nine", "no", "nobody", "non", "none", "noone", "nor", "normally", "not", "nothing", "novel", "now", "nowhere", "obviously", "of", "off", "often", "oh", "ok", "okay", "old", "on", "once", "one", "ones", "only", "onto", "or", "other", "others", "otherwise", "ought", "our", "ours", "ourselves", "out", "outside", "over", "overall", "own", "particular", "particularly", "per", "perhaps", "placed", "please", "plus", "possible", "presumably", "probably", "provides", "que", "quite", "qv", "rather", "rd", "re", "really", "reasonably", "regarding", "regardless", "regards", "relatively", "respectively", "right", "said", "same", "saw", "say", "saying", "says", "second", "secondly", "see", "seeing", "seem", "seemed", "seeming", "seems", "seen", "self", "selves", "sensible", "sent", "serious", "seriously", "seven", "several", "shall", "she", "should", "shouldnt", "since", "six", "so", "some", "somebody", "somehow", "someone", "something", "sometime", "sometimes", "somewhat", "somewhere", "soon", "sorry", "specified", "specify", "specifying", "still", "sub", "such", "sup", "sure", "ts", "take", "taken", "tell", "tends", "th", "than", "thank", "thanks", "thanx", "that", "thats", "thats", "the", "their", "theirs", "them", "themselves", "then", "thence", "there", "theres", "thereafter", "thereby", "therefore", "therein", "theres", "thereupon", "these", "they", "theyd", "theyll", "theyre", "theyve", "think", "third", "this", "thorough", "thoroughly", "those", "though", "three", "through", "throughout", "thru", "thus", "to", "together", "too", "took", "toward", "towards", "tried", "tries", "truly", "try", "trying", "twice", "two", "un", "under", "unfortunately", "unless", "unlikely", "until", "unto", "up", "upon", "us", "use", "used", "useful", "uses", "using", "usually", "value", "various", "very", "via", "viz", "vs", "want", "wants", "was", "wasnt", "way", "we", "wed", "well", "were", "weve", "welcome", "well", "went", "were", "werent", "what", "whats", "whatever", "when", "whence", "whenever", "where", "wheres", "whereafter", "whereas", "whereby", "wherein", "whereupon", "wherever", "whether", "which", "while", "whither", "who", "whos", "whoever", "whole", "whom", "whose", "why", "will", "willing", "wish", "with", "within", "without", "wont", "wonder", "would", "would", "wouldnt", "yes", "yet", "you", "youd", "youll", "youre", "youve", "your", "yours", "yourself", "yourselves", "zero"};
        Set<String> stopSet = new HashSet<>();
        stopSet.addAll(Arrays.asList(stopwords));
        String content;
        String id;
        String[] words;
        List<String> arrayList = new ArrayList<>();
        Collection<Map<String, Object>> docs=bookDao.getAll();
        Collection<Map> result=new LinkedList<>();
        for(Map<String,Object> doc:docs) {
            arrayList.clear();
            content = (String) doc.get("content");
            id = (String) doc.get("id");
            Document document=new Document(Arrays.toString(content.toLowerCase().split("\\W+")));
            for (Sentence sent : document.sentences()) {
                System.out.println(sent);
                for(int i=0;i<sent.length();i++){
                    arrayList.add(sent.lemma(i));
                }
            }
            //String[] tokens=content.toLowerCase().split("\\W+");
            //Collections.addAll(arrayList, tokens);
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
            Map<String, Float> map = new HashMap<>();
            for (String word : arrayList) {
                map.put(word, bookDao.getScore(word,id)); 
            }
            Map objectMap=new HashMap<>();
            objectMap.put("id",doc.get("id"));
           // objectMap.put("keywords",sortByValue(map));

            List<String> diseases = bookDao.getDiseases();
            List<String> symptoms = bookDao.getSymptoms();
            Map<String,Float> map1=sortByValue(map);
            //Iterator it = map1.entrySet().iterator();
            Iterator<Map.Entry<String, Float>> it = map1.entrySet().iterator();
            int removed=0;
            Map<String,Float> relevantMedicalTerms=new HashMap<>();
            while (it.hasNext())
            {
                Map.Entry<String,Float> entry= it.next();
                removed=0;
                for(String disease:diseases){
                    if(Arrays.asList(disease.split("\\b+")).contains( entry.getKey())){
                        System.out.println("Disease name:"+entry.getKey()+" : "+disease);
                        relevantMedicalTerms.put(entry.getKey(),entry.getValue());
                    }
//                    else{
//                        removed=1;
//                        it.remove();
//                        continue;
//                    }

                }

                for(String symptom:symptoms){
                    if(Arrays.asList(symptom.split("\\b+")).contains(entry.getKey())){
                        System.out.println("Symptom name:"+entry.getKey()+" : "+symptom);
                        relevantMedicalTerms.put(entry.getKey(),entry.getValue());
                    }
//                    else if(removed==0){
//                        it.remove();
//                        continue;
//                    }
                }
            }
            objectMap.put("keywords",relevantMedicalTerms);
            result.add(objectMap);

        }
        return result;
    }

    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> unsortMap) {

        List<Map.Entry<K, V>> list =
                new LinkedList<Map.Entry<K, V>>(unsortMap.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        Map<K, V> result = new LinkedHashMap<K, V>();
        int i=0;
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
            i++;
            if(i==20)
                break;
        }
        return result;
    }
    @GetMapping("/{content}")
    public Collection<Map<String, Object>> searchContent(@PathVariable String content){return bookDao.search(content);}

    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable String id){
         bookDao.deleteBookById(id);
    }
}
