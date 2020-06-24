package model.guetekriterien;

import model.Zufallszahlengenerator;

/**
 * Abstrakte Klasse für Gütekriterien.
 * @see Periodenlänge
 * @see SequenzUpDown
 * @see SerielleAutokorrelation
 *
 */

public abstract class Guetekriterium {
    /**
     * Name des bestimmten Gütekriteriums
     */
    private String name;

    public Guetekriterium(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     *
     * @param generator Zufallszahlengenerator für den das Gütekriterium berechnet werden soll.
     * @param zahlenfolge Zahlenfolge für die das Gütekriterium berechnet werden soll.
     * @return
     */
    public abstract double berechneGuetekriterium(Zufallszahlengenerator generator , double[] zahlenfolge);


}
