package dl.knn;

import dl.model.ExtractedArticle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DatasetSplitter {
    public static ArrayList<List> splitData(ArrayList<ExtractedArticle> articleList, int trainingSetPercentage) {
        ArrayList<List> data = new ArrayList<>();
        Collections.shuffle(articleList);
        int trainingElements = (trainingSetPercentage * articleList.size()) / 100;
        data.add(articleList.subList(0, trainingElements));
        data.add(articleList.subList(trainingElements, articleList.size()));
        return data;
    }
}
