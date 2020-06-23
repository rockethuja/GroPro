package controller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TextFileReader implements ReadInput {
    private File file;

    public TextFileReader(File file) {
        this.file = file;
    }

    public List<String> readData() throws IOException {
        List<String> input = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line ;
            while ((line  = br.readLine()) != null ) {
                input.add(line);
            }
        }
        return input;
    }
}
