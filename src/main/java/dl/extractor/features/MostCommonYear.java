package dl.extractor.features;

import dl.extractor.Feature;
import dl.parser.Article;

import java.util.*;

public class MostCommonYear implements Feature {

    Map<String, Integer> years;

    public MostCommonYear() {
        years = new HashMap<>();
    }

    @Override
    public void extract(Article article) {
        article.getBody().forEach(world -> {
            if (world.matches("18\\d{2}|19\\d{2}|20\\d{2}")) {
                Integer oldCount = years.get(world);
                if (oldCount == null) {
                    oldCount = 0;
                }
                years.put(world, oldCount + 1);
            }

        });
    }

    @Override
    public Integer getFeature() {
        if (years.isEmpty()){
            return 0;
        }
        return Integer.valueOf(Collections.max(years.entrySet(),
                (o1, o2) -> o1.getValue().compareTo(o2.getValue())).
                getKey());
    }

    @Override
    public void clear() {
        years = new HashMap<>();
    }

    @Override
    public double distance(Object object1, Object object2) {
        return ((Integer)object1 - (Integer)object2)*((Integer)object1 - (Integer)object2);
    }
}
