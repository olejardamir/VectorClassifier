package VectorBooleanOperators;

import Vector.VectorClassifier;

import java.util.HashMap;
import java.util.Map;

public class Xor {

    // x XOR y given z
    // Returns max if (x <= min and y > min) or (x > min and y <= min), min otherwise
    public Map<String, Integer> calculate(VectorClassifier x, VectorClassifier y, VectorClassifier z){
        Map<String, Integer> ret = new HashMap<>();

        Map<String, Integer> xmap = x.getWordFrequency();
        Map<String, Integer> ymap = y.getWordFrequency();
        Map<String, Integer> zmap = z.getWordFrequency();

        for(String keyx:xmap.keySet()){

            if(ymap.containsKey(keyx) && zmap.containsKey(keyx)){
                int freqx = xmap.get(keyx);
                int freqy = ymap.get(keyx);
                int freqz = zmap.get(keyx);

                int min = Math.min(freqx,Math.min(freqy,freqz));
                int max = Math.max(freqx,Math.max(freqy,freqz));

                int freqr = (freqx <= min && freqy > min) || (freqx > min && freqy <= min)? max:min;
                ret.put(keyx,freqr);
            }


        }
        return ret;
    }



}
