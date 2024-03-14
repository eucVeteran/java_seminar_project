package cz.muni.fi.pb162.project;

import cz.muni.fi.pb162.project.helper.BasicRulesTester;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Alzbeta Strompova
 */
public class DraughtsTest {
    private final Player player = new Player("Pat", Color.BLACK);
    private final Player player2 = new Player("Mat", Color.WHITE);
    private final Game game = new Draughts(player, player2);

    @Test
    void attributesAndMethodsAmount() {
        BasicRulesTester.attributesAmount(Draughts.class, 0);
        BasicRulesTester.methodsAmount(Draughts.class, 1);
    }

    @Test
    void inheritance() {
        BasicRulesTester.testInheritance(Game.class, Draughts.class);
    }

    @Test
    void getPlayers() {
        assertEquals(player, game.getPlayerOne());
        assertEquals(player2, game.getPlayerTwo());
    }

    @Test
    void getCurrentPlayer() {
        assertEquals(player2, game.getCurrentPlayer());
        assertEquals(player2, new Game(player2, player).getCurrentPlayer());
    }

    @Test
    void getAndSetStateOfGame() {
        assertEquals(StateOfGame.PLAYING, game.getStateOfGame());
        game.setStateOfGame(StateOfGame.BLACK_PLAYER_WIN);
        assertEquals(StateOfGame.BLACK_PLAYER_WIN, game.getStateOfGame());
        game.setStateOfGame(StateOfGame.WHITE_PLAYER_WIN);
        assertEquals(StateOfGame.WHITE_PLAYER_WIN, game.getStateOfGame());
    }

    @Test
    void setInitialSet() {
        IntStream.range(0, 2).forEach(i -> testPieceType(i, PieceType.DRAUGHTS_MAN));
        IntStream.range(5, 7).forEach(i -> testPieceType(i, PieceType.DRAUGHTS_MAN));
        IntStream.range(0, 2).forEach(i -> testColor(i, Color.WHITE));
        IntStream.range(5, 7).forEach(i -> testColor(i, Color.BLACK));
    }

    private void testPieceType(int line, PieceType type) {
        assertTrue(IntStream.range(0, 7)
                        .filter(col -> col % 2 == line % 2)
                        .mapToObj(col -> game.getBoard().getPiece(new Position(col, line)))
                        .allMatch(piece -> piece != null && piece.getPieceType().equals(type)),
                "Wrong type of piece in the line " + line);
    }

    private void testColor(int line, Color white) {
        assertTrue(IntStream.range(0, 7)
                        .filter(col -> col % 2 == line % 2)
                        .mapToObj(col -> game.getBoard().getPiece(new Position(col, line)))
                        .allMatch(piece -> piece.getColor().equals(white)),
                "Wrong order of pieces on the board in the " + line + ". column");
    }

    @Test
    void move() {
        var piece = new Piece(Color.WHITE, PieceType.DRAUGHTS_MAN);
        game.getBoard().putPieceOnBoard(new Position(1, 0), piece);
        game.move(new Position(1, 0), new Position(3, 2));
        assertEquals(piece.getId(), game.getBoard().getPiece(new Position(3, 2)).getId());
        var piece2 = new Piece(Color.WHITE, PieceType.DRAUGHTS_MAN);
        game.getBoard().putPieceOnBoard(new Position(7, 6), piece2);
        game.move(new Position(7, 6), new Position(3, 2));
        assertEquals(piece2.getId(), game.getBoard().getPiece(new Position(3, 2)).getId());
    }

}
