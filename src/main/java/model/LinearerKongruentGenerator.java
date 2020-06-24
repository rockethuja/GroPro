package model;

public class LinearerKongruentGenerator extends Generatorklasse {
    private long modul;
    private long multiplikater;
    private long verschiebung;

    public LinearerKongruentGenerator(boolean deterministisch, int startwert, String name, long modul, long multiplikater, long verschiebung) {

        super(deterministisch,startwert, name);
        setModul(modul);
        setMultiplikater(multiplikater);
        setVerschiebung(verschiebung);
        setStartwert(startwert);

    }

    public void setModul(long modul) {
        if (modul < 0) {
            throw new IllegalArgumentException("Der Modulowert muss größer 0 sein.");
        }
        this.modul = modul;
    }

    public void setMultiplikater(long multiplikater) {
        if (multiplikater < 0 || multiplikater > modul) {
            throw new IllegalArgumentException("Der Multiplikator muss zwischen 0 und dem gewählten Modulo-Wert sein");
        }
        this.multiplikater = multiplikater;
    }

    public void setVerschiebung(long verschiebung) {
        if (verschiebung < 0 || verschiebung > modul) {
            throw new IllegalArgumentException("Der Multiplikator muss zwischen 0 und dem gewählten Modulo-Wert sein");
        }
        this.verschiebung = verschiebung;
    }

    public void setStartwert(long startwert) {
        if (startwert < 0 || startwert > modul) {
            throw new IllegalArgumentException("Der Multiplikator muss zwischen 0 und dem gewählten Modulo-Wert sein");
        }
        this.startwert = startwert;
    }

    @Override
    public double[] generiereZahlenfolge(int n) {
       double[] zahlenfolge = new double[n];
        long x = this.startwert;

        for (int i = 0; i < n; i++) {
            x = (multiplikater * x + verschiebung) % modul;
            double x1 = (double) x/modul;
            zahlenfolge[i] = x1;
        }
        return zahlenfolge;
    }

    public double[] generiereZahlenfolge(int n, long startwer){
        double[] zahlenfolge = new double[n];
        long x = startwer;

        for (int i = 0; i < n; i++) {
            x = (multiplikater * x + verschiebung) % modul;
            double x1 = (double) x/modul;
            zahlenfolge[i] = x1;
        }
        return zahlenfolge;
    }


}
