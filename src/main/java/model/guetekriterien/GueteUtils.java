package model.guetekriterien;

import model.Zufallszahlengenerator;

import java.util.*;

public class GueteUtils {

    /**
     * Eigenes implementiertes Gütekriterium
     * Weist nach dass ein Generator seinen Startwert bis zu übergebener Periodenlänge nicht wiederholt.
     *
     * @param generator
     * @param grenze    Periodenlänge die geprüft werden soll
     * @return gibt true zurück wenn innerhalb der übergebenen Periodenlänge der Startwert kein zweitesmal generiert wurde
     */
    public static boolean checkPeriodenLaenge(Zufallszahlengenerator generator, int grenze) {
        double[] zahlenfolge = generator.generiereZahlenfolge(grenze + 1);
        double x = zahlenfolge[0];
        ;
        if (Arrays.stream(zahlenfolge).filter(a -> a == x).count() > 1) {
            return false;
        }
        return true;


    }


    public static HashMap<Integer, Map.Entry<Integer, Double>> getSequenzUpDown(int n, double u, double[] zahlenfolge) {
        HashMap<Integer, Map.Entry<Integer, Double>> result = new HashMap<>();
        String bitfolge = "";
        int i = 0;

        while (i < n) {
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


        for (Map.Entry<Integer, Integer> s : set) {
            int k = s.getKey();
            double kEw = berechnekEw(k, n - 1);
            result.put(k, Map.entry(s.getValue(), kEw));

        }


    }

    /**
     * Berechnet für gegebene Parameter die Korrelation.
     * Sie beschreibt wie oder ob die generierten Zufallszahlen über die Zahlenfolge hinweg miteinander in Verbindung stehen.
     * Wenn der Korrelationskoeffizient von null abweicht, spricht das für keine zufällige Zahlenfolge. Man spricht von einer negativen Autokorrelation.
     *
     * @param n           Anzahl Zufallszahlen
     * @param k           Lag
     * @param u           Erwartungswert
     * @param zahlenfolge Zufallszahlenfolge
     * @return berechnet
     */
    public static double berechneKorrelation(int n, int k, double u, double[] zahlenfolge) {
        if (k < 0 || k >= n) {
            throw new IllegalArgumentException("Das gewählte Lag ist für die Länge an Zahlen in der Zahlenfolge nicht geeignet");
        }

        double denominator = 0;
        double counter = 0;
        for (int i = 0; i < n - k; i++) {
            counter += (zahlenfolge[i] - u) * (zahlenfolge[i + k] - u);
            denominator += (zahlenfolge[i] - u) * (zahlenfolge[i] - u);
        }
        return counter / denominator;
    }

    /**
     * @param k Länge der Bitfolgen
     * @param n Anzahl Zufallszahlen eniger eins
     * @return Erwarteter Wert für Länge Bitfolge
     */

    private double berechnekEw(int k, int n) {
        double kEw = ((k * k + 3 * k + 1) * n - (Math.pow(k, 3) + 3 * k * k - k - 4)) / ((fakul(k + 3)) / 2);
        return kEw;
    }

    /**
     * Berechnet die Fakultät für gegebenes i
     *
     * @param i Wert
     * @return Fakultät von i
     */
    private double fakul(int i) {
        if (i == 1) {
            return 1;
        } else {
            return i * fakul(i - 1);
        }
    }

    /**
     * Zählt we oft eine bestimmte Bitlänge in der Sequenz vorgekommen ist
     *
     * @param length Liste von Längen von Bitsequenzen
     * @return Map wo für jede Bitlänge die Anzahl ihres Vorkommens gespeichert wird
     */
    private HashMap<Integer, Integer> getBitEnumeration(ArrayList<Integer> length) {
        HashMap<Integer, Integer> enumeration = new HashMap<>();
        for (int bit : length) {
            if (enumeration.containsKey(bit)) {
                int count = enumeration.get(bit);
                enumeration.put(bit, ++count);
            } else {
                enumeration.put(bit, 0);
            }
        }
        return enumeration;
    }

    /**
     * Berechnet für eine Bitfolge die Anzahl aufeinander folgender Nullen oder Einsen
     *
     * @param bitfolge Bitsequenz
     * @return Liste von Längen von Bitsequenzen
     */
    private ArrayList<Integer> getBitLength(String bitfolge) {
        ArrayList<Integer> length = new ArrayList<>();
        int count = 1;
        char n1 = bitfolge.charAt(0);
        for (int j = 1; j < bitfolge.length(); j++) {
            char n2 = bitfolge.charAt(j);
            if (n1 == n2) {
                count++;

            } else {
                length.add(count);
                count = 1;
            }
            n1 = n2;
        }
        return length;
    }


}
