package Vector;

import TextUtils.WordFrequency;

import java.util.Map;

public class VectorClassifier {

    private final HashMapTermVectorStorage storage = new HashMapTermVectorStorage();

    public double classify(String category, String input) {
    input = input.toLowerCase();
    TermVector tv = storage.getTermVector(category);
    return tv == null ? 0 : getClassificationResult(input, tv, tv.getValues());
  }

    private double getClassificationResult(String input, TermVector tv, int[] two) {
        return calculateResult(
        getSumOfSquares(two), getSumOfSquares1(input, tv), getResult(input, tv, two));
    }

    private double calculateResult(double sumOfSquares, double sumOfSquares1, int result){
        double denominator = getDenominator(sumOfSquares, sumOfSquares1);
        return denominator == 0 ? 0 : result / denominator;
    }

    private double getSumOfSquares(int[] two) {
        double sum = 0.0;
        for (int item : two) sum += item * item;
        return sum;
    }

    private double getSumOfSquares1(String input, TermVector tv) {
        double sum = 0.0;
        Map<String, Integer> wordFrequency = WordFrequency.getWordFrequency(input);
        for (int inputValue : generateTermValuesVector(tv.getTerms(), wordFrequency)) sum += inputValue * inputValue;
        return sum;
    }

    private int getResult(String input, TermVector tv, int[] two) {
        return getSum(tv, two, WordFrequency.getWordFrequency(input));
    }

    private double getDenominator(double sumOfSquares, double sumOfSquares1) {
        return Math.sqrt(sumOfSquares1) * Math.sqrt(sumOfSquares);
    }



    private int getSum(TermVector tv, int[] two, Map<String, Integer> wordFrequency) {
        int sum = 0;
        for (int bound = generateTermValuesVector(tv.getTerms(), wordFrequency).length, i = 0;
        i < bound;
        ++i) sum += generateTermValuesVector(tv.getTerms(), wordFrequency)[i] * two[i];
        return sum;
    }

    public void teach(String category, String input) {
        input = input.toLowerCase();
        Map<String, Integer> wordFrequency = WordFrequency.getWordFrequency(input);
        String[] terms = WordFrequency.getMostFrequentWords(wordFrequency.size(), wordFrequency);
        storage.addTermVector(category, new TermVector(terms, generateTermValuesVector(terms, wordFrequency)));
    }

    private int[] generateTermValuesVector(String[] terms, Map<String, Integer>  wordFrequencies) {
        int[] result = new int[terms.length];
        for (int i = 0; i < terms.length; ++i) {
            Integer value = wordFrequencies.get(terms[i]);
            result[i] = value == null ? 0 : value;
        }
        return result;
    }
}
