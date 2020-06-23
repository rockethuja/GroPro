package model;

import model.Guetekriterien.Guetekriterium;
import model.Verteilung.Verteilung;

import java.util.ArrayList;

public class Zufallszahlengenerator {

    private Generatorklasse generator;
    private Verteilung verteilung;
    private ArrayList<Guetekriterium> güte;
    private float[] zahlenfolge;

    public Zufallszahlengenerator(Generatorklasse generator,Verteilung verteilung){
        this.generator =generator;
        this.verteilung =verteilung;
    }

    public float[] generiereZahlenfolge(int n){
        float[] zufallszahlen = generator.generiereZahlenfolge(n);
        //double[] zufallszahlenverteilt = verteilung.generiereVerteilung(zufallszahlen) ;
        return zufallszahlen;
    }

    public ArrayList<Guetekriterium> ermittleGuete(){
        return new ArrayList<Guetekriterium>();

    }
}
