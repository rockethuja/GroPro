package model;

public abstract class  Generatorklasse {
    protected boolean deteministisch;
    protected float startwert;

    abstract float[] generiereZahlenfolge(int n);

    public Generatorklasse(boolean deterministisch){
        this.deteministisch = deterministisch;

    }

}
