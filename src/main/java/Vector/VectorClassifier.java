package Vector;

import TextUtils.DefaultStopWordsProvider;
import TextUtils.DefaultTokenizer;
import TextUtils.WordFrequency;
import java.util.Map;
import java.util.Set;

public class VectorClassifier {

    private DefaultTokenizer tokenizer;
    private DefaultStopWordsProvider stopWordsProvider;
    private HashMapTermVectorStorage storage;

    public VectorClassifier(HashMapTermVectorStorage storage, DefaultStopWordsProvider stopWordsProvider) {
        tokenizer = new DefaultTokenizer();
        this.storage = storage;
        this.stopWordsProvider = stopWordsProvider;
    }

    public double classify(String category, String input) {
        input = input.toLowerCase();
        TermVector tv = storage.getTermVector(category);
        if (tv == null) {
            return 0;
        }
        int[] two = tv.getValues();
        return getDenominator(input, tv, two);
    }

    private double getDenominator(String input, TermVector tv, int[] two) {
        double sumOfSquares = getSumOfSquares(two);
        double sumOfSquares1 = getSumOfSquares1(input, tv);
        double denominator = getDenominator(sumOfSquares, sumOfSquares1);
        int result = getResult(input, tv, two);
        return denominator == 0 ? 0 : result / denominator;
    }

    private double getSumOfSquares(int[] two) {
        double sum = 0.0;
        for (int item : two) {
            double v = (item * item);
            sum += v;
        }
        return sum;
    }

    private double getSumOfSquares1(String input, TermVector tv) {
        double sum = 0.0;
        Map<String, Integer> wordFrequency = WordFrequency.getWordFrequency(input, tokenizer, stopWordsProvider);
        for (int inputValue : generateTermValuesVector(tv.getTerms(), wordFrequency)) {
            double v = (inputValue * inputValue);
            sum += v;
        }
        return sum;
    }

    private double getDenominator(double sumOfSquares, double sumOfSquares1) {
        return Math.sqrt(sumOfSquares1) * Math.sqrt(sumOfSquares);
    }

    private int getResult(String input, TermVector tv, int[] two) {
        int sum = 0;
        Map<String, Integer> wordFrequency = WordFrequency.getWordFrequency(input, tokenizer, stopWordsProvider);
        return getSum(tv, two, sum, wordFrequency);
    }

    private int getSum(TermVector tv, int[] two, int sum, Map<String, Integer> wordFrequency) {
        int bound = generateTermValuesVector(tv.getTerms(),wordFrequency).length;
        for (int i = 0; i < bound; i++) {
            int i1 = generateTermValuesVector(tv.getTerms(),wordFrequency)[i] * two[i];
            sum += i1;
        }
        return sum;
    }


    public void teachMatch(String category, String input, int numTermsInVector) {
        input = input.toLowerCase();
        Map<String, Integer> wordFrequency = WordFrequency.getWordFrequency(input, tokenizer, stopWordsProvider);
        Set mostFrequentWords = WordFrequency.getMostFrequentWords(numTermsInVector, wordFrequency);
        String[] terms = (String[]) mostFrequentWords.toArray(new String[0]);
        storage.addTermVector(category, new TermVector(terms, generateTermValuesVector(terms, wordFrequency)));
    }

    private int[] generateTermValuesVector(String[] terms, Map<String, Integer>  wordFrequencies) {
        int[] result = new int[terms.length];
        for (int i = 0; i < terms.length; i++) {
            Integer value = wordFrequencies.get(terms[i]);
            result[i] = value == null ? 0 : value;
        }
        return result;
    }


}
