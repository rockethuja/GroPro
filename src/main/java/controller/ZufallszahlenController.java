package controller;

import model.*;
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
        Generatorklasse generator = new LinearerKongruentGenerator(true,12345,2*Math.pow(2,31),1103515245,0);
        Zufallszahlengenerator test= new Zufallszahlengenerator(generator, new Normalverteilung(true,0,1,4));
        Generatorklasse generatorNormal = new PolarKoordinatenGenerator(true,generator);
        Zufallszahlengenerator testNormal = new Zufallszahlengenerator(generatorNormal,new Normalverteilung(true,0,1,4));

         double[]  zufallszahlen =test.generiereZahlenfolge(10);
         double[] zufallszahlenNormal = testNormal.generiereZahlenfolge(10);

        System.out.println("Linear: "+Arrays.toString(zufallszahlen));
        System.out.println("Polar: "+Arrays.toString(zufallszahlenNormal));

    }

    private void writeSolution() {
        WriteOutput writer = new TextFileWriter(new File("Ausgabe.txt"));
        writer.writeData(new ArrayList<>());
    }

}
