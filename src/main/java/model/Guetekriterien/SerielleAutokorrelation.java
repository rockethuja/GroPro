package model.Guetekriterien;

import model.Zufallszahlengenerator;

public class SerielleAutokorrelation implements Guetekriterium {


    @Override
    public double berechneGuetekriterium(Zufallszahlengenerator generator, double[] zahlenfolge) {
        int n = zahlenfolge.length;
        System.out.println("n=" + n);
        double u = generator.getVerteilung().getU();


        for (int k = 0; k < n; k++) {
            double autoKor = berechneKorrelation(n, k, u,zahlenfolge);
            System.out.println("Autokorrelation für k=" + k + "Auto Korrelation" + autoKor);
        }
        return 0;
    }

    private double berechneKorrelation(int n, int k, double u, double[] zahlenfolge) {
        double denominator=0;
        double counter= 0;
        for (int i = 0; i < n - k; i++) {
            double x = zahlenfolge[i];
            counter = (x-u)* (zahlenfolge[i+k]-u);
            denominator = (x-u)*(x-u);
        }
        return counter/denominator;
    }
}
