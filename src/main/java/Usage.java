import Vector.VectorClassifier;

class Usage {
    public static void main(String[] args) {
        VectorClassifier vc = new VectorClassifier();
        vc.newVectorFromInput("hello there is this a long sentence yes it is blah blah hello.");
        System.out.println(vc.classify("hello blah"));

    }
}
