package dl.extractor.features;

import dl.extractor.Feature;
import dl.parser.Article;

import java.util.*;

public class MostCommonYear implements Feature {

    Map<String, Integer> years;
    private static final int MAX_YEAR = 2099;
    private static final int MIN_YEAR = 0;

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
    public double getNormalizeCoefficient() {
        return  1/(double)(MAX_YEAR-MIN_YEAR);
    }

    @Override
    public double preEuclideanDistance(Object object1, Object object2) {
        return getNormalizeCoefficient()*((Integer)object1 - (Integer)object2)*((Integer)object1 - (Integer)object2);
    }

    @Override
    public double preTaxiCabGeometryDistance(Object object1, Object object2) {
        return Math.abs(getNormalizeCoefficient() * ((Integer)object1 - (Integer)object2));
    }

    @Override
    public double preCousinsAmplitudeNominatorDistance(Object object1, Object object2) {
        return getNormalizeCoefficient()*((Integer)object1 * (Integer)object2);
    }

    @Override
    public double preCousinsAmplitudeDenominatorDistance(Object object1) {
        return (Integer)object1*(Integer)object1;
    }
}
