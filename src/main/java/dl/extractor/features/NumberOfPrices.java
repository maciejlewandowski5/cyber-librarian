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

    public NumberOfPrices() {
        this.priceOccurrences = 0;
    }

    @Override
    public void extract(Article article) {
        List<String> text = article.getBody();
        text.forEach(word -> {
            checkOccurrence(word);
        });
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
    public Object getFeature() {
        return priceOccurrences;
    }

    @Override
    public void clear() {
        priceOccurrences = 0;
    }
}
