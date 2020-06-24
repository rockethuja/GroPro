package model.guetekriterien;

import model.Zufallszahlengenerator;

import java.util.*;


public class SequenzUpDown extends Guetekriterium {
    public SequenzUpDown(String name) {
        super(name);
    }

    /**
     *
     * Berechnet für einen Zufallszahlengenerator und eine bestimmte Menge an Zufallszahlen die durchschnittliche Abweichung vom zu erwarteten Wert für den Up-Down Test.
     * Gibt Hinweis darauf  wie die Reihenfolge von generierten Zufallszahlen in einer Folge ist.
     * Der Up-Down-Test geht davon aus, dass bei einer unkorrelierten Zufallsfolge ein Wechsel von Zufallszahlen die aufsteigen oder absteigen in gewisser Anzahl zu erwarten ist.
     *
     * @param generator Zufallszahlengenerator
     * @param zahlenfolge Zufallszahlen
     * @return Wert der Durchschnittlichen Abweichung , Summe aller Differenzen von tatsächlichen zu erwarteten Werten dividiert durch die Anzahl der betrachteten Zahlen
     */
    @Override
    public double berechneGuetekriterium(Zufallszahlengenerator generator, double[] zahlenfolge) {
        String bitfolge = "";
        int i = 0;
        int anzahl = zahlenfolge.length;
        while (i < anzahl)  {
            double x1 = zahlenfolge[i++];
            double x2 = zahlenfolge[i++];
            if (x1 < x2) {
                bitfolge += "1";
            } else {
                bitfolge += "0";
            }
        }
        ArrayList<Integer> length = getBitLength(bitfolge);
        Set<Map.Entry<Integer, Integer>> set = getBitEnumeration(length).entrySet();
        double diff = 0;
        for (Map.Entry<Integer, Integer> n : set) {
            int k = n.getKey();
            double kEw = berechnekEw(k, anzahl-1);
            diff+= Math.abs(kEw-k);
        }


        return diff/anzahl;
    }


}
