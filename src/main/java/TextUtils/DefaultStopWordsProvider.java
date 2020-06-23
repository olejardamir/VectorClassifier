package TextUtils;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class DefaultStopWordsProvider   {
     private final List<String> stopWords;

    public DefaultStopWordsProvider(String s) {
        stopWords = new ArrayList<>();
        loadStopWords(s);
        Arrays.sort(new ArrayList[]{(ArrayList) stopWords});
    }

    private void loadStopWords(String s) {
        try {
            BufferedReader read  = new BufferedReader(new FileReader(s));

        String l;
        while((l=read.readLine())!=null){
            l=l.toLowerCase().trim();
            if(!stopWords.contains(l))
                stopWords.add(l);
        }
        read.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isStopWord(String word) {
        return stopWords.contains(word);
    }
}
