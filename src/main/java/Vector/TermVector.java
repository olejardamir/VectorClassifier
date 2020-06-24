package Vector;

import java.io.Serializable;

public class TermVector implements Serializable {
    private final String[] terms;
    private final int[] values;

    public TermVector(String[] terms, int[] values) {
        this.terms = terms;
        this.values = values;
    }

    String[] getTerms() {
        return terms.clone();
    }

    int[] getValues() {
        return values.clone();
    }
}