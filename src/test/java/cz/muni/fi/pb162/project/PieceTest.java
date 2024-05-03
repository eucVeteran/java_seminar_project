package cz.muni.fi.pb162.project;

import cz.muni.fi.pb162.project.helper.BasicRulesTester;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Alzbeta Strompova
 */
public class PieceTest {

    private final Piece piece = new Piece(Color.WHITE, PieceType.KING);
    private final Piece piece2 = new Piece(Color.WHITE, PieceType.QUEEN);
    private final Piece piece3 = new Piece(Color.BLACK, PieceType.ROOK);

    @Test
    void attributesAndMethodsAmount() {
        BasicRulesTester.attributesAmount(Piece.class, 4);
        //BasicRulesTester.methodsAmount(Piece.class, 7);
        BasicRulesTester.attributesFinal(Piece.class, 4);
    }

    @Test
    void getId() {
        var pieceIds = new HashSet<Long>();
        for (int i = 0; i < 42; i++) {
            pieceIds.add(new Piece(Color.WHITE, PieceType.KING).getId());
        }
        assertEquals(42, pieceIds.size(), "Instances of piece should have different ids.");
    }

    @Test
    void getColor() {
        assertEquals(Color.WHITE, piece.getColor());
        assertEquals(Color.WHITE, piece2.getColor());
        assertEquals(Color.BLACK, piece3.getColor());
    }

    @Test
    void getAndSetTypeOfPiece() {
        assertEquals(PieceType.KING, piece.getPieceType());
        assertEquals(PieceType.QUEEN, piece2.getPieceType());
        assertEquals(PieceType.ROOK, piece3.getPieceType());
    }

    //@Test
    void testToString() {
        assertEquals("\u2654", piece.toString());
        assertEquals("\u2655", piece2.toString());
        assertEquals("\u265C", piece3.toString());
    }

    @Test
    void makeClone() {
        var pieceClone = piece.makeClone();
        var pieceClone2 = piece.makeClone();
        var piece2Clone = piece2.makeClone();
        var piece3Clone = piece3.makeClone();
        assertEquals(piece.getColor(), pieceClone.getColor());
        assertEquals(piece.getPieceType(), pieceClone.getPieceType());
        assertNotEquals(piece.getId(), pieceClone.getId());

        assertEquals(piece.getColor(), pieceClone2.getColor());
        assertEquals(piece.getPieceType(), pieceClone2.getPieceType());
        assertNotEquals(piece.getId(), pieceClone2.getId());

        assertEquals(piece2.getColor(), piece2Clone.getColor());
        assertEquals(piece2.getPieceType(), piece2Clone.getPieceType());
        assertNotEquals(piece.getId(), piece2Clone.getId());

        assertEquals(piece3.getColor(), piece3Clone.getColor());
        assertEquals(piece3.getPieceType(), piece3Clone.getPieceType());
        assertNotEquals(piece3.getId(), piece3Clone.getId());
    }

    @Test
    void testEquals() {
        assertThat(piece).isNotEqualTo(piece2);
        assertThat(piece).isNotEqualTo(piece3);
        assertThat(piece2).isNotEqualTo(piece3);
        assertThat(piece).isEqualTo(new Piece(Color.WHITE, PieceType.KING));
        //assertThat(piece).isNotEqualTo(new Piece(Color.WHITE, PieceType.KING));
        assertThat(piece).isEqualTo(piece);
        assertThat(piece2).isEqualTo(piece2);
        assertThat(piece3).isEqualTo(piece3);
    }

    @Test
    void testHashCode() {
        assertNotEquals(piece.hashCode(), piece2.hashCode());
        assertNotEquals(piece.hashCode(), piece3.hashCode());
        assertNotEquals(piece2.hashCode(), piece3.hashCode());
        assertEquals(piece.hashCode(), new Piece(Color.WHITE, PieceType.KING).hashCode());
        //assertNotEquals(piece.hashCode(), new Piece(Color.WHITE, PieceType.KING).hashCode());
        assertEquals(piece.hashCode(), piece.hashCode());
        assertEquals(piece2.hashCode(), piece2.hashCode());
        assertEquals(piece3.hashCode(), piece3.hashCode());
    }

    @Test
    void getMoves() {
        int expectedSize = piece.getMovementStrategies().size();
        try {
            piece.getMovementStrategies().clear();
            Assertions.assertThat(piece.getMovementStrategies())
                    .as("Method returns modifiable collection - return new or unmodifiable.")
                    .hasSize(expectedSize);
        } catch (UnsupportedOperationException e) {
            // ok (unmodifiable)
        }
    }

    @Test
    void getAllPossibleMoves() {
        var game = new Chess(null, null);
        var whiteKing = game.getBoard().getPiece(new Position(4, 0));
        Assertions.assertThat(whiteKing.getAllPossibleMoves(game)).isEmpty();
        var blackPawn = game.getBoard().getPiece(new Position(3, 6));
        Assertions.assertThat(blackPawn.getAllPossibleMoves(game))
                .containsOnly(new Position(3, 5), new Position(3, 4));
        var whiteQueen = new Piece(Color.WHITE, PieceType.QUEEN);
        game.getBoard().putPieceOnBoard(new Position(7, 1), whiteQueen);
        Assertions.assertThat(whiteQueen.getAllPossibleMoves(game))
                .containsOnly(new Position(7, 2),
                        new Position(7, 3),
                        new Position(7, 4),
                        new Position(7, 5),
                        new Position(7, 6),
                        new Position(6, 2),
                        new Position(5, 3),
                        new Position(4, 4),
                        new Position(3, 5),
                        new Position(2, 6));
        game.move(new Position(3, 1), new Position(3, 3));
        Assertions.assertThat(whiteKing.getAllPossibleMoves(game)).containsOnly(new Position(3, 1));
    }

    @Test
    void invalidConstructors() {
        assertThrows(NullPointerException.class, () -> new Piece(null, PieceType.KING));
        assertThrows(NullPointerException.class, () -> new Piece(Color.WHITE, null));
        assertThrows(NullPointerException.class, () -> new Piece(null));
    }
}
