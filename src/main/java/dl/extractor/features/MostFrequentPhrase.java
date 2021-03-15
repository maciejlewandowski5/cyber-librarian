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

public class MostFrequentPhrase implements Feature {
    private Map<String, Integer> map;
    private ArrayList<String> phrases;

    public MostFrequentPhrase (MostFrequentFile filenameEnum) {
        String filename = "";
        switch (filenameEnum) {
            case MONTHS:
                filename = "months.txt";
                break;
            case BUSINESS_ENTITY:
                filename = "businessEntity.txt";
                break;
            case STOCK_EXCHANGE:
                filename = "stockExchange.txt";
                break;
            case SEAS:
                filename = "seas.txt";
                break;
        }

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

    @Override
    public String getFeature() {
        if (map.isEmpty()){
            return "";
        }
        String key = Collections.max(map.entrySet(), (entry1, entry2) -> entry1.getValue() - entry2.getValue()).getKey();
        if (map.get(key) > 0) {
            return key;
        } else {
            return "";
        }
    }

    @Override
    public void clear() {
        map.forEach((k,v) ->map.put(k,0));
    }
}
