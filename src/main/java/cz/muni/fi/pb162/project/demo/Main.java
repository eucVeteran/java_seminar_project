package cz.muni.fi.pb162.project.demo;

import cz.muni.fi.pb162.project.*;

import java.io.IOException;

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
    public static void main(String[] args) throws IOException {
        var game = new Chess.Builder()
                .addPlayer(new Player("Mat", Color.WHITE))
                .addPlayer(new Player("Pat", Color.BLACK))
                .addPieceToBoard(new Position('e', 1), new Piece(Color.WHITE, PieceType.KING))
                .build();
        game.writeJson(System.out, game);
    }
}
