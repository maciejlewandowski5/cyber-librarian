package dl.extractor.features;

import dl.extractor.Feature;
import dl.parser.Article;

public class TotalWordsNumber implements Feature {

    int size;

    public TotalWordsNumber() {
        size=0;
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
    public double distance(Object object1, Object object2) {
        return ((Integer)object1 - (Integer)object2)*((Integer)object1 - (Integer)object2);
    }
}
