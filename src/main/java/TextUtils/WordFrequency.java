package TextUtils;

import java.util.*;

public class WordFrequency {

    private static final DefaultTokenizer tokenizer = new DefaultTokenizer();
    private static DefaultStopWordsProvider stopWordsProvider = new DefaultStopWordsProvider("data/stopwords.txt");

    public static Map<String, Integer> getWordFrequency(String input) {
        Map<String, Integer> ret = new TreeMap<>();
        String[] words = tokenizer.tokenize(input);
        for(String word:words) {
            if (!stopWordsProvider.isStopWord(word)) {
                Integer freq = ret.get(word);
                ret.put(word, freq == null ? 1 : freq + 1);
            }
        }
        return ret;
    }

    public static String[] getMostFrequentWords(int count, Map<String, Integer>  wordFrequencies) {
        List<String> result = new ArrayList<>();
        int cnt = 0;
        for(String word:wordFrequencies.keySet()){
            result.add(word);
            cnt++;
            if(cnt>=count) break;
        }
        return result.toArray(new String[0]);
    }
}
