package dl;

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
            System.out.println(a.toString());
        }
    }
}
