package dl.parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StopWord {
    private static Set<String> stemmedStopwords;

    static {
        try {
            stemmedStopwords = new HashSet<>();
            List<String> stopwords = Files.readAllLines(Paths.get("stopwords.txt"));
            for (String word : stopwords) {
                stemmedStopwords.add(word.toLowerCase());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean removeStopword(String word) throws IOException {
        if (stemmedStopwords.contains(word)) {
            return false;
        }

        return true;
    }
}
