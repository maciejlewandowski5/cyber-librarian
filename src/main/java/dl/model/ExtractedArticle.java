package dl.model;

import dl.extractor.Feature;

import java.util.ArrayList;

public class ExtractedArticle {

    ArrayList<Object> featureValues;
    private String country;

//    public ExtractedArticle(ArrayList<Object> features) {
//        this.featureValues = features;
//    }

    public ExtractedArticle(ArrayList<Object> featureValues, String country) {
        this.featureValues = featureValues;
        this.country = country;
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

    public String getCountry() {
        return country;
    }
}
