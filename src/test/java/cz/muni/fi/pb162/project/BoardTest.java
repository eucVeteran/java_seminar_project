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

    private final Board board = new Board();

    @Test
    void attributesAndMethodsAmount() {
        BasicRulesTester.attributesAmount(Board.class, 1);
        BasicRulesTester.methodsAmount(Board.class, 6);
        BasicRulesTester.attributesFinal(Board.class, 2);
    }

    @Test
    void inheritance() {
        BasicRulesTester.testInheritance(Prototype.class, Piece.class);
    }

    @Test
    void getPiece() {
        assertNull(board.getPiece(new Position(1, 3)));
        assertNull(board.getPiece(new Position(7, 6)));
        var piece = new Piece(Color.WHITE, PieceType.KING);
        board.putPieceOnBoard(new Position(1, 2), piece);
        assertEquals(piece.getId(), board.getPiece(new Position(1, 2)).getId());
        assertEquals(piece.getId(), board.getPiece(new Position('b', 3)).getId());
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
        var piece = new Piece(Color.WHITE, PieceType.KING);
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

    @Test
    void findCoordinatesOfPieceById() {
        var piece = new Piece(Color.WHITE, PieceType.KING);
        board.putPieceOnBoard(new Position(5, 5), piece);
        var result = board.findCoordinatesOfPieceById(piece.getId());
        assertEquals(5, result.column());
        assertEquals(5, result.line());
        var piece2 = new Piece(Color.WHITE, PieceType.KING);
        board.putPieceOnBoard(new Position(2, 7), piece2);
        var result2 = board.findCoordinatesOfPieceById(piece2.getId());
        assertEquals(2, result2.column());
        assertEquals(7, result2.line());
        var result3 = board.findCoordinatesOfPieceById(piece.getId() + piece.getId() + 42);
        assertNull(result3);
    }


}
