package model;

public class LinearerKongruentGenerator extends Generatorklasse {
    private double modul;
    private double multiplikater;
    private double verschiebung;
    private double startwert;

    public void setModul(double modul) {
        if (modul < 0) {
            throw new IllegalArgumentException("Der Modulowert muss größer 0 sein.");
        }
        this.modul = modul;
    }

    public void setMultiplikater(double multiplikater) {
        if (multiplikater < 0 || multiplikater > modul) {
            throw new IllegalArgumentException("Der Multiplikator muss zwischen 0 und dem gewählten Modulo-Wert sein");
        }
        this.multiplikater = multiplikater;
    }

    public void setVerschiebung(double verschiebung) {
        if (verschiebung < 0 || verschiebung > modul) {
            throw new IllegalArgumentException("Der Multiplikator muss zwischen 0 und dem gewählten Modulo-Wert sein");
        }
        this.verschiebung = verschiebung;
    }

    public void setStartwert(double startwert) {
        if (startwert < 0 || startwert > modul) {
            throw new IllegalArgumentException("Der Multiplikator muss zwischen 0 und dem gewählten Modulo-Wert sein");
        }
        this.startwert = startwert;
    }

    public LinearerKongruentGenerator(boolean deterministisch, int startwert, double modul, double multiplikater, double verschiebung) {
        //TODO Primteiler etc
        super(deterministisch);
        setModul(modul);
        setMultiplikater(multiplikater);
        setVerschiebung(verschiebung);
        setStartwert(startwert);

    }

    @Override
    double[] generiereZahlenfolge(int n) {
        double[] zahlenfolge = new double[n];
        double x = this.startwert;

        for (int i = 0; i < n; i++) {
            x = (multiplikater * x + verschiebung) % modul;
            zahlenfolge[i] = x/modul;
        }
        return zahlenfolge;
    }


}
