package dl.metrics;

import java.util.List;

public class Metrics {
    public static double calculateAccuracy(List<String> expected, List<String> actual) {
        double truePositives = 0;
        for(int i = 0; i < expected.size(); i++) {
            if (expected.get(i).equals(actual.get(i))) {
                truePositives++;
            }
        }
        return truePositives / expected.size();
    }

    public static double calculateF1(List<String> expected, List<String> actual, String country) {
        double PPV = calculatePrecision(expected, actual, country);
        double TPR = calculateRecall(expected, actual, country);
        if (PPV + TPR != 0) {
            return 2 * ((PPV * TPR) / (PPV + TPR));
        }
        return 0;
    }

    public static double calculateWeightedF1(List<String> expected, List<String> actual, String country) {
        double PPV = calculateWeightedPrecision(expected, actual, country);
        double TPR = calculateWeightedRecall(expected, actual, country);
        if (PPV + TPR != 0) {
            return 2 * ((PPV * TPR) / (PPV + TPR));
        }
        return 0;
    }

    public static double calculatePrecision (List<String> expected, List<String> actual, String country) {
        double truePositives = 0;
        double falsePositives = 0;
            for (int i = 0; i < expected.size(); i++) {
                if (expected.get(i).equals(country)) {
                    if (actual.get(i).equals(country)) {
                        truePositives++;
                    } else {
                        falsePositives++;
                    }
                }
            }
            if(truePositives == 0 && falsePositives==0){
                truePositives = 0.0001;
                falsePositives = 0.0001;
            }
        return truePositives / (truePositives + falsePositives);
    }

    public static double calculateWeightedPrecision (List<String> expected, List<String> actual, String country) {
        double weight = 0;
        double truePositives = 0;
        double falsePositives = 0;
            for (int i = 0; i < expected.size(); i++) {
                if (expected.get(i).equals(country)) {
                    weight++;
                    if (actual.get(i).equals(country)) {
                        truePositives++;
                    } else {
                        falsePositives++;
                    }
                }
            }
            if(truePositives == 0 && falsePositives==0){
                truePositives = 0.0001;
                falsePositives = 0.0001;
            }
        return (truePositives / (truePositives + falsePositives)) * weight;
    }

    public static double calculateRecall (List<String> expected, List<String> actual, String country) {
        double truePositives = 0;
        double falseNegatives = 0;
            for (int i = 0; i < expected.size(); i++) {
                if (actual.get(i).equals(country)) {
                    if (expected.get(i).equals(country)) {
                        truePositives++;
                    } else {
                        falseNegatives++;
                    }
                }
            }
        return truePositives / (truePositives + falseNegatives);
    }

    public static double calculateWeightedRecall (List<String> expected, List<String> actual, String country) {
        double weight = 0;
        double truePositives = 0;
        double falseNegatives = 0;
        for (int i = 0; i < expected.size(); i++) {
            if (actual.get(i).equals(country)) {
                weight++;
                if (expected.get(i).equals(country)) {
                    truePositives++;
                } else {
                    falseNegatives++;
                }
            }
        }
        return (truePositives / (truePositives + falseNegatives)) * weight;
    }
}
