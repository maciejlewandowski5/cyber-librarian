package dl.extractor.features;

import dl.extractor.Feature;
import dl.parser.Article;

public class TotalWordsNumber implements Feature {

    int size;
    private static int MAX_WORDS_COUNT;

    public TotalWordsNumber(int maxArticleLength) {
        size=0;
        MAX_WORDS_COUNT = maxArticleLength;
    }

    @Override
    public void extract(Article article) {
        size = article.getBody().size();
    }

    @Override
    public Integer getFeature() {
        return size;
    }

    @Override
    public void clear() {
        size=0;
    }

    @Override
    public double getNormalizeCoefficient() {
        return 1/(double)(MAX_WORDS_COUNT);
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
