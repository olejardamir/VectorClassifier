package VectorBooleanOperators;

import Vector.VectorClassifier;

import java.util.HashMap;
import java.util.Map;

public class IfThenElse {

    // If x then y else z
    // Returns y if x is greater than min, z otherwise
    public Map<String, Integer> calculate(VectorClassifier x, VectorClassifier y, VectorClassifier z){
        Map<String, Integer> ret = new HashMap<>();

        Map<String, Integer> xmap = x.getWordFrequency();
        Map<String, Integer> ymap = y.getWordFrequency();
        Map<String, Integer> zmap = z.getWordFrequency();

        for(String keyx:xmap.keySet()){

            if(zmap.containsKey(keyx) && ymap.containsKey(keyx)){
                int freqx = xmap.get(keyx);
                int freqy = ymap.get(keyx);
                int freqz = zmap.get(keyx);

                int min = Math.min(freqx,Math.min(freqy,freqz));

                int freqr = freqx>min?freqy:freqz;
                ret.put(keyx,freqr);
            }

        }
        return ret;
    }

}
