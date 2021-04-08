package dl.knn;

import dl.model.ExtractedArticle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class DatasetSplitter {
    public static ArrayList<ArrayList> splitData(ArrayList<ExtractedArticle> articleList, int trainingSetPercentage,
                                                 int kNeighbors, ArrayList<String> countries) {
        HashMap knnValidationMap = new HashMap<>();
        countries.forEach(country -> {
            knnValidationMap.put(country, 0);
        });

        ArrayList<ArrayList> data = new ArrayList<>();
        Collections.shuffle(articleList);
        int trainingElements = (trainingSetPercentage * articleList.size()) / 100;

        ArrayList<ExtractedArticle> trainingList = new ArrayList(articleList.subList(0, trainingElements));
        for (int i = 0; i < trainingList.size(); i++) {
            String country = trainingList.get(i).getCountry();
            if (countries.contains(country)) {
                knnValidationMap.put(country, (int) knnValidationMap.get(country)+1);
            }
        }

        countries.forEach(country -> {
            if ((int) knnValidationMap.get(country) < kNeighbors) {
                try {
                    throw new Exception("Invalid training data. Please increase training data range. Not enough" +
                            " data for: " + country);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };
        });


        data.add(trainingList);
        data.add(new ArrayList(articleList.subList(trainingElements, articleList.size())));
        return data;
    }
}
