package dl.knn;

import dl.extractor.Extractor;
import dl.model.ExtractedArticle;


import java.util.*;

public class Knn {

    int kNeighbours;
    ArrayList<ExtractedArticle> extractedArticles = new ArrayList<>();
    Extractor extractor;

    public Knn(int kNeighbours, ArrayList<ExtractedArticle> extractedArticles, Extractor extractor) {
        this.kNeighbours = kNeighbours;
        this.extractedArticles = extractedArticles;
        this.extractor = extractor;
    }

    public ArrayList<ExtractedArticle> findKNearestNeighbours(ExtractedArticle hero) {
        ArrayList<ExtractedArticle> neighbours = new ArrayList<>();
        SortedSet<Pair<ExtractedArticle, Double>> distances =
                new TreeSet<Pair<ExtractedArticle, java.lang.Double>>(
                        new Comparator<Pair<ExtractedArticle, Double>>() {
                            @Override
                            public int compare(Pair<ExtractedArticle, Double> o1
                                    , Pair<ExtractedArticle, Double> o2) {
                                return o1.second.compareTo(o2.second);
                            }
                        });

        for (ExtractedArticle extractedArticle : extractedArticles) {
            if (!extractedArticle.equals(hero)) {
                distances.add(new Pair<>(extractedArticle, extractor.getDistance(hero, extractedArticle)));
            }
        }
        Pair<ExtractedArticle, Double> n = null;
        Iterator<Pair<ExtractedArticle, Double>> iterator = distances.iterator();
        for (int i = 0; i <= kNeighbours; i++) {
            n =iterator.next();
            neighbours.add(n.first);
        }
        return neighbours;
    }
}
