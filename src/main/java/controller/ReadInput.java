package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface ReadInput {
    List<String> readData() throws IOException;
}
