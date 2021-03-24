package dl.metrics;

public class TriGram {
    public static double calculate(String string1, String string2) {
        double N = Math.max(string1.length(), string2.length());
        double triGrams = 0;
        for (int i = 0; i <= string1.length() - 3; i++) {
            String gram = string1.substring(i, i+3);
            if (string2.contains(gram)) {
                triGrams++;
            }
        }
        return triGrams / (N - 2);
    }
}
