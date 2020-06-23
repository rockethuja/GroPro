package model;

import java.util.ArrayList;
import java.util.Arrays;

public class PolarKoordinatenGenerator extends Generatorklasse {
    Generatorklasse gleichverteilung;

    public PolarKoordinatenGenerator(boolean deterministisch, Generatorklasse gleichverteilung) {
        super(deterministisch);
        this.gleichverteilung = gleichverteilung;

    }

    @Override
    float[] generiereZahlenfolge(int n) {

        float[] gleichverteilteZahlen = gleichverteilung.generiereZahlenfolge(n);
        System.out.println("VorPolar: "+ Arrays.toString(gleichverteilteZahlen));
        float[] normalverteilteZahlen = new float[n];
        int count = 0;
        int i = 0;
        while (count <n) {

            try {
                float[] a =generierePolarKoordinatenpaar(gleichverteilteZahlen[i++], gleichverteilteZahlen[i++]);

                normalverteilteZahlen[count++]=a[0];
                if(count<n) {
                    normalverteilteZahlen[count++] = a[1];
                }
;
            } catch (ArithmeticException e) {

            }
            if (i >= n - 3) {
                gleichverteilteZahlen = gleichverteilung.generiereZahlenfolge(n,(long)gleichverteilteZahlen[n-1]);
                i=0;
            }

        }


        return normalverteilteZahlen ;
    }

    @Override
    float[] generiereZahlenfolge(int n, long startwert) {
        return new float[0];
    }

    protected float[] generierePolarKoordinatenpaar(float u, float v) {
        if (u < -1 || u > 1 || v < -1 || v > 1) {
            throw new IllegalArgumentException("Die Eingaben müssen im Intervall zwischen [-1,1] liegen.");
        }
        ;
        float[] zufallszahlenVerteilt = new float[2];
        float q = (float)Math.pow(u, 2) + (float)Math.pow(v, 2);

        if (q < 0 || q >= 1) {
            throw new ArithmeticException("Q muss im Interval [0,1]liegen");
        }
        System.out.println("u:"+u+"v:"+v+"q:"+q);
       float p = (float)Math.sqrt((-2 * Math.log(q)) / q);
        zufallszahlenVerteilt[0] = p * u;
        zufallszahlenVerteilt[1] = p * v;
        return zufallszahlenVerteilt;

    }

}
