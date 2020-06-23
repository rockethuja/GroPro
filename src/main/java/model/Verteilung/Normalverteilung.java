package model.Verteilung;

import model.Verteilung.Verteilung;

public class Normalverteilung extends Verteilung {
    private double erwartungswert;

    public Normalverteilung(boolean stetig, int werteBegin, int werteEnde, double erwartungswert) {
        super(stetig, werteBegin, werteEnde);
        this.erwartungswert = erwartungswert;
    }



}
