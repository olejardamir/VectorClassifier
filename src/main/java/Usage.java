import TextUtils.DefaultStopWordsProvider;
import Vector.HashMapTermVectorStorage;
import Vector.VectorClassifier;

public class Usage {
    public static void main(String[] args) throws Exception {
        HashMapTermVectorStorage storage = new HashMapTermVectorStorage();
        DefaultStopWordsProvider defaultStopWordsProvider = new DefaultStopWordsProvider("data/stopwords.txt"); //we load the stopwords only once!
        VectorClassifier vc = new VectorClassifier(storage, defaultStopWordsProvider);
        vc.teachMatch("category", "hello there is this a long sentence yes it is blah blah hello.",25);
        double result = vc.classify("category", "hello blah");
        System.out.println(result);

    }

}
