package cz.muni.fi.pb162.project.demo;

import cz.muni.fi.pb162.project.*;

/**
 * Class for running main method.
 *
 * @author Alzbeta Strompova
 * @author Azizbek Toshpulatov
 */
public class Main {
    /**
     * Runs the code.
     *
     * @param args command line arguments, will be ignored.
     */
    public static void main(String[] args) {
        Player mat = new Player("Mat", Color.WHITE);
        Player pat = new Player("Pat", Color.BLACK);
        Board board = new Board();
    }
}
