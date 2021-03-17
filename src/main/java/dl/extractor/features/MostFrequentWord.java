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

public class MostFrequentWord extends MostFrequent {

    public MostFrequentWord(MostFrequentFile filenameEnum) {
        String filename = checkFilename(filenameEnum);

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
                Integer count = map.get(word);
                map.put(word, count + 1);
            }
        });
    }
}
