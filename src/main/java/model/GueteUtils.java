package model;


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

        return Arrays.stream(zahlenfolge).filter(a -> a == x).count() <= 1;


    }

    /**
     * Berechnet für einen Zufallszahlengenerator und eine bestimmte Menge an Zufallszahlen für einen gegebenen Erwartungswert die Sequenz UpDown Test-Ergebnisse .
     * Gibt Hinweis darauf  wie die Reihenfolge von generierten Zufallszahlen in einer Folge ist.
     * Der Up-Down-Test geht davon aus, dass bei einer unkorrelierten Zufallsfolge ein Wechsel von Zufallszahlen die aufsteigen oder absteigen in gewisser Anzahl zu erwarten ist.
     *
     * @param n           Anzahl
     * @param u           Erwartungswert
     * @param zahlenfolge zu testende Zahlenfolge
     * @return Gibt eine HashMap zurück die im Key die Beträge der Absteigenden oder Aufsteigenden Folgenteile beinhaltet und
     * dazu als Value die ermittelte Anzahl ihrees Vorkommens und dem erwarteten Wert ihres Vorkommens als Map.Entry
     */

    public static HashMap<Integer, Map.Entry<Integer, Double>> getSequenzUpDown(int n, double u, double[] zahlenfolge) {
        HashMap<Integer, Map.Entry<Integer, Double>> result = new HashMap<>();
        StringBuilder bitfolge = new StringBuilder();
        int i = 0;

        while (i < n - 1) {
            double x1 = zahlenfolge[i++];
            double x2 = zahlenfolge[i];
            if (x1 < x2) {
                bitfolge.append("1");
            } else {
                bitfolge.append("0");
            }
        }
        ArrayList<Integer> length = getBitLaenge(bitfolge.toString());
        Set<Map.Entry<Integer, Integer>> set = getBitEnumeration(length).entrySet();


        for (Map.Entry<Integer, Integer> s : set) {
            int k = s.getKey();
            double kEw = berechnekEw(k, n - 1);
            result.put(k, Map.entry(s.getValue(), kEw));

        }
        return result;

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
     * Berechnet für einen Zufallszahlengenerator und eine bestimmte Menge an Zufallszahlen die durchschnittliche Abweichung vom zu erwarteten Wert für den Up-Down Test.
     * Gibt Hinweis darauf  wie die Reihenfolge von generierten Zufallszahlen in einer Folge ist.
     * Der Up-Down-Test geht davon aus, dass bei einer unkorrelierten Zufallsfolge ein Wechsel von Zufallszahlen die aufsteigen oder absteigen in gewisser Anzahl zu erwarten ist.
     *
     * @param generator   Zufallszahlengenerator
     * @param zahlenfolge Zufallszahlen
     * @return Wert der Durchschnittlichen Abweichung , Summe aller Differenzen von tatsächlichen zu erwarteten Werten dividiert durch die Anzahl der betrachteten Zahlen
     */
    public double berechneDurchschnittSequenzUpDown(Zufallszahlengenerator generator, double[] zahlenfolge) {

        HashMap<Integer, Map.Entry<Integer, Double>> sequenz = getSequenzUpDown(zahlenfolge.length, generator.getVerteilung().getU(), zahlenfolge);

        double diff = 0;

        for (Map.Entry<Integer, Map.Entry<Integer, Double>> it : sequenz.entrySet()) {
            diff += Math.abs(it.getValue().getKey() - it.getValue().getValue());

        }

        return diff / zahlenfolge.length;

    }

    /**
     * Berechnet für einen Zufallszahlengenerator und eine bestimmte Menge an Zufallszahlen die durchschnittliche Autokorellation.
     * Sie beschreibt wie oder ob die generierten Zufallszahlen über die Zahlenfolge hinweg miteinander in Verbindung stehen.
     * Wenn der Korrelationskoeffizient von null abweicht, spricht das für keine zufällige Zahlenfolge. Man spricht von einer negativen Autokorrelation.
     *
     * @param generator   Zufallszahlengenerator für den das Gütekriterium berechnet werden soll.
     * @param zahlenfolge Zahlenfolge für die das Gütekriterium berechnet werden soll.
     * @return Der Durchschnitt der Autokor
     */

    public double berechneDurchschnittKorrelation(Zufallszahlengenerator generator, double[] zahlenfolge) {
        int n = zahlenfolge.length;
        System.out.println("n=" + n);
        double sum = 0;
        double u = generator.getVerteilung().getU();
        for (int k = 1; k < n; k++) {
            double autoKor = Math.abs(berechneKorrelation(n, k, u, zahlenfolge));
            sum += autoKor;
            System.out.println("Autokorrelation für k=" + k + "  " + autoKor);
        }
        return sum / n;
    }


    /**
     * @param k Länge der Bitfolgen
     * @param n Anzahl Zufallszahlen eniger eins
     * @return Erwarteter Wert für Länge Bitfolge
     */

    private static double berechnekEw(int k, int n) {
        if (k >= 16) {
            throw new ArithmeticException("");
        }
        double kEw = ((k * k + 3 * k + 1) * n - (Math.pow(k, 3) + 3 * k * k - k - 4)) / ((fakul(k + 3)) / 2.);
        return kEw;
    }

    /**
     * Berechnet die Fakultät für gegebenes i
     *
     * @param i Wert
     * @return Fakultät von i
     */
    private static double fakul(int i) {
        if (i == 1) {
            return 1;
        } else {
            return i * fakul(i - 1);
        }
    }

    /**
     * Zählt we oft eine bestimmte Bitlänge in der Sequenz vorgekommen ist
     *
     * @param laenge Liste von Längen von Bitsequenzen
     * @return Map wo für jede Bitlänge die Anzahl ihres Vorkommens gespeichert wird
     */
    public static HashMap<Integer, Integer> getBitEnumeration(ArrayList<Integer> laenge) {
        HashMap<Integer, Integer> enumeration = new HashMap<>();
        for (int bit : laenge) {
            if (enumeration.containsKey(bit)) {
                int count = enumeration.get(bit);
                enumeration.put(bit, ++count);
            } else {
                enumeration.put(bit, 1);
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
    private static ArrayList<Integer> getBitLaenge(String bitfolge) {
        ArrayList<Integer> length = new ArrayList<>();
        int count = 1;
        char n1 = bitfolge.charAt(0);
        for (int j = 1; j < bitfolge.length(); j++) {
            char n2 = bitfolge.charAt(j);
            if (n1 == n2) {
                count++;

            } else {
                length.add(count);
                count=1;
            }
            n1 = n2;
        }
        return length;
    }


}
