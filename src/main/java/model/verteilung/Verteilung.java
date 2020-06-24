package model.verteilung;

public abstract class Verteilung {
    private boolean stetig;
    private double u;
    private int werteBegin;
    private int werteEnde;

    public Verteilung(boolean stetig,int werteBegin, int werteEnde, double u){
        this.stetig = stetig;
        this.werteBegin = werteBegin;
        this.werteEnde = werteEnde;
        this.u = u;
    }

    public double getU() {
        return u;
    }
}
