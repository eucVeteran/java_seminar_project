package cz.muni.fi.pb162.project;

import cz.muni.fi.pb162.project.helper.BasicRulesTester;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Alzbeta Strompova
 */
public class BoardTest {

    private final Board board = new Board(8);

    @Test
    void attributesAndMethodsAmount() {
        BasicRulesTester.attributesAmount(Board.class, 1);
        BasicRulesTester.methodsAmount(Board.class, 5);
        assertEquals(8, board.getSize());
    }

    @Test
    void getPiece() {
        var piece = new Piece();
        board.putPieceOnBoard(new Position(3, 4), piece);
        board.putPieceOnBoard(new Position(6, 1), piece);
        var piece2 = new Piece();
        board.putPieceOnBoard(new Position(2, 2), piece2);
        board.putPieceOnBoard(new Position(5, 7), piece2);

        assertNull(board.getPiece(new Position(1, 3)));
        assertNull(board.getPiece(new Position(7, 6)));
        assertNull(board.getPiece(new Position(9, 9)));
        assertNull(board.getPiece(new Position(2, -2)));
        assertEquals(piece, board.getPiece(new Position(3, 4)));
        assertEquals(piece, board.getPiece(new Position(6, 1)));
        assertEquals(piece2, board.getPiece(new Position(2, 2)));
        assertEquals(piece2, board.getPiece(new Position(5, 7)));
    }

    @Test
    void inRange() {
        assertTrue(board.inRange(new Position(2, 4)));
        assertTrue(board.inRange(new Position(0, 0)));
        assertTrue(board.inRange(new Position(7, 7)));
        assertTrue(board.inRange(new Position(6, 1)));
        assertFalse(board.inRange(new Position(5, 15)));
        assertFalse(board.inRange(new Position(0, 9)));
        assertFalse(board.inRange(new Position(8, 0)));
        assertFalse(board.inRange(new Position(-4, -7)));
    }

    @Test
    void isEmptyAndPutPieceOnBoard() {
        var piece = new Piece();
        board.putPieceOnBoard(new Position(3, 4), piece);
        board.putPieceOnBoard(new Position(6, 1), piece);
        board.putPieceOnBoard(new Position(2, 2), piece);
        board.putPieceOnBoard(new Position(5, 7), piece);

        assertTrue(board.isEmpty(new Position(1, 1)));
        assertTrue(board.isEmpty(new Position(7, 6)));
        assertTrue(board.isEmpty(new Position(3, -1)));
        assertTrue(board.isEmpty(new Position(8, 6)));
        assertFalse(board.isEmpty(new Position(3, 4)));
        assertFalse(board.isEmpty(new Position(6, 1)));
        assertFalse(board.isEmpty(new Position(2, 2)));
        assertFalse(board.isEmpty(new Position(5, 7)));
    }
}
