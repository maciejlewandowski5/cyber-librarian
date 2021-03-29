package dl;

import dl.extractor.features.*;
import dl.knn.DatasetSplitter;
import dl.knn.Knn;
import dl.utils.MostFrequentCountry;
import dl.model.ExtractedArticle;
import dl.model.MostFrequentFile;
import dl.extractor.Extractor;
import dl.parser.Article;
import dl.parser.ArticlesLoader;

import java.io.IOException;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws IOException {
        ArticlesLoader articlesLoader = new ArticlesLoader();

        Extractor extractor = new Extractor();
        TotalWordsNumber totalWordsNumber = new TotalWordsNumber(500 /*
        Initial
        parsing
        should
        provide
        this
        information
        */
        );
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
        UniqueWordsNumber uniqueWordsNumber = new UniqueWordsNumber(500/*
        Initial
        parsing
        should
        provide
        this
        information
        */
        );

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
            extractedArticles.add(new ExtractedArticle(extractor.getFeaturesValues(), a.getPlaces()));
        }

        ArrayList<ArrayList> splitData = DatasetSplitter.splitData(extractedArticles, 60);
        ArrayList<ExtractedArticle> classifiedSet = splitData.get(0);
        ArrayList<ExtractedArticle> learningSet = splitData.get(1);

        Knn knn = new Knn(15,classifiedSet,extractor);

        for (int i = 0; i < learningSet.size(); i++) {
            ExtractedArticle extractedArticle = learningSet.get(i);
            //System.out.println(extractedArticle);
            ArrayList<ExtractedArticle> kNearestNeighbours = knn.findKNearestNeighbours(extractedArticle);
            String mostFrequentCountry = MostFrequentCountry.getMostFrequentCountry(kNearestNeighbours);
            System.out.println("Real country: " + extractedArticle.getCountry() + " classified country: " + mostFrequentCountry);
        }
    }
}
