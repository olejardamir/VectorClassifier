import Vector.VectorClassifier;
import VectorBooleanOperators.Or;

import java.util.Map;

class Usage {
    public static void main(String[] args) {

        //NOTE: There are many possible ways this classifier can be used, and not all cases are covered.
        //If necessary, please code your own special cases.


        //the simple classifier
        VectorClassifier vc = new VectorClassifier();
        vc.newVectorFromInput("hello there is this a long sentence yes it is blah blah hello.");
        //please note, there are more options than just the newVectorFromInput, see VectorClassifier.java comments
        System.out.println(vc.classify("hello blah"));

        //------------------------------------------------------------------------------

        //Boolean operators usage:

        //Since we are extending the binary onto n-ary logic, we must assume the universe where the logic will function.
        VectorClassifier universe = new VectorClassifier();
        universe.newVectorFromInput("Since we are extending the binary onto n-ary logic, we must assume the universe where the boolean logic will function.");

        //now, we can define the frequencies we will use with the boolean operators
        VectorClassifier xclass = new VectorClassifier();
        xclass.newVectorFromInput("now, we can define the frequencies we will use with the boolean operators");

        VectorClassifier yclass = new VectorClassifier();
        yclass.newVectorFromInput("This is a second frequency we will use with the boolean operators, lets say we are using the OR operator");

        Map<String, Integer> result = new Or().calculate(xclass,yclass,universe);

        //now we can simply override the vc we have initiated above in a previous example
        vc.newVectorFromInput(result);

        System.out.println(vc.classify("This is a result we get using the boolean OR operator"));



    }
}
