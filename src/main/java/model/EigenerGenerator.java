package model;

public class EigenerGenerator extends Generatorklasse {

    private float startwert;


    public EigenerGenerator(boolean deterministisch, int startwert) {
        super(deterministisch);
        this.startwert = startwert;

    }

    @Override
    float[] generiereZahlenfolge(int n) {
        return new float[0];
    }

    @Override
    float[] generiereZahlenfolge(int n, double startwert) {
        return new float[0];
    }
}
