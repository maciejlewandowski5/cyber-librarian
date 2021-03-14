package dl.extractor.features;

import dl.model.MostFrequentFile;
import dl.parser.Article;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class MostFrequentPhraseTest {

    @Test
    public void extract() {
        String text = "lrs a barrel can be\n" +
                "crucial in determining if the well remains in production.\n" +
                "    Nugent also called for state lawmakers to exempt new\n" +
                "wildcat wells from the state severance tax for up to five years\n" +
                "as a financial incentive to explore for new oil reserves.\n" +
                "    Secondary and tertiary oil production, expensive methods of\n" +
                "production that inject water or gas into the ground to recover\n" +
                "oil, should also be exempted from the severance tax, Nugent\n" +
                "said. His plan would exempt existing secondary and tertiary\n" +
                "wells that produce at a rate of less than three barrels a day\n" +
                "for three years, or until the price of oil reaches $25 a\n" +
                "barrel.\n" +
                "    \"We've been sitting back and waiting on two federal\n" +
                "administrations to develop a coherent energy policy for the\n" +
                "nation to follow. I coral sea we have waited long enough,\" Nugent";
        Article newArticle = new Article("Coral Sea", "", Arrays.asList(text));
        MostFrequentPhrase mostFrequentPhrase = new MostFrequentPhrase(MostFrequentFile.SEAS);
        mostFrequentPhrase.extract(newArticle);
        assertEquals(9, mostFrequentPhrase.getFeature());
    }
}