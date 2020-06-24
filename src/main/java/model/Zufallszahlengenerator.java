package model;

import model.guetekriterien.Guetekriterium;
import model.guetekriterien.Periodenlänge;
import model.guetekriterien.SequenzUpDown;
import model.guetekriterien.SerielleAutokorrelation;
import model.verteilung.Verteilung;

import java.util.ArrayList;

/**
 *Die Klasse bietet Funktionalitäten, um sich mit Hilfe einer Generatorklasse und einer Verteilung eine Zufallszahlenfolge generieren zu lassen
 * Außerdem
 */
public class Zufallszahlengenerator {

    private Generatorklasse generator;

    private Verteilung verteilung;





    /**
     *
     * @param generator  Generatorklasse die bestimmt auf welcher Berechnungsgrundlage die Zufallszahlen generiert werden
     * @param verteilung Die Verteilung der generierten Zufallszahlen
     */
    public Zufallszahlengenerator(Generatorklasse generator,Verteilung verteilung){
        this.generator =generator;
        this.verteilung =verteilung;

    }

    /**
     *
     * @param n Anzahl
     * @return Zufallszahlenfolge der Grüße n
     */

    public double[] generiereZahlenfolge(int n){
        return generiereZahlenfolge(n,generator.startwert);
    }
    public double[] generiereZahlenfolge(int n, long startwert){
        double[] zufallszahlen = generator.generiereZahlenfolge(n,startwert);
        //double[] zufallszahlenverteilt = verteilung.generiereVerteilung(zufallszahlen) ;
        return zufallszahlen;
    }

    public Verteilung getVerteilung() {
        return verteilung;
    }

    public Generatorklasse getGenerator() {
        return generator;
    }

    public String getNameVonGenerator(){
        return generator.getName();
    }
}
