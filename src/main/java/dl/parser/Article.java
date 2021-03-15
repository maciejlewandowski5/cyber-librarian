package dl.parser;

import java.util.List;

public class Article {
    private String title;
    private String places;
    private List<String> body;
    private String preprocessedBody;

    public Article(String title, String places, List<String> body) {
        this.title = title;
        this.places = places;
        this.body = body;
    }

    public Article(String title, String places, List<String> body, String preprocessedBody) {
        this.title = title;
        this.places = places;
        this.body = body;
        this.preprocessedBody = preprocessedBody;
    }

    @Override
    public String toString() {
        return "Article{" +
                "title='" + title + '\'' +
                ", places='" + places + '\'' +
                ", body=" + body +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public String getPlaces() {
        return places;
    }

    public List<String> getBody() {
        return body;
    }

    public String getPreProcessedBody() {
        return preprocessedBody;
    }
}
