package dl.extractor;

import dl.parser.Article;

public interface Feature {
    void extract(Article article);

    Object getFeature();

    void clear();

    double distance(Object object1, Object object2);
}
