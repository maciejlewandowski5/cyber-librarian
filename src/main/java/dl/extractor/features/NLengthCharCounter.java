package dl.extractor.features;

import dl.extractor.Feature;
import dl.parser.Article;

public class NLengthCharCounter implements Feature {

    int minimalLength;
    int maximalLength;
    int numberOfWordsInWithSpecificLength;
    Double result;

    public NLengthCharCounter(int minimalLength, int maximalLength) {
        this.minimalLength = minimalLength;
        this.maximalLength = maximalLength;
        numberOfWordsInWithSpecificLength = 0;
        result=0d;
    }

    public void clear(){
        numberOfWordsInWithSpecificLength = 0;
        result=0d;
    }

    @Override
    public double getNormalizeCoefficient() {
        return  1;
    }

    @Override
    public double preEuclideanDistance(Object object1, Object object2) {
        return ((Double)object1 - (Double)object2)*((Double)object1 - (Double)object2);
    }

    @Override
    public double preTaxiCabGeometryDistance(Object object1, Object object2) {
        return Math.abs((Double)object1 - (Double)object2);
    }

    @Override
    public double preCousinsAmplitudeNominatorDistance(Object object1, Object object2) {
        return (Double)object1 * (Double)object2;
    }

    @Override
    public double preCousinsAmplitudeDenominatorDistance(Object object1) {
        return (Double)object1 * (Double)object1;
    }

    @Override
    public void extract(Article article) {
        article.getBody().forEach(word -> {
            if (word.length() > minimalLength && word.length() < maximalLength) {
                numberOfWordsInWithSpecificLength++;
            }
        });
        result=numberOfWordsInWithSpecificLength/(double)article.getBody().size();
    }

    @Override
    public Double getFeature() {
        return result;
    }
}
