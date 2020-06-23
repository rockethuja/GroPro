package model;

public abstract class  Generatorklasse {
    protected boolean deteministisch;
    protected float startwert;

    public Generatorklasse(boolean deterministisch){
        this.deteministisch = deterministisch;

    }
    abstract float[] generiereZahlenfolge(int n);
    abstract float[] generiereZahlenfolge(int n, long startwert);

}
