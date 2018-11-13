package com.stackroute.knowledgevault.benchmark;

import com.stackroute.knowledgevault.Processor;

public class Benchmarking {

    public static void main(String[] args) {
        Processor processor = new Processor();
        processor.getFullTextSearch().setFilesPath("paragraph-processor/src/main/java/com/stackroute/benchmark/files");
        processor.getFullTextSearch().setIndexPath("paragraph-processor/src/main/java/com/stackroute/benchmark/indices");

        processor.getFullTextSearch().indexer();
        processor.getFullTextSearch().search("hepatic");
    }

}
