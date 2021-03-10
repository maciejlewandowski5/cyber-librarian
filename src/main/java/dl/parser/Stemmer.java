package dl.parser;

import opennlp.tools.stemmer.PorterStemmer;

public class Stemmer {
    private static PorterStemmer porterStemmer = new PorterStemmer();

    public static String stemWord(String word) {
        String out = porterStemmer.stem(word);

        return out;
    }
}
