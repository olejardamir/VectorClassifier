package Vector;

import TextUtils.WordFrequency;

import java.util.Map;

public class VectorClassifier {

    protected TermVector category;
    protected Map<String, Integer> wordFrequency;


    public Map<String, Integer> getWordFrequency() {
        return wordFrequency;
    }

    public double classify(String input) {
        input = input.toLowerCase();
        return category == null ? 0 : getClassificationResult(input, category, category.getValues());
    }

    private double getClassificationResult(String input, TermVector tv, int[] two) {

        Map<String, Integer> wordFrequency = WordFrequency.getWordFrequency(input);
        String[] terms = tv.getRelevantTerms(wordFrequency);

        return calculateResult(
                getSumOfSquares(two), getSumOfSquares1(input, terms), getResult(input, terms, two));
    }

    private double calculateResult(double sumOfSquares, double sumOfSquares1, int result){
        double denominator = getDenominator(sumOfSquares, sumOfSquares1);
        return (denominator < result || denominator==0) ? 0 : result / denominator;
    }

    private double getSumOfSquares(int[] two) {
        double sum = 0.0;
        for (int item : two) sum += item * item;
        return sum;
    }

    private double getSumOfSquares1(String input, String[] terms) {
        double sum = 0.0;
        Map<String, Integer> wordFrequency = WordFrequency.getWordFrequency(input);
        for (int inputValue : generateTermValuesVector(terms, wordFrequency)) sum += inputValue * inputValue;
        return sum;
    }

    private int getResult(String input, String[] terms, int[] two) {
        return getSum(terms, two, WordFrequency.getWordFrequency(input));
    }

    private double getDenominator(double sumOfSquares, double sumOfSquares1) {
        return Math.sqrt(sumOfSquares1) * Math.sqrt(sumOfSquares);
    }



    private int getSum(String[] terms, int[] two, Map<String, Integer> wordFrequency) {
        int sum = 0;
        for (int bound = generateTermValuesVector(terms, wordFrequency).length, i = 0;
             i < bound;
             ++i) sum += generateTermValuesVector(terms, wordFrequency)[i] * two[i];
        return sum;
    }



    protected int[] generateTermValuesVector(String[] terms, Map<String, Integer> wordFrequencies) {
        int[] result = new int[terms.length];
        for (int i = 0; i < terms.length; ++i) {
            Integer value = wordFrequencies.get(terms[i]);
            result[i] = value == null ? 0 : value;
        }
        return result;
    }

    protected Map<String, Integer> init(String input) {
        input = input.toLowerCase();
        return WordFrequency.getWordFrequency(input);
    }
    //--------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------
    //A simple train with the new data, or override the old train data with the new data
    public void newVectorFromInput(String input){
        Map<String, Integer> wordFrequency_ = init(input);
        newVectorFromInput(wordFrequency_);
    }
    public void newVectorFromInput(Map<String, Integer> frequency) {
        wordFrequency = frequency;
        String[] terms = WordFrequency.getMostFrequentWords(wordFrequency.size(), wordFrequency);
        category = new TermVector(terms, generateTermValuesVector(terms, wordFrequency));
    }
    //--------------------------------------------------------------------------------
    //Add additional data
    public void mergeInputToVector(String input) {
        Map<String, Integer> wordFrequency_ = init(input);
        mergeInputToVector(wordFrequency_);
    }

    public void mergeInputToVector(Map<String, Integer> wordFrequency_) {
        if(wordFrequency!=null) {
            for (String str : wordFrequency_.keySet()) {
                int oldFreq = 0;
                if(wordFrequency.containsKey(str)) {
                    oldFreq = wordFrequency.get(str);
                }
                int newFreq = wordFrequency_.get(str);
                int result = oldFreq + newFreq;
                wordFrequency.put(str, result);
            }
            String[] terms = WordFrequency.getMostFrequentWords(wordFrequency.size(), wordFrequency);
            category = new TermVector(terms, generateTermValuesVector(terms, wordFrequency));
        }
        else{
            newVectorFromInput(wordFrequency_);
        }
    }

    //--------------------------------------------------------------------------------
    //Train with the new data but don't add any new words to existing tran set.
    public void mapInputToVector(String input) {
        Map<String, Integer> wordFrequency_ = init(input);
        mapInputToVector(wordFrequency_);
    }

    public void mapInputToVector(Map<String, Integer> wordFrequency_) {
        if(wordFrequency!=null) {
            for (String str : wordFrequency_.keySet()) {
                if(wordFrequency.containsKey(str)) {
                    int oldFreq = wordFrequency.get(str);
                    int newFreq = wordFrequency_.get(str);
                    int result = oldFreq + newFreq;
                    wordFrequency.put(str, result);
                }
            }
            String[] terms = WordFrequency.getMostFrequentWords(wordFrequency.size(), wordFrequency);
            category = new TermVector(terms, generateTermValuesVector(terms, wordFrequency));
        }
    }

    //-----------------------------------------
    //Do the opposite of learning, remove from the learned.
    public void subtractInputFromVector(String input) {
        Map<String, Integer> wordFrequency_ = init(input);
        subtractInputFromVector(wordFrequency_);
    }

    public void subtractInputFromVector(Map<String, Integer> wordFrequency_) {
        if(wordFrequency!=null) {
            for (String str : wordFrequency_.keySet()) {
                if (wordFrequency.containsKey(str)) {
                    int oldFreq = wordFrequency.get(str);
                    int newFreq = wordFrequency_.get(str);
                    int result = oldFreq - newFreq;
                    if (result < 1) {
                        wordFrequency.remove(str);
                    } else {
                        wordFrequency.put(str, result);
                    }
                }
            }
            String[] terms = WordFrequency.getMostFrequentWords(wordFrequency.size(), wordFrequency);
            category = new TermVector(terms, generateTermValuesVector(terms, wordFrequency));
        }
    }




}
