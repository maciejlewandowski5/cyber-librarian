package dl.extractor;

import dl.parser.Article;

public interface Feature {
    void extract(Article article);

    Object getFeature();

    void clear();
}
