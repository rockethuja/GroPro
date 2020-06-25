package controller;

import model.*;
import model.generatoren.EigenerGenerator;
import model.generatoren.Generatorklasse;
import model.generatoren.LinearerKongruentGenerator;
import model.generatoren.PolarKoordinatenGenerator;
import model.verteilung.Gleichverteilung;
import model.verteilung.Normalverteilung;
import view.TextFileWriter;
import view.WriteOutput;

import java.io.File;
import java.util.*;

import static controller.Testkonstanten.*;
import static model.GueteUtils.*;

/**
 *Controller der das Tetsprogramm darstellt, um die Bibliothek zu testen und Zufallszahlengeneratoren implementiert und von ihnen die Güte bestimmt und so auch die
 * Verfahren zur Güteberechnung testet.
 */
public class ZufallszahlenController {
    /**
     * Liste der initialisierten Zufallszahlengeneratoren
     */
    private ArrayList<Zufallszahlengenerator> testGeneratoren;

    public ZufallszahlenController() {

        this.testGeneratoren = new ArrayList<>();
        init();

    }

    /**
     * Initialisiert die sechs in der Aufgabenstellung genannten LCGs.
     * Außerdem einen Polar-Koordinaten-Generator und zwei Eigene Generatoren
     */
    private void init() {

        Gleichverteilung gleichverteilung = new Gleichverteilung(true, 0, 1, 0.5);

        //ANSI-C
        Generatorklasse generatorAnsiC = new LinearerKongruentGenerator(true, 12345, LCGImplementierung.Ansi_C.name(), (long) Math.pow(2, 31), 1103515245, 12345);
        Zufallszahlengenerator ansiC = new Zufallszahlengenerator(generatorAnsiC, gleichverteilung);
        testGeneratoren.add(ansiC);

        //Minimal Standard
        Generatorklasse generatorMinimal = new LinearerKongruentGenerator(true, 1, LCGImplementierung.MinStandard.name(), (long) Math.pow(2, 31) - 1, 16807, 0);
        Zufallszahlengenerator minimal = new Zufallszahlengenerator(generatorMinimal, gleichverteilung);
        testGeneratoren.add(minimal);

        //Randu
        Generatorklasse generatorRandu = new LinearerKongruentGenerator(true, 1, LCGImplementierung.RANDU.name(), (long) Math.pow(2, 31), 65539, 0);
        Zufallszahlengenerator randu = new Zufallszahlengenerator(generatorRandu, gleichverteilung);
        testGeneratoren.add(randu);

        //Simsscript
        Generatorklasse generatorSims = new LinearerKongruentGenerator(true, 1, LCGImplementierung.SIMSCRIPT.name(), (long) Math.pow(2, 31) - 1, 630360016, 0);
        Zufallszahlengenerator simsscript = new Zufallszahlengenerator(generatorSims, gleichverteilung);
        testGeneratoren.add(simsscript);

        //NAS's LCG
        Generatorklasse generatorNag = new LinearerKongruentGenerator(true, 123456789, LCGImplementierung.NAG_LCG.name(), (long) Math.pow(2, 59), (long) Math.pow(13, 13), 0);
        Zufallszahlengenerator nag = new Zufallszahlengenerator(generatorNag, gleichverteilung);
        testGeneratoren.add(nag);

        //Maple's LCG
        Generatorklasse generatorMaple = new LinearerKongruentGenerator(true, 1, LCGImplementierung.Maple_LCG.name(), (long) Math.pow(10, 12), 427419669081L, 0);
        Zufallszahlengenerator maple = new Zufallszahlengenerator(generatorMaple, gleichverteilung);
        testGeneratoren.add(maple);

        //EigenerGenerator1
        EigenerGenerator generatorEigener = new EigenerGenerator(true, 100, "Eigener_Generator_1", 3, 0.8);
        Zufallszahlengenerator eigener = new Zufallszahlengenerator(generatorEigener, gleichverteilung);
        testGeneratoren.add(eigener);

        //EigenerGenerator2
        EigenerGenerator generatorEigener_2 = new EigenerGenerator(true, 100, "Eigener_Generator_1", 1, 0.1);
        Zufallszahlengenerator eigener_2 = new Zufallszahlengenerator(generatorEigener_2, gleichverteilung);
        testGeneratoren.add(eigener_2);

        //PolarKoordinatenGenerator
        Generatorklasse generatorNormal = new PolarKoordinatenGenerator(true, generatorAnsiC, "Polar_Koordinaten");
        Zufallszahlengenerator testNormal = new Zufallszahlengenerator(generatorNormal, new Normalverteilung(true, 0, 1, 0));
        testGeneratoren.add(testNormal);
    }

