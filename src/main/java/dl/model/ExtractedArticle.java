package dl.model;

import dl.extractor.Feature;

import java.util.ArrayList;

public class ExtractedArticle {

    ArrayList<Object> featureValues;

    public ExtractedArticle(ArrayList<Object> features) {
        this.featureValues = features;
    }

    public Object getFeatureValue(int index) {
        return featureValues.get(index);
    }

    @Override
    public String toString() {
        return "ExtractedArticle{" +
                "featureValues=" + featureValues +
                '}';
    }
}
