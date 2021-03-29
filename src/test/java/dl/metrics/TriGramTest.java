package dl.metrics;

import org.junit.Test;

import static org.junit.Assert.*;

public class TriGramTest {

    @Test
    public void calculate() {
        double calculate = TriGram.calculate("tester", "teste");
        assertEquals(0.75, calculate, 0);
    }
}