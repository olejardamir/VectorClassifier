package TextUtils;


 import java.io.BufferedReader;
 import java.io.FileReader;
 import java.io.IOException;
 import java.util.ArrayList;
 import java.util.Arrays;

public class DefaultStopWordsProvider   {
     private ArrayList<String> stopWords;

    public DefaultStopWordsProvider(String s) throws Exception {
        stopWords = new ArrayList<>();
        loadStopWords(s);
        Arrays.sort(new ArrayList[]{stopWords});
    }

    private void loadStopWords(String s) throws IOException {
        BufferedReader read = new BufferedReader(new FileReader(s));
        String l = "";
        while((l=read.readLine())!=null){
            l=l.toLowerCase().trim();
            if(!stopWords.contains(l))
                stopWords.add(l);
        }
        read.close();
    }

    public boolean isStopWord(String word) {
        return stopWords.contains(word); //we already convert input to lowercase.
    }



}
