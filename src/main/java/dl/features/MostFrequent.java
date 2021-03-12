package dl.features;

import dl.model.MostFrequentFile;
import dl.parser.Stemmer;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class MostFrequent {
    private Map<String, Integer> map;

    public MostFrequent(List<String> text, MostFrequentFile filenameEnum) {
        String filename = "";
        switch (filenameEnum) {
            case MONTHS:
                filename = "months.txt";
                break;
        }

        map = new HashMap<>();
        try (Stream<String> lines = Files.lines(Paths.get("res", filename), Charset.defaultCharset())) {
            lines.forEachOrdered(line -> {
                line = Stemmer.stemWord(line.toLowerCase());
                map.put(line, 0);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        loadText(text);
    }

    private void loadText(List<String> text) {
        text.forEach(word -> {
            if (map.containsKey(word)) {
                map.put(word, map.get(word) + 1);
            }
        });
    }

    public Map<String, Integer> getMap() {
        return map;
    }

    public String getHighest() {
        return Collections.max(map.entrySet(), (entry1, entry2) -> entry1.getValue() - entry2.getValue()).getKey();
    }
}
