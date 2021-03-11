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
}
