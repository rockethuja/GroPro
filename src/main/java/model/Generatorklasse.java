package model;

public abstract class  Generatorklasse {
    private String name;
    protected boolean deteministisch;
    protected long startwert;


    public Generatorklasse(boolean deterministisch,long startwert, String name){
        this.deteministisch = deterministisch;
        this.startwert = startwert;
        this.name = name;

    }
    abstract double[] generiereZahlenfolge(int n);
    abstract double[] generiereZahlenfolge(int n, long startwert);


    public long getStartwert() {
        return startwert;
    }

    public String getName() {
        return name;
    }
}
