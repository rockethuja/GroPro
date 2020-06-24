package model;


public class PolarKoordinatenGenerator extends Generatorklasse {
    Generatorklasse gleichverteilung;

    public PolarKoordinatenGenerator(boolean deterministisch, Generatorklasse gleichverteilung, String name) {
        super(deterministisch,gleichverteilung.startwert,name);
        this.gleichverteilung = gleichverteilung;

    }

    @Override
    double[] generiereZahlenfolge(int n) {
       return generiereZahlenfolge(n,startwert);
    }

    @Override
    double[] generiereZahlenfolge(int n, long startwert) {

        double[] gleichverteilteZahlen = gleichverteilung.generiereZahlenfolge(n,startwert);
        double[] normalverteilteZahlen = new double[n];
        int count = 0;
        int i = 0;
        while (count < n) {

            try {
                double[] a = generierePolarKoordinatenpaar(gleichverteilteZahlen[i++], gleichverteilteZahlen[i++]);
                normalverteilteZahlen[count++] = a[0];
                if (count < n) {
                    normalverteilteZahlen[count++] = a[1];
                }

            } catch (ArithmeticException e) {

            }
            if (i >= n - 3) {
                gleichverteilteZahlen = gleichverteilung.generiereZahlenfolge(n, (long) gleichverteilteZahlen[n - 1]);
                i = 0;
            }

        }


        return normalverteilteZahlen;
    }

    protected double[] generierePolarKoordinatenpaar(double u, double v) {
        if (u < -1 || u > 1 || v < -1 || v > 1) {
            throw new IllegalArgumentException("Die Eingaben müssen im Intervall zwischen [-1,1] liegen.");
        }
        ;
        double[] zufallszahlenVerteilt = new double[2];
        double q = Math.pow(u, 2) + Math.pow(v, 2);

        if (q < 0 || q >= 1) {
            throw new ArithmeticException("Q muss im Interval [0,1]liegen");
        }

        double p =  Math.sqrt((-2 * Math.log(q)) / q);

        zufallszahlenVerteilt[0] = p * u;
        zufallszahlenVerteilt[1] = p * v;

        return zufallszahlenVerteilt;

    }

}
