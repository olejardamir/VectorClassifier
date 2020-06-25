package Vector;

import org.simmetrics.StringMetric;
import org.simmetrics.metrics.JaroWinkler;

import java.io.Serializable;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class TermVector implements Serializable {
    private final String[] terms;
    private final int[] values;
    private final StringMetric stringMetric = new JaroWinkler();

    public TermVector(String[] terms, int[] values) {
        this.terms = terms;
        this.values = values;
    }

    String[] getRelevantTerms(Map<String, Integer> wordFrequency){

        if(terms.length<=wordFrequency.size()){return terms;}

        Map<Double, String> tmpMap = new TreeMap<>(Collections.reverseOrder());
        for(String term:terms){
            double sum = 0;
            for(String freq:wordFrequency.keySet()){
                double value = stringMetric.compare(term,freq);
                sum = sum+value;
            }
            while(tmpMap.containsKey(sum)) {
                sum = sum-0.0000000001; //just a very small number, to simplify the sorting of a Map
            }
            tmpMap.put(sum, term);
        }

        String[] ret = new String[wordFrequency.size()];
        int cnt = 0;
        for(Double key:tmpMap.keySet()){
            String value = tmpMap.get(key);
            ret[cnt] = value;
            cnt++;
            if (cnt==wordFrequency.size()){break;}
        }

        return ret;
    }

    int[] getValues() {
        return values.clone();
    }
}