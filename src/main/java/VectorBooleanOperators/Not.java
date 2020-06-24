package VectorBooleanOperators;

import Vector.VectorClassifier;

import java.util.HashMap;
import java.util.Map;

public class Not {

    // Not x given z
    // Returns min if x is greater than min, max otherwise
    public Map<String, Integer> calculate(VectorClassifier x, VectorClassifier z){
        Map<String, Integer> ret = new HashMap<>();

        Map<String, Integer> xmap = x.getWordFrequency();
        Map<String, Integer> zmap = z.getWordFrequency();

        for(String keyx:xmap.keySet()){

            if(zmap.containsKey(keyx)){
                int freqx = xmap.get(keyx);
                int freqz = zmap.get(keyx);

                int min = Math.min(freqx,freqz);
                int max = Math.max(freqx,freqz);

                int freqr = freqx>min?min:max;
                ret.put(keyx,freqr);
            }

        }
        return ret;
    }



}
