package dl;

import dl.extractor.features.MostFrequent;
import dl.model.MostFrequentFile;
import dl.extractor.Extractor;
import dl.extractor.features.MostCommonYear;
import dl.extractor.features.NLengthCharCounter;
import dl.extractor.features.TotalWordsNumber;
import dl.extractor.features.UnitType;
import dl.parser.Article;
import dl.parser.ArticlesLoader;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        ArticlesLoader articlesLoader = new ArticlesLoader();

        Extractor extractor = new Extractor();
        TotalWordsNumber totalWordsNumber = new TotalWordsNumber();
        UnitType unitType = new UnitType();
        NLengthCharCounter shortWords = new NLengthCharCounter(0, 4);
        NLengthCharCounter middleWords = new NLengthCharCounter(5, 8);
        NLengthCharCounter longWords = new NLengthCharCounter(9, Integer.MAX_VALUE);
        MostCommonYear mostCommonYear = new MostCommonYear();
        MostFrequent mostFrequentMonth = new MostFrequent(MostFrequentFile.MONTHS);

        extractor.addFeature(totalWordsNumber);
        extractor.addFeature(unitType);
        extractor.addFeature(shortWords);
        extractor.addFeature(middleWords);
        extractor.addFeature(longWords);
        extractor.addFeature(mostCommonYear);
        extractor.addFeature(mostFrequentMonth);

        for (Article a : articlesLoader.getArticles()) {
            System.out.println(a);
            extractor.clear();
            extractor.extract(a);
            if (extractor.getFeature(1) != null) {
                System.out.println(extractor.getFeature(0));
                System.out.println(extractor.getFeature(1));
                System.out.println(extractor.getFeature(2));
                System.out.println(extractor.getFeature(3));
                System.out.println(extractor.getFeature(4));
                System.out.println(extractor.getFeature(5));
                System.out.println(extractor.getFeature(6));
            }
            ;
        }

    }
}
