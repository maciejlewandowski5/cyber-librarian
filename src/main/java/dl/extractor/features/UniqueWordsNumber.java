package dl.extractor.features;

import dl.extractor.Feature;
import dl.parser.Article;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class UniqueWordsNumber implements Feature {
    private HashSet<String> words;
    private static int MAX_WORDS_COUNT;

    public UniqueWordsNumber(int maxArticleLength) {
        this.words = new HashSet<>();
        this.MAX_WORDS_COUNT = maxArticleLength;
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
    public Integer getFeature() {
        return words.size();
    }

    @Override
    public void clear() {
        words = new HashSet<>();
    }

    @Override
    public double getNormalizeCoefficient() {
        return 1/(double)MAX_WORDS_COUNT;
    }

    @Override
    public double preEuclideanDistance(Object object1, Object object2) {
        return getNormalizeCoefficient()*getNormalizeCoefficient()*((Integer)object1 - (Integer)object2)*((Integer)object1 - (Integer)object2);
    }

    @Override
    public double preTaxiCabGeometryDistance(Object object1, Object object2) {
        return Math.abs(getNormalizeCoefficient()*((Integer)object1 - (Integer)object2));
    }

    @Override
    public double preCousinsAmplitudeNominatorDistance(Object object1, Object object2) {
        return getNormalizeCoefficient()*(Integer)object1 * (Integer)object2;
    }

    @Override
    public double preCousinsAmplitudeDenominatorDistance(Object object1) {
        return getNormalizeCoefficient()*(Integer)object1 * (Integer)object1;
    }
}
