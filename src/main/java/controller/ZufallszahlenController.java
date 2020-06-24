package controller;

import model.*;
import model.guetekriterien.Guetekriterium;
import model.verteilung.Gleichverteilung;
import model.verteilung.Normalverteilung;
import view.TextFileWriter;
import view.WriteOutput;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 */
public class ZufallszahlenController {
    private ArrayList<Zufallszahlengenerator> testGeneratoren;

    /**
     *
     */
    public ZufallszahlenController() {
        String path = ("Eingabe.txt");
        this.testGeneratoren = new ArrayList<>();
        init(path);

    }

    private void init(String path) {

        Gleichverteilung gleichverteilung = new Gleichverteilung(true, 0, 1, 0.5);

        //ANSI-C
        Generatorklasse generatorAnsiC = new LinearerKongruentGenerator(true, 12345, LCGVarianten.Ansi_C.name(), (long) Math.pow(2, 31), 1103515245, 12345);
        Zufallszahlengenerator ansiC = new Zufallszahlengenerator(generatorAnsiC, gleichverteilung);
        testGeneratoren.add(ansiC);

        //Minimal Standard
        Generatorklasse generatorMinimal = new LinearerKongruentGenerator(true, 1, LCGVarianten.MinStandard.name(), (long) Math.pow(2, 31) - 1, 16807, 0);
        Zufallszahlengenerator minimal = new Zufallszahlengenerator(generatorMinimal, gleichverteilung);
        testGeneratoren.add(minimal);

        //Randu
        Generatorklasse generatorRandu = new LinearerKongruentGenerator(true, 1, LCGVarianten.RANDU.name(), (long) Math.pow(2, 31), 65539, 0);
        Zufallszahlengenerator randu = new Zufallszahlengenerator(generatorRandu, gleichverteilung);
        testGeneratoren.add(randu);

        //Simsscript
        Generatorklasse generatorSims = new LinearerKongruentGenerator(true, 1, LCGVarianten.SIMSCRIPT.name(), (long) Math.pow(2, 31) - 1, 630360016, 0);
        Zufallszahlengenerator simsscript = new Zufallszahlengenerator(generatorSims, gleichverteilung);
        testGeneratoren.add(simsscript);

        //NAS's LCG
        Generatorklasse generatorNag = new LinearerKongruentGenerator(true, 123456789, LCGVarianten.NAG_LCG.name(), (long) Math.pow(2, 59), (long) Math.pow(13, 13), 0);
        Zufallszahlengenerator nag = new Zufallszahlengenerator(generatorNag, gleichverteilung);
        testGeneratoren.add(nag);

        //Maple's LCG
        Generatorklasse generatorMaple = new LinearerKongruentGenerator(true, 1, LCGVarianten.Maple_LCG.name(), (long) Math.pow(10, 12), 427419669081L, 0);
        Zufallszahlengenerator maple = new Zufallszahlengenerator(generatorMaple, gleichverteilung);
        testGeneratoren.add(maple);

        //EigenerGenerator
        EigenerGenerator generatorEigener = new EigenerGenerator(true, 100, "Eigener_Generator");
        Zufallszahlengenerator eigener = new Zufallszahlengenerator(generatorEigener, gleichverteilung);
        testGeneratoren.add(eigener);

        //PolarKoordinatenGenerator
        Generatorklasse generatorNormal = new PolarKoordinatenGenerator(true, generatorAnsiC, "Polar_Koordinaten");
        Zufallszahlengenerator testNormal = new Zufallszahlengenerator(generatorNormal, new Normalverteilung(true, 0, 1, 0));
        testGeneratoren.add(testNormal);


    }

    public void testAusgabe(int n) {
       ArrayList<String> ausgabe = new ArrayList<>();
       ausgabe.add("Generierung von Zufallszahlen für eine Folgenlänge n = "+ n );
        for (Zufallszahlengenerator gen : testGeneratoren) {
          double[] zahlenfolge = gen.generiereZahlenfolge(n);
          ausgabe.add(gen.getNameVonGenerator()+": "+Arrays.toString(zahlenfolge));

          writeSolution(ausgabe,"TestGenerierung");
        }


    }

    public void run() {
        testAusgabe(20);
        int[] testN ={10,100,1000,10000};



        for(int i = 0; i < testN.length; i++) {
            ArrayList<String> ausgabe = new ArrayList<>();

            for (Zufallszahlengenerator gen : testGeneratoren) {
                ausgabe.add(gen.getNameVonGenerator() + ": ");


                for (Guetekriterium kriterium : guetekriterien) {
                    ausgabe.add(kriterium.getName());

                    double wert = kriterium.berechneGuetekriterium(gen, gen.generiereZahlenfolge(testN[i]));

                    ausgabe.add(String.valueOf(wert));

                }
                ausgabe.add("_________________________________");
            }


            writeSolution(ausgabe, String.valueOf(testN[i]));

            ausgabe.clear();
        }
    }

    private void writeSolution(ArrayList<String> ausgabe, String path) {
        WriteOutput writer = new TextFileWriter(new File(path+".txt"));
        writer.writeData(ausgabe);
    }

}
