package com.stackroute.knowledgevault.paragraphprocessor.algos;

import java.util.List;

public interface FullTextSearch {

    String indexer();
    List<String> search(String data);
    String getIndexPath();
    String getFilesPath();
    void setIndexPath(String path);
    void setFilesPath(String path);
    List<String> getRelevantTerms(String path,int type);

}