    /**
     * Startet das Testprogramm
     */

    public void run() {
        testAusgabe(ANZAHLZAHLEN_TESTAUSGABE);

        for (Zufallszahlengenerator gen : testGeneratoren) {
            testGenerator(gen, ZAHLENLAENGEN_FUER_GUETE);
        }
    }

    /**
     *
     * Berechnet für einen Zufallszahlengenerator und verschieden Längen die Gütekriterien
     *
     * @param gen Zufallszahlengenertor
     * @param n verschieden Längen für die getestet wird
     */
    private void testGenerator(Zufallszahlengenerator gen, int[] n) {
        ArrayList<String> ausgabe = new ArrayList<>();
        ausgabe.add("Berechnungen für " + gen.getNameVonGenerator());

        //Periodenlaenge
        ausgabe.add("");
        ausgabe.add("Periodenlaenge check für eine Periodenlaenge n =" + PERIODENLAENGE + " :  ");
        if (checkPeriodenLaenge(gen, PERIODENLAENGE)) {
            ausgabe.add("erfolgreich");
        } else {
            ausgabe.add("unerfolgreich");
        }
        ausgabe.add("");


        for (int value : n) {
            ausgabe.add("");
            ausgabe.add("Berechnungen für n = "+ value);

            double[] zahlenfolge = gen.generiereZahlenfolge(value);

            //Sequenz-Up-Down
            HashMap<Integer, Map.Entry<Integer, Double>> sequenz = getSequenzUpDown(value, gen.getVerteilung().getU(), zahlenfolge);
           for(Map.Entry<Integer,Map.Entry<Integer,Double>>it: sequenz.entrySet()){
                ausgabe.add("N(" +it.getKey()+") = " +it.getValue().getKey() +"("+ it.getValue().getValue()+")");

            }

            //Serielle Autokorrelation
            ausgabe.add("");
            ausgabe.add("Serielle Autokorrelation nur Ausreißer > " + AUTOKORRELATION_GRENZE);

            for (int k = 1; k < value / 2; k++) {
                double autoKor = berechneKorrelation(value, k, gen.getVerteilung().getU(), zahlenfolge);
                if (autoKor > AUTOKORRELATION_GRENZE) {
                    ausgabe.add("Für " + k + "= " + autoKor);

                }
            }


        }
        writeSolution(ausgabe, gen.getNameVonGenerator() );
    }

    /**
     *
     * Berechnet von allen initialisierten Zufallszahlengeneratoren zufallszahlenfolgen in der Anzahl n
     * und schreibt sie in die Daei Testgenerierung
     *
     * @param n Anzahl
     */

    private void testAusgabe(int n) {
        ArrayList<String> ausgabe = new ArrayList<>();
        ausgabe.add("Generierung von Zufallszahlen für eine Folgenlänge n = " + n);
        for (Zufallszahlengenerator gen : testGeneratoren) {
            double[] zahlenfolge = gen.generiereZahlenfolge(n);
            ausgabe.add(gen.getNameVonGenerator() + ": " + Arrays.toString(zahlenfolge));


        }
        writeSolution(ausgabe, "TestGenerierung");


    }

    /**
     *
     * @param ausgabe Liste an Strings, die pro Zeile augegeben werden sollen
     * @param path Dateiname mit der die Datei im Ordner Testdateien angelegt wird
     */

    private void writeSolution(ArrayList<String> ausgabe, String path) {
        WriteOutput writer = new TextFileWriter(new File("../Testdateien/" + path + ".txt"));
        writer.writeData(ausgabe);
    }


}
