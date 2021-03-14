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

public class MostFrequent implements Feature {
    private Map<String, Integer> map;

    public MostFrequent(MostFrequentFile filenameEnum) {
        String filename = "";
        switch (filenameEnum) {
            case MONTHS:
                filename = "months.txt";
                break;
        }

        map = new HashMap<>();
        loadFile(filename);
    }

    private void loadFile(String filename) {
        try (Stream<String> lines = Files.lines(Paths.get("res", filename), Charset.defaultCharset())) {
            lines.forEachOrdered(line -> {
                line = Stemmer.stemWord(line.toLowerCase());
                map.put(line, 0);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void extract(Article article) {
        List<String> text = article.getBody();
        text.forEach(word -> {
            if (map.containsKey(word)) {
                map.put(word, map.get(word) + 1);
            }
        });
    }

    @Override
    public Object getFeature() {
        if (map.isEmpty()){
            return 0;
        }
        return Integer.valueOf(Collections.max(map.entrySet(), (entry1, entry2) -> entry1.getValue() - entry2.getValue()).getKey());
    }

    @Override
    public void clear() {
        map = new HashMap<>();
    }
}
