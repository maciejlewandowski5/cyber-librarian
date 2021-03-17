package dl.knn;

import dl.model.ExtractedArticle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DatasetSplitter {
    public static ArrayList<ArrayList> splitData(ArrayList<ExtractedArticle> articleList, int trainingSetPercentage) {
        ArrayList<ArrayList> data = new ArrayList<>();
        Collections.shuffle(articleList);
        int trainingElements = (trainingSetPercentage * articleList.size()) / 100;
        data.add(new ArrayList(articleList.subList(0, trainingElements)));
        data.add(new ArrayList(articleList.subList(trainingElements, articleList.size())));
        return data;
    }
}
