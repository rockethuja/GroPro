package model;

import model.guetekriterien.Guetekriterium;
import model.guetekriterien.Periodenlänge;
import model.guetekriterien.SequenzUpDown;
import model.guetekriterien.SerielleAutokorrelation;
import model.verteilung.Verteilung;

import java.util.ArrayList;

public class Zufallszahlengenerator {

    private Generatorklasse generator;
    private Verteilung verteilung;
    private ArrayList<Guetekriterium> guete;
    private float[] zahlenfolge;

    public Zufallszahlengenerator(Generatorklasse generator,Verteilung verteilung){
        this.generator =generator;
        this.verteilung =verteilung;
        this.guete = new ArrayList<>();
        this.guete.add(new SerielleAutokorrelation("Serielle Autokorrelation"));
        this.guete.add(new SequenzUpDown("Sequenz Up-Down-Test"));
        this.guete.add(new Periodenlänge("Periodenlänge"));

    }

    public double[] generiereZahlenfolge(int n){
        return generiereZahlenfolge(n,generator.startwert);
    }
    public double[] generiereZahlenfolge(int n, long startwert){
        double[] zufallszahlen = generator.generiereZahlenfolge(n,startwert);
        //double[] zufallszahlenverteilt = verteilung.generiereVerteilung(zufallszahlen) ;
        return zufallszahlen;
    }

    public ArrayList<Guetekriterium> ermittleGuete(){
        return new ArrayList<Guetekriterium>();

    }

    public ArrayList<Guetekriterium> getGuete() {
        return guete;
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
