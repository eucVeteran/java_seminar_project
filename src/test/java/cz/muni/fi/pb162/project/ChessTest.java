package cz.muni.fi.pb162.project;

import cz.muni.fi.pb162.project.helper.BasicRulesTester;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Alzbeta Strompova
 */
public class ChessTest {
    private final Player player = new Player("Pat", Color.BLACK);
    private final Player player2 = new Player("Mat", Color.WHITE);
    private final Chess game = new Chess(player, player2);

    @Test
    void attributesAndMethodsAmount() {
        BasicRulesTester.attributesAmount(Chess.class, 0);
        //BasicRulesTester.methodsAmount(Chess.class, 1);
    }

    @Test
    void inheritance() {
        BasicRulesTester.testInheritance(Game.class, Chess.class);
    }

    @Test
    void getPlayers() {
        assertEquals(player, game.getPlayerOne());
        assertEquals(player2, game.getPlayerTwo());
    }

    @Test
    void getCurrentPlayer() {
        assertEquals(player2, game.getCurrentPlayer());
    }

    @Test
    void getAndSetStateOfGame() {
        assertEquals(StateOfGame.PLAYING, game.getStateOfGame());
        game.setStateOfGame(StateOfGame.BLACK_PLAYER_WIN);
        assertEquals(StateOfGame.BLACK_PLAYER_WIN, game.getStateOfGame());
        game.setStateOfGame(StateOfGame.WHITE_PLAYER_WIN);
        assertEquals(StateOfGame.WHITE_PLAYER_WIN, game.getStateOfGame());
        game.setStateOfGame(StateOfGame.PAT);
        assertEquals(StateOfGame.PAT, game.getStateOfGame());
    }

    @Test
    void setInitialSet() {
        assertTrue(IntStream.range(0, 7)
                        .mapToObj(x -> game.getBoard().getPiece(new Position(x, 1)))
                        .allMatch(x -> x.getPieceType().equals(PieceType.PAWN) && x.getColor().equals(Color.WHITE)),
                "Wrong order of pieces on the board in the 1st column");
        assertTrue(IntStream.range(0, 7)
                        .mapToObj(x -> game.getBoard().getPiece(new Position(x, 6)))
                        .allMatch(x -> x.getPieceType().equals(PieceType.PAWN) && x.getColor().equals(Color.BLACK)),
                "Wrong order of pieces on the board in the 6th column");
        List<PieceType> pieces = List.of(PieceType.ROOK, PieceType.KNIGHT, PieceType.BISHOP,
                PieceType.QUEEN, PieceType.KING, PieceType.BISHOP, PieceType.KNIGHT, PieceType.ROOK);
        var iter = pieces.iterator();
        assertTrue(IntStream.range(0, 7)
                        .mapToObj(x -> game.getBoard().getPiece(new Position(x, 0)))
                        .allMatch(x -> x.getPieceType().equals(iter.next()) && x.getColor().equals(Color.WHITE)),
                "Wrong order of pieces on the board in the 0th column");
        var iter2 = pieces.iterator();
        assertTrue(IntStream.range(0, 7)
                        .mapToObj(x -> game.getBoard().getPiece(new Position(x, 7)))
                        .allMatch(x -> x.getPieceType().equals(iter2.next()) && x.getColor().equals(Color.BLACK)),
                "Wrong order of pieces on the board in the 7th column");
    }

    @Test
    void move() {
        var piece = new Piece(Color.WHITE, PieceType.QUEEN);
        game.getBoard().putPieceOnBoard(new Position(1, 0), piece);
        game.move(new Position(1, 0), new Position(3, 2));
        assertEquals(piece.getId(), game.getBoard().getPiece(new Position(3, 2)).getId());
        var piece2 = new Piece(Color.WHITE, PieceType.QUEEN);
        game.getBoard().putPieceOnBoard(new Position(7, 6), piece2);
        game.move(new Position(7, 6), new Position(3, 2));
        assertEquals(piece2.getId(), game.getBoard().getPiece(new Position(3, 2)).getId());
    }

