package dl.metrics;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class AccuracyTest {
    @Test
    public void calculateAccTest() {
        String[] expected = {"test", "teste", "tester"};
        String[] actual = {"test", "tester", "testero"};

        double v = Metrics.calculateAccuracy(Arrays.asList(expected), Arrays.asList(actual));
        assertEquals(0.33, v, 0.01);
    }
}