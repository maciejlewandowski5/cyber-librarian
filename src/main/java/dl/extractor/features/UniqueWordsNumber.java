package dl.extractor.features;

import dl.extractor.Feature;
import dl.parser.Article;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class UniqueWordsNumber implements Feature {
    private HashSet<String> words;

    public UniqueWordsNumber() {
        this.words = new HashSet<>();
    }

    @Override
    public void extract(Article article) {
        List<String> text = article.getBody();
        text.forEach(word -> {
            if (!words.contains(word)) {
                words.add(word);
            }
        });
    }

    @Override
    public Object getFeature() {
        return words.size();
    }

    @Override
    public void clear() {
        words = new HashSet<>();
    }
}