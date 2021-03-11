package dl.extractor.features;

import dl.extractor.Feature;
import dl.parser.Article;

public class NLengthCharCounter implements Feature {

    int minimalLength;
    int maximalLength;
    int numberOfWordsInWithSpecificLength;

    public NLengthCharCounter(int minimalLength, int maximalLength) {
        this.minimalLength = minimalLength;
        this.maximalLength = maximalLength;
        numberOfWordsInWithSpecificLength = 0;
    }

    public void clear(){
        this.minimalLength = minimalLength;
        this.maximalLength = maximalLength;
        numberOfWordsInWithSpecificLength = 0;
    }
    @Override
    public void extract(Article article) {
        article.getBody().forEach(word -> {
            if (word.length() > minimalLength && word.length() < maximalLength) {
                numberOfWordsInWithSpecificLength++;
            }
        });
    }

    @Override
    public Integer getFeature() {
        return numberOfWordsInWithSpecificLength;
    }
}
