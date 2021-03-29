package dl.extractor;

import dl.parser.Article;

public interface Feature {
    void extract(Article article);

    Object getFeature();

    void clear();

    double getNormalizeCoefficient();

    double preEuclideanDistance(Object object1, Object object2);

    double preTaxiCabGeometryDistance(Object object1, Object object2);

    double preCousinsAmplitudeNominatorDistance(Object object1, Object object2);
    double preCousinsAmplitudeDenominatorDistance(Object object1);
}
