package model.guetekriterien;

import model.Zufallszahlengenerator;

public class SerielleAutokorrelation extends Guetekriterium {


    public SerielleAutokorrelation(String name) {
        super(name);
    }

    @Override
    public double berechneGuetekriterium(Zufallszahlengenerator generator, double[] zahlenfolge) {
        int n = zahlenfolge.length;
        System.out.println("n=" + n);
        double sum = 0;
        double u = generator.getVerteilung().getU();
        for (int k = 1; k < n; k++) {
            double autoKor = berechneKorrelation(n, k, u,zahlenfolge);
            sum += autoKor;
            System.out.println("Autokorrelation für k=" + k + "  " + autoKor);
        }
        return sum;
    }

    public double berechneKorrelation(int n, int k, double u, double[] zahlenfolge) {
        double denominator=0;
        double counter= 0;
        for (int i = 0; i < n - k; i++) {
            counter+= (zahlenfolge[i]-u)* (zahlenfolge[i+k]-u);
            denominator+= (zahlenfolge[i]-u)*(zahlenfolge[i]-u);
        }
        return counter/denominator;
    }
}
