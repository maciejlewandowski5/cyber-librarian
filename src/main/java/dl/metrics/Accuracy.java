package dl.metrics;

import java.util.List;

public class Accuracy {
    public static double calculateAccuracy(List<String> expected, List<String> actual) {
        double match = 0;
        for(int i = 0; i < expected.size(); i++) {
            if (expected.get(i).equals(actual.get(i))) {
                match++;
            }
        }
        return match / expected.size();
    }
}
