/**
 * @author: jshyperx
 */

package com.stackroute.knowledgevault.paragraphprocessor.algos;

import com.stackroute.knowledgevault.paragraphprocessor.MyAnalyzer;
import org.apache.commons.io.FileUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.search.spans.SpanQuery;
import org.apache.lucene.search.spans.SpanTermQuery;
import org.apache.lucene.search.spans.Spans;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Bits;
import org.apache.lucene.util.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * This implementation class is based on Lucene Library in Java for full-search functionality.
 * It basically uses inverted index to store doc information in a storage space..be it File System,RAM,Database.
 * Refer to {@link} to get basic understanding : https://www.javacodegeeks.com/2015/09/introduction-to-lucene.html
 * Pretty easy to use:
 * 1. set your document path and index path(where you want to store index to be lated used for searching)
 *      with setFilesPath() and setIndexPath() methods respectively.
 * 2. call index() method
 * 3. now your documents you provided are  indexed and ready to be used for search functionality.
 * 4. you can use search() method to search for a particular keyword in the indexed documents.
 * 5. use getRelevantTerms() to get a list of relevant terms from your document based on the stored repositories.
 */

public class FullTextSearchImpl implements FullTextSearch {

    private Analyzer analyzer;
    private IndexWriterConfig config;
    private IndexWriter indexWriter;

    public static final Logger LOGGER = LoggerFactory.getLogger(FullTextSearchImpl.class);
    public static final String CONTENTS = "content";

    /**
     *  This function indexes documents/source repositories and storing information in an inverted-index
     *  to facilitate fast search by using Lucene Library
     */
    @Override
    public String indexer(String filesPath,String indexPath) {
        LOGGER.info("creating indices...");
        this.analyzer = new MyAnalyzer();
        try {
            FSDirectory dir = SimpleFSDirectory.open(new File(indexPath));
            if(Files.exists(Paths.get(indexPath))) {
                LOGGER.info("already indexed..");
                return "already indexed...";
            }
            this.config = new IndexWriterConfig(Version.LUCENE_CURRENT,this.analyzer);
            File repo = new File(filesPath);
            this.indexWriter = new IndexWriter(dir,this.config);
            File[] resources = repo.listFiles();
            int id=0;
            for(File f: resources) {
                LOGGER.info("indexing file {}",f.getPath());
                Document doc = new Document();
                doc.add(new Field("path",f.getPath(), Field.Store.YES, Field.Index.ANALYZED));
                doc.add(new Field("id",String.valueOf(id), Field.Store.YES, Field.Index.ANALYZED));
                doc.add(new Field("name",f.getName(), Field.Store.YES, Field.Index.ANALYZED));
                id++;
                Reader reader = new FileReader(f.getPath());
                doc.add(new Field(CONTENTS,reader,Field.TermVector.WITH_POSITIONS_OFFSETS));
                indexWriter.addDocument(doc);
                reader.close();
                this.indexWriter.commit();
            }
            this.indexWriter.close();

            LOGGER.info("indexing complete....");
        }
        catch(Exception e) {
            LOGGER.error(String.valueOf(e.getMessage()));
            return "failure";
        }
        return "success";
    }

    /**
     * This function uses Lucene Library to  provide fast search of a given word in a  huge corpus of documents
     * @param data : the keyword that needs to be searched in the medical dictionaries/ repositories
     * @return: for now,it's just the location of all documents that contain the keyword.
     */

    @Override
    public List<String> search(String indexPath,String data) {
        LOGGER.info("searching the keyword: {}",data);
        List<String> spanArray = new ArrayList<>();
        List<String> res = new ArrayList<>();
        try {

            FSDirectory dir = FSDirectory.open(new File(indexPath));
            IndexReader iReader = IndexReader.open(dir);
            IndexSearcher searcher = new IndexSearcher(iReader);

            QueryParser queryParser = new QueryParser(CONTENTS, this.analyzer);
            queryParser.setDefaultOperator(QueryParser.Operator.OR);

            SpanQuery query = new SpanTermQuery(new Term(CONTENTS, data));
            Map<Term, TermContext> termContexts = new HashMap<>();

            for (AtomicReaderContext atomic : iReader.leaves()) {
                Bits bitset = atomic.reader().getLiveDocs();
                Spans spans = query.getSpans(atomic, bitset, termContexts);
                while (spans.next()) {
                    int docid = atomic.docBase + spans.doc();
                    String docName = searcher.doc(docid).get("name");
                    spanArray.add("Doc with name: " + docName + " and location is " + spans.end() + "th word in the document\n");
                    if(!res.contains(docName)) res.add(docName);
                }
            }
            iReader.close();
            dir.close();
        }
        catch(Exception e) {
            e.printStackTrace();
            LOGGER.debug(e.getMessage());
            return Collections.singletonList("something went wrong..");
        }

        if(spanArray.isEmpty()) return Collections.singletonList("not found");
        res.add("found");
        return res;
    }

    /**
     * this function gives a list of relevant keywords in a given document corresponding to a corpus of documents
     * @param path: the path of the document that we want to search
     * @param type: it determines the way you want to process things.
     *            If type is 1 then the input is path of the document
     *            Else the input is the document content itself as a String
     * @return: the list of relevant keywords
     */
    @Override
    public List<String> getRelevantTerms(String filePath,String indexPath,String path,int type) {

        indexer(filePath,indexPath);
        List<String> keywords = new ArrayList<>();
        LOGGER.info("please wait while we do the muscle-work.....");
        Double[][] matrix = null;
        TreeMap<Double,String> scoreList = new TreeMap<>(Collections.reverseOrder());
        try {
            String[] text;
            if(type==1)  {
                File file = new File(path);
                text = FileUtils.readFileToString(file,"UTF-8").trim().split("\\s+");
            }
            else {
                text = path.trim().split("\\s+");
            }
            File[] corpus = new File(filePath).listFiles();
            matrix = new Double[text.length][corpus.length];
            for(int i=0;i<matrix.length;i++) {
                for(int j=0;j<matrix[i].length;j++) matrix[i][j] = Double.valueOf(0);
            }
            IndexReader iReader = IndexReader.open(FSDirectory.open(new File(indexPath)));
            IndexSearcher searcher = new IndexSearcher(iReader);
            for(int i=0;i<text.length;i++) {
                SpanQuery query = new SpanTermQuery(new Term(CONTENTS,text[i]));
                TopDocs results = searcher.search(query,10);
                for(int j=0;j<results.totalHits;j++) {
                    String docid = searcher.doc(j).get("id");
                    scoreList.put((double) results.getMaxScore(),text[i]);
                    matrix[i][Integer.parseInt(docid)] = Double.valueOf(results.scoreDocs[j].score);
                }
            }
            iReader.close();
        }
        catch (Exception e) {
            LOGGER.debug(e.getMessage());
        }
        for(int i=0;i<matrix.length;i++) {
            for(int j=0;j<matrix[i].length;j++) LOGGER.info("score in Document{} is : {}",j+1,matrix[i][j]);
        }

        int cnt=0;
        for (Map.Entry<Double,String> entry : scoreList.entrySet()) {
            if (cnt < 30) {
                keywords.add(entry.getValue());
                cnt++;
            }
        }

        String res = keywords.toString();
        LOGGER.info(res);

        return keywords;
    }
}
