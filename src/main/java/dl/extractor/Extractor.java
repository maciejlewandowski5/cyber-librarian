package dl.extractor;

import dl.extractor.features.TotalWordsNumber;
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

    public double getDistance(ExtractedArticle article1, ExtractedArticle article2) {
        double sum = 0;
        for(Feature feature:features) {
            int index = features.indexOf(feature);
            sum += feature.distance(article1.getFeatureValue(index), article2.getFeatureValue(index));
        }
        return Math.sqrt(sum);
    }


    public void addFeature(Feature feature) {
        this.features.add(feature);
    }
}
