package dl;

import dl.extractor.Extractor;
import dl.extractor.features.TotalWordsNumber;
import dl.parser.Article;
import dl.parser.ArticlesLoader;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
        ArticlesLoader articlesLoader = new ArticlesLoader();

        for (Article a : articlesLoader.getArticles()) {
            Extractor extractor = new Extractor();
            TotalWordsNumber totalWordsNumber = new TotalWordsNumber();
            extractor.addFeature(totalWordsNumber);
            extractor.extract(a);
            System.out.println(extractor.getFeature(0));
        }

    }
}
