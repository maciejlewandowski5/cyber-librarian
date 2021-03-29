package dl.cli;

import dl.extractor.Feature;
import dl.extractor.features.*;
import dl.model.MostFrequentFile;

import java.util.ArrayList;

public class CLI {
    static ArrayList<Feature> features = new ArrayList<>();
    static ArrayList<String> countries = new ArrayList<>();
    static int KNeighbors;
    static int learningSetPercentage;

    public static int getLearningSetPercentage() {
        return learningSetPercentage;
    }

    public static void setLearningSetPercentage(int learningSetPercentage) {
        CLI.learningSetPercentage = learningSetPercentage;
    }

    public static int getKNeighbors() {
        return KNeighbors;
    }

    public static void setKNeighbors(int KNeighbors) {
        CLI.KNeighbors = KNeighbors;
    }

    public static void addCountry(int selected) {
        switch (selected) {
            case 0:
                countries.add("usa");
                countries.add("uk");
                countries.add("japan");
                countries.add("canada");
                countries.add("west-germany");
                countries.add("france");
                break;
            case 1:
                countries.add("usa");
                break;
            case 2:
                countries.add("uk");
                break;
            case 3:
                countries.add("japan");
                break;
            case 4:
                countries.add("canada");
                break;
            case 5:
                countries.add("west-germany");
                break;
            case 6:
                countries.add("france");
                break;
        }
    }

    public static void addFeature(int selected) {
        switch (selected) {
            case 0:
                features.add(new TotalWordsNumber(500/*
        Initial
        parsing
        should
        provide
        this
        information
        */));
                features.add(new UnitType());
                features.add(new NLengthCharCounter(0,4));
                features.add(new NLengthCharCounter(5,8));
                features.add(new NLengthCharCounter(9, Integer.MAX_VALUE));
                features.add(new MostCommonYear());
                features.add(new MostFrequentWord(MostFrequentFile.MONTHS));
                features.add(new MostFrequentWord(MostFrequentFile.BUSINESS_ENTITY));
                features.add(new MostFrequentPhrase(MostFrequentFile.STOCK_EXCHANGE));
                features.add(new MostFrequentPhrase(MostFrequentFile.SEAS));
                features.add(new NumberOfPrices());
                features.add(new UniqueWordsNumber(500/*
        Initial
        parsing
        should
        provide
        this
        information
        */));
                break;
            case 1:
                features.add(new TotalWordsNumber(500/*
        Initial
        parsing
        should
        provide
        this
        information
        */));
                break;
            case 2:
                features.add(new UnitType());
                break;
            case 3:
                features.add(new NLengthCharCounter(0,4));
                break;
            case 4:
                features.add(new NLengthCharCounter(5,8));
                break;
            case 5:
                features.add(new NLengthCharCounter(9, Integer.MAX_VALUE));
                break;
            case 6:
                features.add(new MostCommonYear());
                break;
            case 7:
                features.add(new MostFrequentWord(MostFrequentFile.MONTHS));
                break;
            case 8:
                features.add(new MostFrequentWord(MostFrequentFile.BUSINESS_ENTITY));
                break;
            case 9:
                features.add(new MostFrequentPhrase(MostFrequentFile.STOCK_EXCHANGE));
                break;
            case 10:
                features.add(new MostFrequentPhrase(MostFrequentFile.SEAS));
                break;
            case 11:
                features.add(new NumberOfPrices());
                break;
            case 12:
                features.add(new UniqueWordsNumber(500/*
        Initial
        parsing
        should
        provide
        this
        information
        */));
                break;
        }
    }



    public static ArrayList<Feature> getFeatures() {
        return features;
    }

    public static ArrayList<String> getCountries() {
        return countries;
    }
}
