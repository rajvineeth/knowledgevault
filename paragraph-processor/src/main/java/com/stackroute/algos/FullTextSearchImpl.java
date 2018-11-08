package com.stackroute.algos;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;

public class FullTextSearchImpl implements FullTextSearch {

    public static final String FILE = "/home/cgi/Documents/stackroute-proj/knowledge-vault/paragraph-processor/src/main/java/com/stackroute/assets";
    public static final String INDEX = "/home/cgi/Documents/stackroute-proj/knowledge-vault/paragraph-processor/src/main/java/com/stackroute/dataRepo";
    public static final Logger LOGGER = LoggerFactory.getLogger(FullTextSearchImpl.class);

    @Override
    public void indexer(boolean isItIndexed) {
        if(isItIndexed) return;
        LOGGER.info("creating indices....");
        Analyzer analyzer = new StandardAnalyzer();
        try {
            FSDirectory dir = new SimpleFSDirectory(new File(INDEX));
            IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_4_10_3,analyzer);
            IndexWriter indexWriter = new IndexWriter(dir,config);
            File repo = new File(FILE);

            File[] resources = repo.listFiles();
            for(File f: resources) {
                LOGGER.info("indexing file {}",f.getCanonicalPath());
                Document doc = new Document();
                doc.add(new Field("path",f.getPath(), Field.Store.YES, Field.Index.ANALYZED));
                doc.add(new Field("isIndexed","false", Field.Store.YES, Field.Index.ANALYZED));
                Reader reader = new FileReader(f.getCanonicalPath());
                doc.add(new Field("contents",reader));
                indexWriter.addDocument(doc);
                reader.close();
            }
            indexWriter.close();

            LOGGER.info("indexing complete....");
        }
        catch(Exception e) {
            LOGGER.error(String.valueOf(e.getMessage()));
        }
    }

    @Override
    public void search(String data) {
        LOGGER.info("searching the keyword: {}",data);
        try {
            IndexReader iReader = IndexReader.open(FSDirectory.open(new File(INDEX)));
            IndexSearcher searcher = new IndexSearcher(iReader);
            Analyzer analyzer = new StandardAnalyzer();
            QueryParser queryParser = new QueryParser("contents",analyzer);
            Query query = queryParser.parse(data);
            TopDocs hits = searcher.search(query,100);
            if(hits.totalHits==0) {
                LOGGER.info("no data found");
            }
            else {
                int cnt=0;
                for(int i=0;i<hits.totalHits;i++) {
                    Document doc = searcher.doc(hits.scoreDocs[i].doc);
                    String url = doc.get("path");
                    if(url!="null") cnt++;
                    LOGGER.info("found in: {}",url);
                }
                LOGGER.info("total hit: {} ",cnt);
            }
        }
        catch(Exception e) {
            LOGGER.debug(e.getMessage());
        }
    }
}
