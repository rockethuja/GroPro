package model;

public class EigenerGenerator extends Generatorklasse {

    private float startwert;


    public EigenerGenerator(boolean deterministisch, int startwert) {
        super(deterministisch);
        this.startwert = startwert;

    }

    @Override
    double[] generiereZahlenfolge(int n) {
        return new float[0];
    }
}
