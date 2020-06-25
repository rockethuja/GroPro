package view;

import java.util.List;

/**
 * Ausgabe Interface für eine mögliche Erweiterung der AUsgabe Kanäle
 */
public interface WriteOutput {
    /**
     *
     * @param output Liste der Zeilen, die ausgegeben werden sollen
     */
    void writeData(List<String>output );

}
