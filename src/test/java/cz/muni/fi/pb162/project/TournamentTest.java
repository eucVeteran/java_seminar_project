package cz.muni.fi.pb162.project;

import cz.muni.fi.pb162.project.helper.BasicRulesTester;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Radek Oslejsek
 */
public class TournamentTest {

    private final Tournament tournament = new Tournament();
    private final Game game = new Chess(
            new Player("xxx", Color.BLACK),
            new Player("yyy", Color.WHITE));

    @Test
    void attributesAndMethodsAmount() {
        BasicRulesTester.attributesAmount(Tournament.class, 1);
        BasicRulesTester.methodsAmount(Tournament.class, 3);
        BasicRulesTester.attributesFinal(Board.class, 1);
    }

    @Test
    void testHistory() {
        tournament.storeGameState(game);

        Game game2 = new Chess(
                new Player("aaa", Color.BLACK),
                new Player("bbb", Color.WHITE));

        assertNotNull(tournament.getGameHistory(game2));
        assertTrue(tournament.getGameHistory(game2).isEmpty());
        assertNotNull(tournament.getGameHistory(null));
        assertTrue(tournament.getGameHistory(null).isEmpty());
        assertNotNull(tournament.getGameHistory(game));
        assertEquals(1, tournament.getGameHistory(game).size());
        assertTrue(tournament.getGameHistory(game).contains(game.getBoard()));

        Board board1 = game.getBoard().makeClone();
        game.getBoard().putPieceOnBoard(new Position('d', 4), new Piece(Color.BLACK, PieceType.PAWN));
        tournament.storeGameState(game);
        Board board2 = game.getBoard().makeClone();

        var iter = tournament.getGameHistory(game).iterator();
        assertTrue(iter.hasNext());
        assertEquals(iter.next(), board1);
        assertTrue(iter.hasNext());
        assertEquals(iter.next(), board2);
    }

    @Test
    void findGamesOfPlayer() {
        assertEquals(0, tournament.findGamesOfPlayer("fff").size());
        assertEquals(0, tournament.findGamesOfPlayer("xxx").size());
        tournament.storeGameState(game);
        tournament.storeGameState(new Chess(
                new Player("aaa", Color.BLACK),
                new Player("xxx", Color.WHITE)));
        assertEquals(0, tournament.findGamesOfPlayer("fff").size());
        assertEquals(1, tournament.findGamesOfPlayer("aaa").size());
        assertEquals(1, tournament.findGamesOfPlayer("yyy").size());
        assertEquals(2, tournament.findGamesOfPlayer("xxx").size());
    }
}
