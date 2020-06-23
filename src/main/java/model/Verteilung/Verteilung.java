package model.Verteilung;

public abstract class Verteilung {
    private boolean stetig;
    private int werteBegin;
    private int werteEnde;

    public Verteilung(boolean stetig,int werteBegin, int werteEnde){
        this.stetig = stetig;
        this.werteBegin = werteBegin;
        this.werteEnde = werteEnde;

    }

}
