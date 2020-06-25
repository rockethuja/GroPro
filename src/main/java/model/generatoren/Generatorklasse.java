package model.generatoren;

public abstract class  Generatorklasse {
    private String name;
   private boolean deterministisch;
    private long startwert;


    public Generatorklasse(boolean deterministisch,long startwert, String name){
        this.deterministisch = deterministisch;
        this.startwert = startwert;
        this.name = name;

    }
    public abstract double[] generiereZahlenfolge(int n);
    public abstract double[] generiereZahlenfolge(int n, long startwert);


    public long getStartwert() {
        return startwert;
    }

    public String getName() {
        return name;
    }
    public void setStartwert(long startwert){
        this.startwert= startwert;
    }
}
