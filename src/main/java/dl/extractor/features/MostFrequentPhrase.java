package dl.extractor.features;

import dl.extractor.Feature;
import dl.model.MostFrequentFile;
import dl.parser.Article;
import dl.parser.Stemmer;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class MostFrequentPhrase extends MostFrequent {
    private ArrayList<String> phrases;

    public MostFrequentPhrase (MostFrequentFile filenameEnum) {
        String filename = checkFilename(filenameEnum);

        map = new HashMap<>();
        phrases = new ArrayList<>();
        loadFile(filename);
    }

    private void loadFile(String filename) {
        try (Stream<String> lines = Files.lines(Paths.get("res", filename), Charset.defaultCharset())) {
            lines.forEachOrdered(line -> {
                line = Stemmer.stemWord(line.toLowerCase());
                map.put(line, 0);
                phrases.add(line);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void extract(Article article) {
        String preprocessedText = article.getPreProcessedBody();

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            int lastOccurrence = 0;
            while (lastOccurrence != -1) {
                lastOccurrence = preprocessedText.indexOf(entry.getKey(), lastOccurrence + 1);
                if (lastOccurrence != -1) {
                    map.put(entry.getKey(), entry.getValue() + 1);
                }
            }
        }
    }
}
