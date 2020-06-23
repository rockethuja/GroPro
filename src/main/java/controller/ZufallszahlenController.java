package controller;

import model.*;
import model.Guetekriterien.SequenzUpDown;
import model.Guetekriterien.SerielleAutokorrelation;
import model.Verteilung.Gleichverteilung;
import model.Verteilung.Normalverteilung;
import view.TextFileWriter;
import view.WriteOutput;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class ZufallszahlenController {

    /**
     *
     */
    public ZufallszahlenController() {
        String path = ("Eingabe.txt");
        // init(path);

    }

    private void init(String path) {
        ReadInput reader = new TextFileReader(new File(path));
        try {
            List<String> input = reader.readData();

        } catch (IOException e) {

            e.printStackTrace();
        }

    }

    public void run() {


        ArrayList<Zufallszahlengenerator> generatoren = new ArrayList<>();
        Gleichverteilung gleichverteilung = new Gleichverteilung(true, 0, 1, 0.5);
        //ANSI-C
        Generatorklasse generatorAnsiC = new LinearerKongruentGenerator(true, 12345, (long) Math.pow(2, 31), 1103515245, 12345);
        Zufallszahlengenerator ansiC = new Zufallszahlengenerator(generatorAnsiC, gleichverteilung);
        generatoren.add(ansiC);
        //Minimal Standard
        Generatorklasse generatorMinimal = new LinearerKongruentGenerator(true, 1, (long) Math.pow(2, 31) - 1, 16807, 0);
        Zufallszahlengenerator minimal = new Zufallszahlengenerator(generatorMinimal, gleichverteilung);
        generatoren.add(minimal);
        //Randu
        Generatorklasse generatorRandu = new LinearerKongruentGenerator(true, 1, (long) Math.pow(2, 31), 65539, 0);
        Zufallszahlengenerator randu = new Zufallszahlengenerator(generatorRandu, gleichverteilung);
        generatoren.add(randu);
        //Simsscript
        Generatorklasse generatorSims = new LinearerKongruentGenerator(true, 1, (long) Math.pow(2, 31) - 1, 630360016, 0);
        Zufallszahlengenerator simsscript = new Zufallszahlengenerator(generatorSims, gleichverteilung);
        generatoren.add(simsscript);
        //NAS's LCG
        Generatorklasse generatorNag = new LinearerKongruentGenerator(true, 123456789, (long) Math.pow(2, 59), (long) Math.pow(13, 13), 0);
        Zufallszahlengenerator nag = new Zufallszahlengenerator(generatorNag, gleichverteilung);
        generatoren.add(nag);
        //Maple's LCG
        Generatorklasse generatorMaple = new LinearerKongruentGenerator(true, 1, (long) Math.pow(10, 12), 427419669081L, 0);
        Zufallszahlengenerator maple = new Zufallszahlengenerator(generatorMaple, gleichverteilung);
        generatoren.add(maple);

        Generatorklasse generatorNormal = new PolarKoordinatenGenerator(true, generatorAnsiC);
        Zufallszahlengenerator testNormal = new Zufallszahlengenerator(generatorNormal, new Normalverteilung(true, 0, 1, 0.1));
        generatoren.add(testNormal);

        double[] zufallszahlenAnsi = ansiC.generiereZahlenfolge(100);
        double[] zufallszahlenMinimal = minimal.generiereZahlenfolge(100);
        double[] zufallszahlenRando = randu.generiereZahlenfolge(100);
        double[] zufallszahlenSimscript = simsscript.generiereZahlenfolge(100);
        double[] zufallszahlenNAG = nag.generiereZahlenfolge(100);
        double[] zufallszahlenMaple = maple.generiereZahlenfolge(100);


        double[] zufallszahlenNormal = testNormal.generiereZahlenfolge(1112);

        System.out.println("LinearAnsi: " + Arrays.toString(zufallszahlenAnsi));
        System.out.println("LinearMinimal: " + Arrays.toString(zufallszahlenMinimal));
        System.out.println("LinearRandu: " + Arrays.toString(zufallszahlenRando));
        System.out.println("LinearSimscript: " + Arrays.toString(zufallszahlenSimscript));
        System.out.println("LinearNag: " + Arrays.toString(zufallszahlenNAG));
        System.out.println("LinearMaple: " + Arrays.toString(zufallszahlenMaple));


        for (Zufallszahlengenerator gen : generatoren) {
            SequenzUpDown seq = new SequenzUpDown();
            double kor = seq.berechneGuetekriterium(gen, gen.generiereZahlenfolge(100));
            System.out.println("SequenzUpDown :" + kor);

        }

        for (Zufallszahlengenerator gen : generatoren) {
            SerielleAutokorrelation kor = new SerielleAutokorrelation();
            double aKor = kor.berechneGuetekriterium(gen, gen.generiereZahlenfolge(20));

            System.out.println("Autokorrelation :" + aKor);

        }

    }

    private void writeSolution() {
        WriteOutput writer = new TextFileWriter(new File("Ausgabe.txt"));
        writer.writeData(new ArrayList<>());
    }

}
