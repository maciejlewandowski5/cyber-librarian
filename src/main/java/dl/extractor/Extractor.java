package dl.extractor;

import dl.extractor.features.TotalWordsNumber;
import dl.parser.Article;

import java.util.ArrayList;

public class Extractor {

    ArrayList<Feature> features;

    public Extractor() {
        features = new ArrayList<>();
    }

    public void extract(Article article){
        features.forEach(feature -> feature.extract(article));
    }

    public void clear(){
        features.forEach(Feature::clear);
    }

    public Object getFeature(int i){
        return features.get(i).getFeature();
    }

    public void addFeature(Feature feature) {
        this.features.add(feature);
    }
}
