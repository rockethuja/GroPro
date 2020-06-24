package model.guetekriterien;

import model.LinearerKongruentGenerator;
import model.Zufallszahlengenerator;

import java.util.Arrays;

//TODO implementeieren und javadoc
public class Periodenlänge extends Guetekriterium {


    public Periodenlänge(String name) {
        super(name);
    }

    @Override
    public double berechneGuetekriterium(Zufallszahlengenerator generator, double[] zahlenfolge) {
        long randomStart = generator.getGenerator().getStartwert();
        long periodenlaenge = 0;
            if(generator.getGenerator() instanceof LinearerKongruentGenerator){

            }





        return periodenlaenge;
    }

}
