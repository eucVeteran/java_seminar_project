package cz.muni.fi.pb162.project;

import cz.muni.fi.pb162.project.helper.BasicRulesTester;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Alzbeta Strompova
 */
public class GameTest {
    private final Player player = new Player("Pat", Color.BLACK);
    private final Player player2 = new Player("Mat", Color.WHITE);
    private final Game game = new Game(player, player2);

    @Test
    void inheritance() {
        BasicRulesTester.testInheritance(Playable.class, Game.class);
    }

    @Test
    void attributesAndMethodsAmount() {
        BasicRulesTester.attributesAmount(Game.class, 5);
        BasicRulesTester.methodsAmount(Game.class, 7);
    }

    @Test
    void testBoardSize() {
        assertEquals(8, game.getBoard().getSize());
    }

    @Test
    void testToStringStateOfGame() {
        assertEquals("white_player_win", StateOfGame.WHITE_PLAYER_WIN.toString());
        assertEquals("black_player_win", StateOfGame.BLACK_PLAYER_WIN.toString());
        assertEquals("playing", StateOfGame.PLAYING.toString());
        assertEquals("pat", StateOfGame.PAT.toString());
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
        game.setStateOfGame(StateOfGame.PAT);
        assertEquals(StateOfGame.PAT, game.getStateOfGame());
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

}
