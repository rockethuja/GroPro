package model.Guetekriterien;

import model.Guetekriterien.Guetekriterium;
import model.Zufallszahlengenerator;

import java.util.*;

/**
 *
 */
public class SequenzUpDown implements Guetekriterium {
    /**
     * @param generator
     * @param zahlenfolge
     * @return
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
            double kEw = berechnekEw(k, anzahl);
            diff+= Math.abs(kEw-k);
        }


        return diff/anzahl;
    }

    private double berechnekEw(int k, int n) {
         double kEw= ((k * k + 3 * k + 1) * n - (Math.pow(k, 3) + 3 * k * k - k - 4)) / ((fakul(k + 3)) / 2);
    return  kEw;
    }

    private double fakul(int i) {
        if (i == 1) {
            return 1;
        } else {
            return i* fakul(i - 1);
        }
    }

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
