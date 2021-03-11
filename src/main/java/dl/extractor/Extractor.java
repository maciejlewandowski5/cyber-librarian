package dl.extractor;

import dl.parser.Article;

import java.util.ArrayList;

public class Extractor {

    ArrayList<Feature> features;

    public Extractor() {
        features = new ArrayList<>();
    }

    public void setFeatures(Feature feature) {
        this.features.add(feature);
    }

    public void extract(Article article){
        features.forEach(feature -> feature.extract(article));
    }

    public Object getFeature(int i){
        return features.get(i).getFeature();
    }
}
