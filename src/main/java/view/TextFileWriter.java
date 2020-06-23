package view;

import view.WriteOutput;

import java.io.File;
import java.util.List;

public class TextFileWriter implements WriteOutput {
    private File file;

    public TextFileWriter(File file){
        this.file = file;
    }
    @Override
    //anstatt liste dann das Model übergeben
    public void writeData(List<String> output) {

        System.out.println("kappa");

    }
}
