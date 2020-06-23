package model;

public abstract class  Generatorklasse {
    protected boolean deteministisch;


    public Generatorklasse(boolean deterministisch){
        this.deteministisch = deterministisch;

    }
    abstract double[] generiereZahlenfolge(int n);
    abstract double[] generiereZahlenfolge(int n, long startwert);

}
