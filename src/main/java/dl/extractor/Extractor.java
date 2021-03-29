package dl.extractor;

import dl.model.ExtractedArticle;
import dl.parser.Article;

import java.util.ArrayList;

public class Extractor {

    ArrayList<Feature> features;

    public Extractor() {
        features = new ArrayList<>();
    }

    public void extract(Article article) {
        features.forEach(feature -> feature.extract(article));
    }

    public void clear() {
        features.forEach(Feature::clear);
    }

    public Object getFeatureValue(int i) {
        return features.get(i).getFeature();
    }

    public ArrayList<Object> getFeaturesValues() {
        ArrayList<Object> result = new ArrayList<>();
        features.forEach(feature -> result.add(feature.getFeature()));
        return result;
    }

    public double getEuclideanDistance(ExtractedArticle article1, ExtractedArticle article2) {
        double sum = 0;
        for (Feature feature : features) {
            int index = features.indexOf(feature);
            sum += feature.preEuclideanDistance(
                    article1.getFeatureValue(index),
                    article2.getFeatureValue(index));
        }
        return Math.sqrt(sum);
    }

    double getTaxiCabGeometryDistance(ExtractedArticle article1, ExtractedArticle article2) {
        double sum = 0;
        for (Feature feature : features) {
            int index = features.indexOf(feature);
            sum += feature.preTaxiCabGeometryDistance(
                    article1.getFeatureValue(index),
                    article2.getFeatureValue(index));
        }
        return sum;
    }


    double getCousinsAmplitudeDistance(ExtractedArticle article1, ExtractedArticle article2) {
        double nominator = 0;
        double sumOfSquares1 = 0;
        double sumOfSquares2 = 0;

        for (Feature feature : features) {
            int index = features.indexOf(feature);
            nominator += feature.preCousinsAmplitudeNominatorDistance(
                    article1.getFeatureValue(index),
                    article2.getFeatureValue(index));
            sumOfSquares1 += feature.preCousinsAmplitudeDenominatorDistance(article1);
            sumOfSquares2 += feature.preCousinsAmplitudeDenominatorDistance(article2);

        }
        nominator = Math.abs(nominator);
        double denominator = Math.sqrt(sumOfSquares1 * sumOfSquares2);
        return nominator / denominator;
    }


    public void addFeature(Feature feature) {
        this.features.add(feature);
    }

    public void addFeatures(ArrayList features) {
        this.features = features;
    }
}
