package VectorBooleanOperators;

import Vector.VectorClassifier;

import java.util.HashMap;
import java.util.Map;

public class LessThanOrEqual {

    // x less than or equal to y
    // Returns max if x <= y, min otherwise
    public Map<String, Integer> calculate(VectorClassifier x, VectorClassifier y, VectorClassifier z){
        Map<String, Integer> ret = new HashMap<>();

        Map<String, Integer> xmap = x.getWordFrequency();
        Map<String, Integer> ymap = y.getWordFrequency();
        Map<String, Integer> zmap = z.getWordFrequency();

        for(String keyx:xmap.keySet()){

            if(ymap.containsKey(keyx)){
                int freqx = xmap.get(keyx);
                int freqy = ymap.get(keyx);
                int freqz = zmap.get(keyx);

                int min = Math.min(freqx,Math.min(freqy,freqz));
                int max = Math.max(freqx,Math.max(freqy,freqz));

                int freqr = freqx<=freqy?max:min;
                ret.put(keyx,freqr);
            }

        }
        return ret;
    }


}
