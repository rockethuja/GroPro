package view;

import view.WriteOutput;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class TextFileWriter implements WriteOutput {
    private File file;

    public TextFileWriter(File file){
        this.file = file;
    }
    @Override
    //anstatt liste dann das Model übergeben
    public void writeData(List<String> ausgabe) {


        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file)); //Erzeugen eines effizienten Writers für Textdateien

            for(String zeile : ausgabe) {
                writer.write(zeile);
                writer.newLine();
            }
            writer.newLine();
            writer.newLine();
            writer.close();
        }
        catch(IOException ioe) {
            System.err.println(ioe);
        }

    }
}
