package dl.extractor.features;

import dl.extractor.Feature;
import dl.parser.Article;

import java.util.ArrayList;
import java.util.List;

public class NumberOfPrices implements Feature {
    private static List<String> prices = new ArrayList<String>() {
        {
            add("usd");
            add("eur");
            add("jpy");
            add("gbp");
            add("chf");
            add("cnh");
            add("sek");
        }
    };
    private int priceOccurrences;

    public NumberOfPrices() {
        this.priceOccurrences = 0;
    }

    @Override
    public void extract(Article article) {
        List<String> text = article.getBody();
        text.forEach(word -> {
            if (prices.contains(word)) {
                priceOccurrences += 1;
            }
        });
    }

    @Override
    public Object getFeature() {
        return priceOccurrences;
    }

    @Override
    public void clear() {
        priceOccurrences = 0;
    }
}
