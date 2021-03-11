package dl.extractor.features;

import dl.extractor.Feature;
import dl.parser.Article;
import opennlp.tools.stemmer.PorterStemmer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class UnitType implements Feature {

    int numberOfSiUnits;
    int numberOfImperialUnits;
    Set<String> imperial;
    Set<String> si;
    ArrayList<String> prefixesForSi;
    private static PorterStemmer porterStemmer;


    public UnitType() {
        porterStemmer = new PorterStemmer();
        imperial = new HashSet<>(Arrays.asList(
                "pound",
                "foot",
                "inch",
                "yard",
                "mile",
                "gallon",
                "ounce",
                "pounds",
                "inches",
                "yards",
                "miles",
                "gallons",
                "ounces"));
        Set<String> tmp = new HashSet<>();
        for(String word :imperial){
            tmp.add(porterStemmer.stem(word));
        }
        imperial = tmp;
        si = new HashSet<>(Arrays.asList(
                "tonne",
                "litre",
                "kilogram",
                "meter"));
        tmp = new HashSet<>();
        for(String word :si){
            tmp.add(porterStemmer.stem(word));
        }
        si = tmp;
        prefixesForSi = new ArrayList<>(Arrays.asList(
                "tera",
                "giga",
                "mega",
                "kilo",
                "hecto",
                "deca",
                "",
                "deci",
                "centi",
                "mili",
                "micro",
                "nano",
                "pico"
        ));
        numberOfImperialUnits =0;
        numberOfSiUnits=0;
    }

    @Override
    public void extract(Article article) {
        article.getBody().forEach(word -> {
            if(imperial.contains(word)){
                numberOfImperialUnits++;
            }else{
                prefixesForSi.forEach(prefix -> {
                    si.forEach(unit -> {
                        if(word.equals(prefix+unit)){
                            numberOfSiUnits++;
                        }
                    });
                });
            }
        });
    }



    @Override
    public Float getFeature() {
        if(numberOfSiUnits>numberOfImperialUnits){
            return 1f;
        }else if(numberOfSiUnits<numberOfImperialUnits){
            return 0f;
        }else {
            return 0.5f;
        }
    }
}
