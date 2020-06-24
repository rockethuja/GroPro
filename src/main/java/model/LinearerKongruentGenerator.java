package model;

import java.math.BigInteger;

public class LinearerKongruentGenerator extends Generatorklasse {
    private BigInteger modul;
    private BigInteger multiplikater;
    private BigInteger verschiebung;

    public LinearerKongruentGenerator(boolean deterministisch, int startwert, String name, long modul, long multiplikater, long verschiebung) {

        super(deterministisch, startwert, name);
        setModul(modul);
        setMultiplikater(multiplikater);
        setVerschiebung(verschiebung);
        setStartwert(startwert);

    }

    public void setModul(long modul) {
        if (modul < 0) {
            throw new IllegalArgumentException("Der Modulowert muss größer 0 sein.");
        }
        this.modul = BigInteger.valueOf(modul);
    }

    public void setMultiplikater(long multiplikater) {

        if (multiplikater < 0 || multiplikater > modul.longValue()) {
            throw new IllegalArgumentException("Der Multiplikator muss zwischen 0 und dem gewählten Modulo-Wert sein");
        }
        this.multiplikater = BigInteger.valueOf(multiplikater);
    }

    public void setVerschiebung(long verschiebung) {
        if (verschiebung < 0 || verschiebung > modul.longValue()) {
            throw new IllegalArgumentException("Der Multiplikator muss zwischen 0 und dem gewählten Modulo-Wert sein");
        }
        this.verschiebung = BigInteger.valueOf(verschiebung);
    }

    public void setStartwert(long startwert) {
        if (startwert < 0 || startwert > modul.longValue()) {
            throw new IllegalArgumentException("Der Multiplikator muss zwischen 0 und dem gewählten Modulo-Wert sein");
        }
        this.startwert = startwert;
    }

    @Override
    public double[] generiereZahlenfolge(int n) {
     return generiereZahlenfolge(n,this.startwert);
    }

    public double[] generiereZahlenfolge(int n, long startwert) {

        double[] zahlenfolge = new double[n];
        BigInteger rechner = BigInteger.valueOf(startwert);

        for (int i = 0; i < n; i++) {
            rechner = multiplikater.multiply(rechner);
            rechner = rechner.add(verschiebung);
            rechner = rechner.mod(modul);

            zahlenfolge[i] =  rechner.doubleValue()/modul.doubleValue();
        }
        return zahlenfolge;

    }
}
