package com.stackroute.knowledgevault.paragraphprocessor.algos;

import java.util.List;

/**
 * This interface class is based on Lucene Library in Java for full-search functionality.
 * It basically uses inverted index to store doc information in a storage space..be it File System,RAM,Datavbase.
 * Refer to {@link} to get basic understanding : https://www.javacodegeeks.com/2015/09/introduction-to-lucene.html
 * Pretty easy to use:
 * 1. set your document path and index path(where you want to store index to be lated used for searching)
 *      with setFilesPath() and setIndexPath() methods respectively.
 * 2. call index() method
 * 3. now your documents you provided are  indexed and ready to be used for search functionality.
 * 4. you can use search() method to search for a particular keyword in the indexed documents.
 * 5. use getRelevantTerms() to get a list of relevant terms from your document based on the stored repositories.
 */
public interface FullTextSearch {

    String indexer(String filePath,String indexPath);
    List<String> search(String indexPath,String data);
    List<String> getRelevantTerms(String filePath,String indexPath,String path,int type);

}
