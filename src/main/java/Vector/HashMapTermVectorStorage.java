package Vector;


import java.util.HashMap;
import java.util.Map;

public class HashMapTermVectorStorage {
    private final Map<String, TermVector> storage = new HashMap<>();
    public void addTermVector(String category, TermVector termVector) {
        storage.put(category, termVector);
    }
    public TermVector getTermVector(String category) {
        return storage.get(category);
    }
}
