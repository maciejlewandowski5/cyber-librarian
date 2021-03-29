package dl;

import dl.cli.CLI;
import dl.extractor.features.*;
import dl.knn.DatasetSplitter;
import dl.knn.Knn;
import dl.metrics.Metrics;
import dl.model.MostFrequentFile;
import dl.utils.MostFrequentCountry;
import dl.model.ExtractedArticle;
import dl.extractor.Extractor;
import dl.parser.Article;
import dl.parser.ArticlesLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        String input;
        ArticlesLoader articlesLoader = new ArticlesLoader();
        Extractor extractor = new Extractor();



        System.out.println("Okresl % zbioru treningowego:");
        while (true) {
            input = scan.nextLine();
            int selected;
            try {
                selected = Integer.parseInt(input);
            } catch (NumberFormatException e) { break; }
            if (selected >= 1 && selected <= 99) {
                CLI.setLearningSetPercentage(selected);
                break;
            }
        }

        System.out.println("Okresl ilosc sasiadow KNN:");
        while (true) {
            input = scan.nextLine();
            int selected;
            try {
                selected = Integer.parseInt(input);
            } catch (NumberFormatException e) { break; }
            if (selected >= 1 && selected <= 1000) {
                CLI.setKNeighbors(selected);
                break;
            }
        }

        System.out.println("Wybierz interesujące kraje: \n" +
                "0. Wszystkie\n" +
                "1. USA\n" +
                "2. UK\n" +
                "3. Japan\n" +
                "4. Canada\n" +
                "5. West-Germany\n" +
                "6. France\n" +
                "Y. Przejdz dalej");
        while (true) {
            input = scan.nextLine();
            int selected;
            try {
                selected = Integer.parseInt(input);
            } catch (NumberFormatException e) { break; }
            if (selected >= 0 && selected <= 6) {
                CLI.addCountry(selected);
                if (input.equals("0")) {
                    break;
                }
            } else {
                break;
            }
        }

        System.out.println("Wybierz interesujące metryki: \n" +
                "0. Wszystkie\n" +
                "1. TotalWordsNumber\n" +
                "2. unitType\n" +
                "3. shortWords\n" +
                "4. middleWords\n" +
                "5. longWords\n" +
                "6. mostCommonYear\n" +
                "7. mostFrequentWordMonth\n" +
                "8. mostFrequentWordBusinessEntity\n" +
                "9. mostFrequentStockExchange\n" +
                "10. mostFrequentSea\n" +
                "11. numberOfPrices\n" +
                "12. uniqueWordsNumber\n" +
                "Y. Przejdz dalej");
        while (true) {
            input = scan.nextLine();
            int selected;
            try {
                selected = Integer.parseInt(input);
            } catch (NumberFormatException e) { break; }
            if (selected >= 0 && selected <= 12) {
                CLI.addFeature(selected);
                if (input.equals("0")) {
                    break;
                }
            } else {
                break;
            }
        }
        System.out.println("loading...");
        extractor.addFeatures(CLI.getFeatures());

        if (CLI.getFeatures().isEmpty() || CLI.getCountries().isEmpty()) {
            return;
        }


        ArrayList<ExtractedArticle> extractedArticles = new ArrayList<>();

        for (Article a : articlesLoader.getArticles()) {
            extractor.clear();
            extractor.extract(a);
            extractedArticles.add(new ExtractedArticle(extractor.getFeaturesValues(), a.getPlaces()));
        }

        ArrayList<ArrayList> splitData = DatasetSplitter.splitData(extractedArticles, CLI.getLearningSetPercentage());
        ArrayList<ExtractedArticle> classifiedSet = splitData.get(0);
        ArrayList<ExtractedArticle> learningSet = splitData.get(1);

        Knn knn = new Knn(CLI.getKNeighbors(),classifiedSet,extractor);

        ArrayList<String> expected = new ArrayList<>();
        ArrayList<String> actual = new ArrayList<>();

        for (int i = 0; i < learningSet.size(); i++) {
            ExtractedArticle extractedArticle = learningSet.get(i);
            ArrayList<ExtractedArticle> kNearestNeighbours = knn.findKNearestNeighbours(extractedArticle);
            String mostFrequentCountry = MostFrequentCountry.getMostFrequentCountry(kNearestNeighbours);

            expected.add(mostFrequentCountry);
            actual.add(extractedArticle.getCountry());

        //    System.out.println("Real country: " + extractedArticle.getCountry() + "; classified country: " + mostFrequentCountry);
        }

        ArrayList<String> countries = CLI.getCountries();
        countries.forEach(country -> {
            System.out.println("\n");
            System.out.println("Dla " + country + " _________");
            double acc = Metrics.calculateAccuracy(expected, actual);
            double rec = Metrics.calculateRecall(expected, actual, country);
            double prec = Metrics.calculatePrecision(expected, actual, country);
            double f1 = Metrics.calculateF1(expected, actual, country);
            System.out.println("Dokladnosc: " + acc);
            System.out.println("Precyzja: " + prec);
            System.out.println("Czulosc: " + rec);
            System.out.println("F1: " + f1);
        });

    }
}
