package com.stackroute.knowledgevault.paragraphprocessor.algos;

import java.util.*;

public class TfIdf {

    public enum TfType { NATURAL,LOGARITHM,BOOLEAN }
    public enum Normalization { NONE,COSINE }

    /**
     * Term frequency for a single document
     *
     * @param document bag of terms
     * @param type     natural or logarithmic
     * @return map of terms to their term frequencies
     */
    public static <String> Map<String, Double> tf(Collection<String> document, TfType type) {
        Map<String, Double> tf = new HashMap<>();
        for (String term : document) {
            tf.put(term, tf.getOrDefault(term, 0.0) + 1);
        }
        if (type != TfType.NATURAL) {
            for (String term : tf.keySet()) {
                switch (type) {
                    case LOGARITHM:
                        tf.put(term, 1 + Math.log(tf.get(term)));
                        break;
                    case BOOLEAN:
                        tf.put(term, tf.get(term) == 0.0 ? 0.0 : 1.0);
                        break;
                }
            }
        }
        return tf;
    }

    /**
     * Natural term frequency for a single document
     *
     * @param document bag of terms
     * @return map of terms to their term frequencies
     */
    public static <String> Map<String, Double> tf(Collection<String> document) {
        return tf(document, TfType.NATURAL);
    }

    /**
     * Term frequencies for a set of documents
     *
     * @param documents sequence of documents, each of which is a bag of terms
     * @param type      natural or logarithmic
     * @param <String>    term type
     * @return sequence of map of terms to their term frequencies
     */
    public static <String> Iterable<Map<String, Double>> tfs(Iterable<Collection<String>> documents, TfType type) {
        List<Map<String, Double>> tfs = new ArrayList<>();
        for (Collection<String> document : documents) {
            tfs.add(tf(document, type));
        }
        return tfs;
    }

    /**
     * Natural term frequencies for a set of documents
     *
     * @param documents sequence of documents, each of which is a bag of terms
     * @param <String>    term type
     * @return sequence of map of terms to their term frequencies
     */
    public static <String> Iterable<Map<String, Double>> tfs(Iterable<Collection<String>> documents) {
        return tfs(documents, TfType.NATURAL);
    }

    /**
     * Inverse document frequency for a set of documents
     *
     * @param corpus sets of terms which appear in the documents
     * @param smooth               smooth the counts by treating the document set as if it contained an additional
     *                             document with every term in the vocabulary
     * @param addOne               add one to idf values to prevent divide by zero errors in tf-idf
     * @param <String>               term type
     * @return map of terms to their inverse document frequency
     */
    public static <String> Map<String, Double> idf(Iterable<Iterable<String>> corpus,
                                               boolean smooth, boolean addOne) {
        Map<String, Integer> df = new HashMap<>();
        int d = smooth ? 1 : 0;
        int a = addOne ? 1 : 0;
        int n = d;
        for (Iterable<String> documentVocabulary : corpus) {
            n += 1;
            for (String term : documentVocabulary) {
                df.put(term, df.getOrDefault(term, d) + 1);
            }
        }
        Map<String, Double> idf = new HashMap<>();
        for (Map.Entry<String, Integer> e : df.entrySet()) {
            String term = e.getKey();
            double f = e.getValue();
            idf.put(term, Math.log(n / f) + a);
        }
        return idf;
    }

    /**
     * Smoothed, add-one inverse document frequency for a set of documents
     *
     * @param corpus sets of terms which appear in the documents
     * @param <String>               term type
     * @return map of terms to their inverse document frequency
     */
    public static <String> Map<String, Double> idf(Iterable<Iterable<String>> corpus) {
        return idf(corpus, true, true);
    }

    /**
     * tf-idf for a document
     *
     * @param tf            term frequencies of the document
     * @param idf           inverse document frequency for a set of documents
     * @param normalization none or cosine
     * @param <String>        term type
     * @return map of terms to their tf-idf values
     */
    public static <String> Map<String, Double> tfIdf(Map<String, Double> tf, Map<String, Double> idf,
                                                 Normalization normalization) {
        Map<String, Double> tfIdf = new HashMap<>();
        for (String term : tf.keySet()) {
            tfIdf.put(term, tf.get(term) * idf.get(term));
        }
        if (normalization == Normalization.COSINE) {
            double n = 0.0;
            for (double x : tfIdf.values()) {
                n += x * x;
            }
            n = Math.sqrt(n);

            for (String term : tfIdf.keySet()) {
                tfIdf.put(term, tfIdf.get(term) / n);
            }
        }
        return tfIdf;
    }

    /**
     * Unnormalized tf-idf for a document
     *
     * @param tf     term frequencies of the document
     * @param idf    inverse document frequency for a set of documents
     * @param <String> term type
     * @return map of terms to their tf-idf values
     */
    public static <String> Map<String, Double> tfIdf(Map<String, Double> tf, Map<String, Double> idf) {
        return tfIdf(tf, idf, Normalization.NONE);
    }

    /**
     * Utility to build inverse document frequencies from a set of term frequencies
     *
     * @param tfs    term frequencies for a set of documents
     * @param smooth smooth the counts by treating the document set as if it contained an additional
     *               document with every term in the vocabulary
     * @param addOne add one to idf values to prevent divide by zero errors in tf-idf
     * @param <String> term type
     * @return map of terms to their tf-idf values
     */
    public static <String> Map<String, Double> idfFromTfs(Iterable<Map<String, Double>> tfs, boolean smooth, boolean addOne) {
        return idf(new KeySetIterable<>(tfs), smooth, addOne);
    }

    /**
     * Utility to build smoothed, add-one inverse document frequencies from a set of term frequencies
     *
     * @param tfs    term frequencies for a set of documents
     * @param <String> term type
     * @return map of terms to their tf-idf values
     */
    public static <String> Map<String, Double> idfFromTfs(Iterable<Map<String, Double>> tfs) {
        return idfFromTfs(tfs, true, true);
    }

    /**
     * Iterator over the key sets of a set of maps.
     *
     * @param <KEY>   map key type
     * @param <VALUE> map value type
     */
    static private class KeySetIterable<KEY, VALUE> implements Iterable<Iterable<KEY>> {
        final private Iterator<Map<KEY, VALUE>> maps;

        public KeySetIterable(Iterable<Map<KEY, VALUE>> maps) {
            this.maps = maps.iterator();
        }

        @Override
        public Iterator<Iterable<KEY>> iterator() {
            return new Iterator<Iterable<KEY>>() {
                @Override
                public boolean hasNext() {
                    return maps.hasNext();
                }

                @Override
                public Iterable<KEY> next() {
                    return maps.next().keySet();
                }
            };
        }
    }
}
