package model.guetekriterien;

import model.Zufallszahlengenerator;

import static model.guetekriterien.GueteUtils.berechneKorrelation;

public class SerielleAutokorrelation extends Guetekriterium {


    public SerielleAutokorrelation(String name) {
        super(name);
    }

    /**
     *
     *Berechnet für einen Zufallszahlengenerator und eine bestimmte Menge an Zufallszahlen die durchschnittliche Autokorellation.
     *Sie beschreibt wie oder ob die generierten Zufallszahlen über die Zahlenfolge hinweg miteinander in Verbindung stehen.
     *Wenn der Korrelationskoeffizient von null abweicht, spricht das für keine zufällige Zahlenfolge. Man spricht von einer negativen Autokorrelation.
     *
     * @param generator Zufallszahlengenerator für den das Gütekriterium berechnet werden soll.
     * @param zahlenfolge Zahlenfolge für die das Gütekriterium berechnet werden soll.
     * @return Der Durchschnitt der Autokor
     */
    @Override
    public double berechneGuetekriterium(Zufallszahlengenerator generator, double[] zahlenfolge) {
        int n = zahlenfolge.length;
        System.out.println("n=" + n);
        double sum = 0;
        double u = generator.getVerteilung().getU();
        for (int k = 1; k < n; k++) {
            double autoKor = Math.abs(berechneKorrelation(n, k, u,zahlenfolge));
            sum += autoKor;
            System.out.println("Autokorrelation für k=" + k + "  " + autoKor);
        }
        return sum/ n;
    }


}
