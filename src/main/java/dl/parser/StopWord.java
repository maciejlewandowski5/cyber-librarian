package dl.parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class StopWord {
    private static List<String> stemmedStopwords;

    static {
        try {
            stemmedStopwords = new ArrayList<>();
            List<String> stopwords = Files.readAllLines(Paths.get("stopwords.txt"));
            for (String word : stopwords) {
                stemmedStopwords.add(word.toLowerCase());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    ;

    public static boolean removeStopword(String word) throws IOException {
        if (stemmedStopwords.contains(word)) {
            return false;
        }

        return true;
    }
}
