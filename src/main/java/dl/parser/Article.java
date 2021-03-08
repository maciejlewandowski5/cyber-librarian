package dl.parser;

import java.util.List;

public class Article {
    private String title;
    private String places;
    private String body;

    public Article(String title, String places, String body) {
        this.title = title;
        this.places = places;
        this.body = body;
    }

    @Override
    public String toString() {
        return "Article{" +
                "title='" + title + '\'' +
                ", places=" + places +
                ", body='" + body + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public String getPlaces() {
        return places;
    }

    public String getBody() {
        return body;
    }
}
