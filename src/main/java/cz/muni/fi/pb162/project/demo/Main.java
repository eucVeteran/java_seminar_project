package cz.muni.fi.pb162.project.demo;

import cz.muni.fi.pb162.project.*;
import cz.muni.fi.pb162.project.exceptions.EmptySquareException;
import cz.muni.fi.pb162.project.exceptions.NotAllowedMoveException;

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
        var game = new Chess.Builder()
                .addPlayer(new Player("Mat", Color.WHITE))
                .addPlayer(new Player("Pat", Color.BLACK))
                .build();

        while (true) {
            try {
                game.play();
            } catch (EmptySquareException | NotAllowedMoveException e) {
                System.out.println(e);
            }
        }
    }
}
