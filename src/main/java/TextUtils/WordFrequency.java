package TextUtils;

import java.util.*;

public class WordFrequency {

    public static Map<String, Integer> getWordFrequency(String input, DefaultTokenizer tokenizer, DefaultStopWordsProvider stopWordsProvider) {
        Map<String, Integer> ret = new TreeMap<>(Collections.reverseOrder());
        String[] words = tokenizer.tokenize(input);
        for(String word:words){
            if(!stopWordsProvider.isStopWord(word)) {
                Integer freq = ret.get(word);
                ret.put(word, freq == null ? 1 : freq + 1);
            }
        }
        return ret;
    }

    public static Set<String> getMostFrequentWords(int count, Map<String, Integer>  wordFrequencies) {
        Set<String> result = new LinkedHashSet<>();
        int cnt = 0;
        for(String word:wordFrequencies.keySet()){
            result.add(word);
            cnt++;
            if(cnt>=count) break;
        }
        return result;
    }


}
