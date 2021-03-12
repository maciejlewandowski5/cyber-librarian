package dl;

import dl.features.MostFrequent;
import dl.model.MostFrequentFile;
import dl.parser.Article;
import dl.parser.ArticlesLoader;

import java.io.IOException;

public class App 
{
    public static void main( String[] args ) throws IOException {
        ArticlesLoader articlesLoader = new ArticlesLoader();
        for (Article a : articlesLoader.getArticles()) {
            System.out.println(a.toString());
        }
        Article article = articlesLoader.getArticles().get(0);

        MostFrequent mostFrequentMonth = new MostFrequent(article.getBody(), MostFrequentFile.MONTHS);
        String highest = mostFrequentMonth.getHighest();
        System.out.println();
    }
}
