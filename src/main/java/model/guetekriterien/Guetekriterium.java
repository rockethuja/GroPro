package model.guetekriterien;

import model.Zufallszahlengenerator;

public abstract class Guetekriterium {
    private String name;

    public Guetekriterium(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract double berechneGuetekriterium(Zufallszahlengenerator generator , double[] zahlenfolge);


}
