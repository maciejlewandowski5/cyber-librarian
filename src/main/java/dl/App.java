package dl;

import dl.extractor.Extractor;
import dl.extractor.features.TotalWordsNumber;
import dl.extractor.features.UnitType;
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
            System.out.println(a);
            Extractor extractor = new Extractor();
            TotalWordsNumber totalWordsNumber = new TotalWordsNumber();
            UnitType unitType = new UnitType();
            extractor.addFeature(totalWordsNumber);
            extractor.addFeature(unitType);
            extractor.extract(a);
            if(extractor.getFeature(1)!=null){
            System.out.println(extractor.getFeature(1));
            };
        }

    }
}
