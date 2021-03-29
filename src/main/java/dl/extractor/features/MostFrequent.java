package dl.extractor.features;

import dl.extractor.Feature;
import dl.model.MostFrequentFile;

import java.util.Collections;
import java.util.Map;

public abstract class MostFrequent implements Feature {
    protected Map<String, Integer> map;

    protected String checkFilename(MostFrequentFile mostFrequentFile) {
        switch (mostFrequentFile) {
            case SEAS:
                return "seas.txt";
            case STOCK_EXCHANGE:
                return "stockExchange.txt";
            case MONTHS:
                return "months.txt";
            case BUSINESS_ENTITY:
                return "businessEntity.txt";
        }
        return "";
    }

    @Override
    public String getFeature() {
        if (map.isEmpty()){
            return "";
        }
        String key = Collections.max(map.entrySet(), (entry1, entry2) -> entry1.getValue() - entry2.getValue()).getKey();
        if (map.get(key) > 0) {
            return key;
        } else {
            return "";
        }
    }

    @Override
    public double getNormalizeCoefficient() {
        return  1;
    }

    @Override
    public void clear() {
        map.forEach((k,v) ->map.put(k,0));
    }

    @Override
    public double preEuclideanDistance(Object object1, Object object2) {
        return ((String)object1).equals((String)object2)?1.0d:0.0;
    }
    @Override
    public double preTaxiCabGeometryDistance(Object object1, Object object2) {
        return preEuclideanDistance(object1,object2);
    }

    @Override
    public double preCousinsAmplitudeNominatorDistance(Object object1, Object object2) {
        return preEuclideanDistance(object1,object2);
    }

    @Override
    public double preCousinsAmplitudeDenominatorDistance(Object object1) {
        return 1.0d;
    }


}
