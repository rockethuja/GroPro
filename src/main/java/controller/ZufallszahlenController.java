package controller;

import model.*;
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

    } public void run(){

        Gleichverteilung gleichverteilung =new Gleichverteilung(true,0,1);
        //ANSI-C
        Generatorklasse generatorAnsiC = new LinearerKongruentGenerator(true,12345,(long)Math.pow(2,31),1103515245,12345);
        Zufallszahlengenerator ansiC= new Zufallszahlengenerator(generatorAnsiC, gleichverteilung);

        //Minimal Standard
        Generatorklasse generatorMinimal = new LinearerKongruentGenerator(true,1,(long)Math.pow(2,31)-1,16807,0);
        Zufallszahlengenerator minimal= new Zufallszahlengenerator(generatorMinimal, gleichverteilung);

        //Randu
        Generatorklasse generatorRandu = new LinearerKongruentGenerator(true,1,(long)Math.pow(2,31),65539,0);
        Zufallszahlengenerator randu= new Zufallszahlengenerator(generatorMinimal, gleichverteilung);

        //Simsscript
        Generatorklasse generatorSims = new LinearerKongruentGenerator(true,1,(long)Math.pow(2,31)-1,630360016,0);
        Zufallszahlengenerator simsscript= new Zufallszahlengenerator(generatorMinimal,gleichverteilung);

        //NAS's LCG
        Generatorklasse generatorNag = new LinearerKongruentGenerator(true,123456789,(long)Math.pow(2,59),(long)Math.pow(13,13),0);
        Zufallszahlengenerator nag= new Zufallszahlengenerator(generatorMinimal, gleichverteilung);

        //Maple's LCG
        Generatorklasse generatorMaple = new LinearerKongruentGenerator(true,1,(long)Math.pow(10,12),427419669081L,0);
        Zufallszahlengenerator maple= new Zufallszahlengenerator(generatorMinimal, gleichverteilung);


        Generatorklasse generatorNormal = new PolarKoordinatenGenerator(true,generatorAnsiC);
        Zufallszahlengenerator testNormal = new Zufallszahlengenerator(generatorNormal,new Normalverteilung(true,0,1,4));

         float[]  zufallszahlenAnsi =ansiC.generiereZahlenfolge(900);
         float[]  zufallszahlenMinimal =minimal.generiereZahlenfolge(900);
         float[]  zufallszahlenRando =randu.generiereZahlenfolge(900);
         float[]  zufallszahlenSimscript =simsscript.generiereZahlenfolge(900);
         float[]  zufallszahlenNAG =nag.generiereZahlenfolge(900);
         float[]  zufallszahlenMaple =maple.generiereZahlenfolge(900);



         float[] zufallszahlenNormal = testNormal.generiereZahlenfolge(1112);

        System.out.println("LinearAnsi: "+Arrays.toString(zufallszahlenAnsi));
        System.out.println("LinearMinimal: "+Arrays.toString(zufallszahlenMinimal));
        System.out.println("LinearRandu: "+Arrays.toString(zufallszahlenRando));
        System.out.println("LinearSimscript: "+Arrays.toString(zufallszahlenSimscript));
        System.out.println("LinearNag: "+Arrays.toString(zufallszahlenNAG));
        System.out.println("LinearMaple: "+Arrays.toString(zufallszahlenMaple));






        System.out.println("Polar: "+Arrays.toString(zufallszahlenNormal));

    }

    private void writeSolution() {
        WriteOutput writer = new TextFileWriter(new File("Ausgabe.txt"));
        writer.writeData(new ArrayList<>());
    }

}
