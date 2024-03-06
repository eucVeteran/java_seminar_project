package cz.muni.fi.pb162.project.demo;

import cz.muni.fi.pb162.project.Board;
import cz.muni.fi.pb162.project.Piece;
import cz.muni.fi.pb162.project.Player;
import cz.muni.fi.pb162.project.Position;

/**
 * Class for running main method.
 *
 * @author Alzbeta Strompova
 */
public class Main {

    /**
     * Runs the code.
     *
     * @param args command line arguments, will be ignored.
     */
    public static void main(String[] args) {
        Player matko = new Player("Matko");
        System.out.println(matko.getName());

        Board board = new Board(8);
        Piece piece1 = new Piece();
        Piece piece2 = new Piece();

        board.putPieceOnBoard(new Position('a', 8), piece1);
        board.putPieceOnBoard(new Position('e', 1), piece2);
    }

}
