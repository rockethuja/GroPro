package model.guetekriterien;

import model.Zufallszahlengenerator;


public class Periodenlänge extends Guetekriterium {


    public Periodenlänge(String name) {
        super(name);
    }

    @Override
    public double berechneGuetekriterium(Zufallszahlengenerator generator, double[] zahlenfolge) {
        long randomStart = generator.getGenerator().getStartwert();
        long startwert = randomStart;
        long periodenlaenge = 0;
        boolean periodenEnde = false;
    /*    while (!periodenEnde) {

            zahlenfolge = generator.generiereZahlenfolge(10000000, startwert);
            if(Arrays.asList(zahlenfolge).contains(randomStart)){
                periodenEnde = true;
                System.out.println("gefunden");
            }
            System.out.println("n");

            startwert = (long)zahlenfolge[zahlenfolge.length-1];

            }
*/
        return periodenlaenge;
    }
}
