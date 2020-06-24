package model;

public class EigenerGenerator extends Generatorklasse {


    public EigenerGenerator(boolean deterministisch, int startwert, String name) {
        super(deterministisch,startwert,name);
    }

    @Override
    public double[] generiereZahlenfolge(int n) {
        return generiereZahlenfolge(n,this.startwert);
    }

    @Override
    public double[] generiereZahlenfolge(int n, long startwert) {
        double[] zahlen = new double[n];
        double x= startwert;
        for (int i = 0; i < n; i++){
            x =Math.abs(Math.sin(3*x+Math.pow(-1,i)*5/6*Math.PI));
            zahlen[i]=x;
        }
            return zahlen;
    }
}
