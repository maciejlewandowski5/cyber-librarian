package dl;

import dl.extractor.features.*;
import dl.knn.DatasetSplitter;
import dl.knn.Knn;
import dl.model.ExtractedArticle;
import dl.model.MostFrequentFile;
import dl.extractor.Extractor;
import dl.parser.Article;
import dl.parser.ArticlesLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        MostFrequentWord mostFrequentWordMonth = new MostFrequentWord(MostFrequentFile.MONTHS);
        MostFrequentWord mostFrequentWordBusinessEntity = new MostFrequentWord(MostFrequentFile.BUSINESS_ENTITY);
        MostFrequentPhrase mostFrequentStockExchange = new MostFrequentPhrase(MostFrequentFile.STOCK_EXCHANGE);
        MostFrequentPhrase mostFrequentSea = new MostFrequentPhrase(MostFrequentFile.SEAS);
        NumberOfPrices numberOfPrices = new NumberOfPrices();
        UniqueWordsNumber uniqueWordsNumber = new UniqueWordsNumber();

        extractor.addFeature(totalWordsNumber);
        extractor.addFeature(unitType);
        extractor.addFeature(shortWords);
        extractor.addFeature(middleWords);
        extractor.addFeature(longWords);
        extractor.addFeature(mostCommonYear);
        extractor.addFeature(mostFrequentWordMonth);
        extractor.addFeature(mostFrequentWordBusinessEntity);
        extractor.addFeature(mostFrequentStockExchange);
        extractor.addFeature(mostFrequentSea);
        extractor.addFeature(numberOfPrices);
        extractor.addFeature(uniqueWordsNumber);

        ArrayList<ExtractedArticle> extractedArticles = new ArrayList<>();

        for (Article a : articlesLoader.getArticles()) {
            extractor.clear();
            extractor.extract(a);
            extractedArticles.add(new ExtractedArticle(extractor.getFeaturesValues()));
        }
        Knn knn = new Knn(15,extractedArticles,extractor);
        for (ExtractedArticle n:knn.findKNearestNeighbours(extractedArticles.get(6))){
            System.out.println(n);
        }
    }
}
