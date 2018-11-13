package com.stackroute.algos;

import com.stackroute.domain.DocInfo;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.*;
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

public class FullTextSearchImpl implements FullTextSearch {

    private static String filesPath;
    private static String indexPath;

    public static final Logger LOGGER = LoggerFactory.getLogger(FullTextSearchImpl.class);
    private DocInfo docInfo;

    @Override
    public String getIndexPath() {
        return indexPath;
    }

    @Override
    public String getFilesPath() {
        return filesPath;
    }

    @Override
    public void setIndexPath(String path) {
        indexPath = path;
    }

    @Override
    public void setFilesPath(String path) {
        filesPath = path;
    }

    /**
     *  This function indexes documents/source repositories and storing information in an inverted-index
     *  to facilitate fast search by using Lucene Library
     */
    @Override
    public String indexer() {
        LOGGER.info("creating indices....");
        Analyzer analyzer = new StandardAnalyzer();
        try {
            FSDirectory dir = new SimpleFSDirectory(new File(indexPath));
            if(Files.exists(Paths.get(indexPath))) {
                LOGGER.info("already indexed..");
                return "already indexed...";
            }
            IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_CURRENT,analyzer);
            IndexWriter indexWriter = new IndexWriter(dir,config);
            File repo = new File(filesPath);
            this.docInfo = new DocInfo();

            File[] resources = repo.listFiles();
            int id=0;
            for(File f: resources) {
                LOGGER.info("indexing file {}",f.getCanonicalPath());
                Document doc = new Document();
                doc.add(new Field("path",f.getPath(), Field.Store.YES, Field.Index.ANALYZED));
                doc.add(new Field("name",f.getName(), Field.Store.YES, Field.Index.ANALYZED));
                this.docInfo.add(id,f.getName());
                id++;
                Reader reader = new FileReader(f.getCanonicalPath());
                doc.add(new Field("contents",reader,Field.TermVector.WITH_POSITIONS_OFFSETS));
                indexWriter.addDocument(doc);
                reader.close();
            }
            indexWriter.close();

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
    public List<String> search(String data) {
        StringBuilder sb = new StringBuilder();
        LOGGER.info("searching the keyword: {}",data);
        List<String> spanArray = new ArrayList<>();
        try {
            IndexReader iReader = IndexReader.open(FSDirectory.open(new File(indexPath)));
            IndexSearcher searcher = new IndexSearcher(iReader);

            SpanQuery query = new SpanTermQuery(new Term("contents", data));
            TopDocs results = searcher.search(query,10);
            Map<Term, TermContext> termContexts = new HashMap<>();

            for (AtomicReaderContext atomic : iReader.leaves()) {
                Bits bitset = atomic.reader().getLiveDocs();
                Spans spans = query.getSpans(atomic, bitset, termContexts);
                while (spans.next()) {
                    int docid = atomic.docBase + spans.doc();
                    spanArray.add("Doc: " + docid + " and location is from " + spans.start() + " to "
                            + spans.end()+"\n");
                };
            };
            for(String s: spanArray) LOGGER.info(s);
        }
        catch(Exception e) {
            LOGGER.debug(e.getMessage());
            return Collections.singletonList("error.............");
        }

        if(spanArray.isEmpty()) return Collections.singletonList("not found");
        spanArray.add("found");
        return spanArray;
    }
}
