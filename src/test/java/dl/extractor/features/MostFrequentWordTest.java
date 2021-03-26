package dl.extractor.features;

import dl.model.MostFrequentFile;
import dl.parser.Article;
import org.junit.Test;

import java.util.ArrayList;

public class MostFrequentWordTest {
    @Test
    public void test() {
        MostFrequentWord mostFrequentWord = new MostFrequentWord(MostFrequentFile.MONTHS);
        ArrayList body = new ArrayList(){
            {
                add("janar");
                add("januar");
                add("janary");
                add("janare");
                add("ragreag");
            }
        };
        Article article = new Article("test", "usa", body);
        mostFrequentWord.extract(article);
    }
}