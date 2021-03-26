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

    public static double calculateF1(List<String> expected, List<String> actual, List<String> countries) {
        double PPV = calculatePrecision(expected, actual, countries);
        double TPR = calculateRecall(expected, actual, countries);
        return 2 * ((PPV * TPR) / (PPV + TPR));
    }

    public static double calculatePrecision (List<String> expected, List<String> actual, List<String> countries) {
        double truePositives = 0;
        double falsePositives = 0;
        for (String country : countries) {
            for (int i = 0; i < expected.size(); i++) {
                if (expected.get(i).equals(country)) {
                    if (actual.get(i).equals(country)) {
                        truePositives++;
                    } else {
                        falsePositives++;
                    }
                }
            }
        }
        return truePositives / (truePositives + falsePositives);
    }

    public static double calculateRecall (List<String> expected, List<String> actual, List<String> countries) {
        double truePositives = 0;
        double falseNegatives = 0;
        for (String country : countries) {
            for (int i = 0; i < expected.size(); i++) {
                if (actual.get(i).equals(country)) {
                    if (expected.get(i).equals(country)) {
                        truePositives++;
                    } else {
                        falseNegatives++;
                    }
                }
            }
        }
        return truePositives / (truePositives + falseNegatives);
    }
}
