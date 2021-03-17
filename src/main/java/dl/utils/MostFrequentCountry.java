package dl.utils;

import dl.model.ExtractedArticle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MostFrequentCountry {
    public static String getMostFrequentCountry(ArrayList<ExtractedArticle> extractedArticles) {
        Map<String, Integer> countriesOccurence = new HashMap<>();
        extractedArticles.forEach(extractedArticle -> {
            String country = extractedArticle.getCountry();
            if (countriesOccurence.containsKey(country)) {
                countriesOccurence.put(country, countriesOccurence.get(country)+1);
            } else {
                countriesOccurence.put(country, 0);
            }
        });
        if (countriesOccurence.isEmpty()) {
            return "";
        } else {
            return Collections.max(countriesOccurence.entrySet(), (entry1, entry2) -> entry1.getValue() - entry2.getValue()).getKey();
        }
    }
}
