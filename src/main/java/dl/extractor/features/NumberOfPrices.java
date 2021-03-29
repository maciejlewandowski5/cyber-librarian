package dl.extractor.features;

import dl.extractor.Feature;
import dl.parser.Article;

import java.util.ArrayList;
import java.util.List;

public class NumberOfPrices implements Feature {
    private static List<String> prices = new ArrayList<String>() {
        {
            add("usd");
            add("$");
            add("eur");
            add("€");
            add("jpy");
            add("¥");
            add("gbp");
            add("£");
            add("chf");
            add("rub");
            add("руб");
            add("sek");
            add("kr");
        }
    };
    private int priceOccurrences;
    private int totalWords;

    public NumberOfPrices() {
        this.priceOccurrences = 0;
        this.totalWords = 0;
    }

    @Override
    public void extract(Article article) {
        List<String> text = article.getBody();
        text.forEach(this::checkOccurrence);
        totalWords = text.size();
    }

    private void checkOccurrence(String word) {
        for (int i = 0; i < prices.size(); i++) {
            String item = prices.get(i);
            if (word.contains(item)) {
                priceOccurrences += 1;
                break;
            }
        }
    }

    @Override
    public Integer getFeature() {
        return priceOccurrences;
    }

    @Override
    public void clear() {
        priceOccurrences = 0;
        totalWords = 0;
    }

    @Override
    public double getNormalizeCoefficient() {
        return 1/(double)(totalWords);
    }

    @Override
    public double preEuclideanDistance(Object object1, Object object2) {
        return getNormalizeCoefficient()*getNormalizeCoefficient()*((Integer) object1 - (Integer) object2) * ((Integer) object1 - (Integer) object2);
    }

    @Override
    public double preTaxiCabGeometryDistance(Object object1, Object object2) {
        return Math.abs(getNormalizeCoefficient()*((Integer) object1 - (Integer) object2));
    }

    @Override
    public double preCousinsAmplitudeNominatorDistance(Object object1, Object object2) {
        return getNormalizeCoefficient()*((Integer) object1 * (Integer) object2);
    }

    @Override
    public double preCousinsAmplitudeDenominatorDistance(Object object1) {
        return getNormalizeCoefficient()*(Integer) object1 * (Integer) object1;
    }
}
