package dl.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class ArticlesLoader {
    private File[] files;
    private List<Document> docs;
    private Set<Article> articles;

    public ArticlesLoader() throws IOException {
        File folder = new File(".\\res\\reuters21578\\articles");
        this.files = folder.listFiles();
        loadFiles();
        parseArticles();
//        System.out.println(docs.get(0));
    }

    private void loadFiles() throws IOException {
        docs = new LinkedList<>();
        for (File file : files) {
            docs.add(Jsoup.parse(file, "UTF-8"));
        }
    }

    private void parseArticles() {
        articles = new HashSet<>();
        for (Document doc : docs) {
            for (Element element : doc.select("REUTERS")) {
                System.out.println(element.select("TITLE"));
            }
        }
    }
}
