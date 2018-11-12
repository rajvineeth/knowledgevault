package com.stackroute.algos;

public interface FullTextSearch {

    String indexer();
    String search(String data);
    String getIndexPath();
    String getFilesPath();
    void setIndexPath(String path);
    void setFilesPath(String path);
}
