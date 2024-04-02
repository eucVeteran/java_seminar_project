package cz.muni.fi.pb162.project;

import cz.muni.fi.pb162.project.helper.BasicRulesTester;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
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
        //BasicRulesTester.methodsAmount(Board.class, 8);
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

    @Test
    void getAllPiecesFromBoard() {
        var board = new Board();
        var result = new ArrayList<Piece>();
        for (int i = 0; i < 5; i++) {
            for (int j = 1; j < 8; j++) {
                var piece = new Piece(Color.WHITE, PieceType.PAWN);
                board.putPieceOnBoard(new Position(i, j), piece);
                result.add(piece);
            }
        }
        assertEquals(result.size(), board.getAllPiecesFromBoard().length);

        var board2 = new Board();
        var piece = new Piece(Color.WHITE, PieceType.KING);
        board2.putPieceOnBoard(new Position(1, 2), piece);
        var piece2 = new Piece(Color.BLACK, PieceType.QUEEN);
        board2.putPieceOnBoard(new Position(5, 5), piece2);
        var piece3 = new Piece(Color.BLACK, PieceType.BISHOP);
        board2.putPieceOnBoard(new Position(7, 4), piece3);
        Assertions.assertThat(board2.getAllPiecesFromBoard())
                .containsOnly(piece, piece2, piece3);
    }

    @Test
    void testToString() {
        var expectedOutput = """
                    A   B   C   D   E   F   G   H\s
                  --------------------------------
                8 |   |   |   |   |   |   |   |   |
                  --------------------------------
                7 |   |   |   |   |   |   |   |   |
                  --------------------------------
                6 |   |   |   |   |   |   |   |   |
                  --------------------------------
                5 |   |   |   |   |   |   |   |   |
                  --------------------------------
                4 |   |   |   |   |   |   |   |   |
                  --------------------------------
                3 |   |   |   |   |   |   |   |   |
                  --------------------------------
                2 |   |   |   |   |   |   |   |   |
                  --------------------------------
                1 |   |   |   |   |   |   |   |   |
                  --------------------------------""".replace("\n", System.lineSeparator());
        assertEquals(expectedOutput, board.toString());
    }

    @Test
    void testEquals() {
        assertThat(new Board()).isEqualTo(new Board());
        var game = new Chess(null, null);
        var game2 = new Chess(null, null);
        assertThat(game.getBoard()).isEqualTo(game2.getBoard());
        var board2 = new Board();
        var piece = new Piece(Color.WHITE, PieceType.ROOK);
        board.putPieceOnBoard(new Position(7, 1), piece);
        board2.putPieceOnBoard(new Position(7, 1), piece);
        assertThat(board).isEqualTo(board2);
        board2.putPieceOnBoard(new Position(7, 1), new Piece(Color.BLACK, PieceType.ROOK));
        assertThat(board).isNotEqualTo(board2);
    }

    @Test
    void testHashCode() {
        assertThat(new Board()).hasSameHashCodeAs(new Board());
        var game = new Chess(null, null);
        var game2 = new Chess(null, null);
        assertThat(game.getBoard().hashCode()).hasSameHashCodeAs(game2.getBoard());
        var board2 = new Board();
        var piece = new Piece(Color.WHITE, PieceType.ROOK);
        board.putPieceOnBoard(new Position(7, 1), piece);
        board2.putPieceOnBoard(new Position(7, 1), piece);
        assertThat(board).hasSameHashCodeAs(board2);
        board2.putPieceOnBoard(new Position(7, 1), new Piece(Color.BLACK, PieceType.ROOK));
        assertThat(board).doesNotHaveSameHashCodeAs(board2);
    }

}