    @Test
    void movePromotionWhiteSize() {
        var piece = new Piece(Color.WHITE, PieceType.PAWN);
        game.getBoard().putPieceOnBoard(new Position(7, 6), piece);
        game.move(new Position(7, 6), new Position(7, 7));
        assertEquals(piece.getColor(), game.getBoard().getPiece(new Position(7, 7)).getColor());
        assertEquals(PieceType.QUEEN, game.getBoard().getPiece(new Position(7, 7)).getPieceType());

        var piece2 = new Piece(Color.WHITE, PieceType.PAWN);
        game.getBoard().putPieceOnBoard(new Position(3, 6), piece2);
        game.move(new Position(3, 6), new Position(3, 7));
        assertEquals(piece2.getColor(), game.getBoard().getPiece(new Position(3, 7)).getColor());
        assertEquals(PieceType.QUEEN, game.getBoard().getPiece(new Position(3, 7)).getPieceType());

        var piece3 = new Piece(Color.WHITE, PieceType.PAWN);
        game.getBoard().putPieceOnBoard(new Position(7, 4), piece3);
        game.move(new Position(7, 4), new Position(7, 5));
        assertEquals(piece3.getId(), game.getBoard().getPiece(new Position(7, 5)).getId());
        assertEquals(PieceType.PAWN, game.getBoard().getPiece(new Position(7, 5)).getPieceType());

        var piece4 = new Piece(Color.WHITE, PieceType.BISHOP);
        game.getBoard().putPieceOnBoard(new Position(3, 6), piece4);
        game.move(new Position(3, 6), new Position(3, 7));
        assertEquals(piece4.getId(), game.getBoard().getPiece(new Position(3, 7)).getId());
        assertEquals(PieceType.BISHOP, game.getBoard().getPiece(new Position(3, 7)).getPieceType());
    }

    @Test
    void movePromotionBlackSize() {
        var piece = new Piece(Color.BLACK, PieceType.PAWN);
        game.getBoard().putPieceOnBoard(new Position(7, 1), piece);
        game.move(new Position(7, 1), new Position(7, 0));
        assertEquals(piece.getColor(), game.getBoard().getPiece(new Position(7, 0)).getColor());
        assertEquals(PieceType.QUEEN, game.getBoard().getPiece(new Position(7, 0)).getPieceType());

        var piece2 = new Piece(Color.BLACK, PieceType.PAWN);
        game.getBoard().putPieceOnBoard(new Position(3, 1), piece2);
        game.move(new Position(3, 1), new Position(3, 0));
        assertEquals(piece2.getColor(), game.getBoard().getPiece(new Position(3, 0)).getColor());
        assertEquals(PieceType.QUEEN, game.getBoard().getPiece(new Position(3, 0)).getPieceType());

        var piece3 = new Piece(Color.BLACK, PieceType.PAWN);
        game.getBoard().putPieceOnBoard(new Position(0, 5), piece3);
        game.move(new Position(0, 5), new Position(0, 4));
        assertEquals(piece3.getId(), game.getBoard().getPiece(new Position(0, 4)).getId());
        assertEquals(PieceType.PAWN, game.getBoard().getPiece(new Position(0, 4)).getPieceType());

        var piece4 = new Piece(Color.BLACK, PieceType.BISHOP);
        game.getBoard().putPieceOnBoard(new Position(3, 1), piece4);
        game.move(new Position(3, 1), new Position(3, 0));
        assertEquals(piece4.getId(), game.getBoard().getPiece(new Position(3, 0)).getId());
        assertEquals(PieceType.BISHOP, game.getBoard().getPiece(new Position(3, 0)).getPieceType());
    }

    @Test
    void testEquals() {
        assertThat(new Chess(null, null)).isEqualTo(new Chess(null, null));
        assertThat(game).isEqualTo(new Chess(player, player2));

        var game2 = new Chess(player, player2);
        assertThat(game2).isEqualTo(game);

        var piece = new Piece(Color.WHITE, PieceType.ROOK);
        game2.getBoard().putPieceOnBoard(new Position(7, 1), piece);
        assertThat(game).isEqualTo(game2);
    }

    @Test
    void testHashCode() {
        assertThat(new Chess(null, null)).hasSameHashCodeAs(new Chess(null, null));

        var game2 = new Chess(player, player2);
        assertThat(game).hasSameHashCodeAs(game2);

        var piece = new Piece(Color.WHITE, PieceType.ROOK);
        game.getBoard().putPieceOnBoard(new Position(7, 1), piece);
        assertThat(game).hasSameHashCodeAs(game2);
    }

    @Test
    void builderAddPieceToBoard() {
        var game = new Chess.Builder()
                .addPlayer(player)
                .addPlayer(player2)
                .addPieceToBoard(new Position('a', 3), new Piece(Color.WHITE, PieceType.KING))
                .addPieceToBoard(new Position('e', 7), new Piece(Color.BLACK, PieceType.QUEEN))
                .build();
        org.junit.jupiter.api.Assertions.assertEquals(2, game.getBoard().getAllPiecesFromBoard().length);
    }

}
