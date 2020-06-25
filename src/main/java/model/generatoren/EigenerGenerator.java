package model.generatoren;

/**
 * Selbst entwickelter Zufallszahlengenerator:
 * Berechnet eine determnistische Folge von Zufallszahlen für das Intervall [0,1].
 * Der Generator macht sich zu Nutze, dass der Werteberecih der Sinusfunktion nur [-1,1] ist.
 * Durch Math.abs() wird der negative Bereich wieder auf den positiven Bereich abgebildet.
 * Der Wertmultiplikator verkleinert den Vorgängerwert.
 * Daraufhin wird eine Phasenverschiebung erreicht indem je nach Iteration ( Math.pow(-1, i)) ein Wert der von Pi
 * abhängt addiert oder dubtrahiert wird. Für piMultiplikator = k*pi wird eine ganze Periode verschoben, deswegen braucht man
 * den wertMultiplikator. Damit dieses Risiko minimiert wird.
 * Es emphielt sich eine nicht ganzzahlige Zahl für den piMultiplikator zu nehmen, um nicht eine regelmäßige Periodenverschiebung zu erreichen.
 *
 *
 */
public class EigenerGenerator extends Generatorklasse {
    /**
     * multipliziert die zufallszahl der Vorgängeriteration
     */
    private int wertMultiplikator;
    /**
     * Multipliziert die Phasenverschiebung
     */
    private double piMultiplikator;


    public EigenerGenerator(boolean deterministisch, int startwert, String name, int wertMultiplikator, double piMultiplikator) {
        super(deterministisch, startwert, name);
        this.wertMultiplikator = wertMultiplikator;
        this.piMultiplikator = piMultiplikator;
    }

    @Override
    public double[] generiereZahlenfolge(int n) {
        return generiereZahlenfolge(n, getStartwert());
    }

    @Override
    public double[] generiereZahlenfolge(int n, long startwert) {
        double[] zahlen = new double[n];
        double x = startwert;
        for (int i = 0; i < n; i++) {
            x = Math.abs(Math.sin(wertMultiplikator * x + Math.pow(-1, i) * piMultiplikator * Math.PI));
            zahlen[i] = x;
        }
        return zahlen;
    }
}
