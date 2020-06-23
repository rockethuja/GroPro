package model;

import java.util.Arrays;

public class Normalverteilung extends Verteilung {
    private double erwartungswert;

    public Normalverteilung(boolean stetig, int werteBegin, int werteEnde, double erwartungswert) {
        super(stetig, werteBegin, werteEnde);
        this.erwartungswert = erwartungswert;
    }

    @Override
    public double[] generiereVerteilung(double[] zufallszahlen) {
        double[] zufallszahlenVerteilt = new double[zufallszahlen.length];

        for (int i = 0; i < zufallszahlen.length; i += 2) {
            double u = zufallszahlen[i];
            double v = zufallszahlen[i + 1];
            double q = Math.pow(u, 2) + Math.pow(v, 2);
            double p = Math.sqrt((-2 * Math.log(q)) / q);
            zufallszahlenVerteilt[i] = p * u;
            zufallszahlenVerteilt[i + 1] = p * v;
        }
        return null;
    }




}
