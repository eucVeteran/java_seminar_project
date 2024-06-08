package cz.muni.fi.pb162.project.moves;

import cz.muni.fi.pb162.project.*;
import cz.muni.fi.pb162.project.helper.BasicRulesTester;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Alzbeta Strompova
 */
class DiagonalTest {

    private final Player player1 = new Player("xxx", Color.WHITE);
    private final Player player2 = new Player("yyy", Color.BLACK);

    @Test
    void attributesAndMethods() {
        BasicRulesTester.attributesAmount(Diagonal.class, 2);
        BasicRulesTester.methodsAmount(Diagonal.class, 1);
    }

    @Test
    void inheritance() {
        BasicRulesTester.testInheritance(Move.class, Diagonal.class);
    }

    @Test
    void getAllowedMovesStepOne() {
        Game game = new Chess(null, null); // board with initial layout
        var diagonal = new Diagonal(1);
        var diagonal2 = new Diagonal(1, true);
        Assertions.assertThat(diagonal.getAllowedMoves(game, new Position(1, 1)))
                .containsOnly(new Position(0, 2), new Position(2, 2));
        Assertions.assertThat(diagonal.getAllowedMoves(game, new Position(5, 6)))
                .containsOnly(new Position(6, 5), new Position(4, 5));
        game.getBoard().putPieceOnBoard(new Position(5, 5), new Piece(Color.WHITE, PieceType.BISHOP));
        Assertions.assertThat(diagonal2.getAllowedMoves(game, new Position(5, 5)))
                .containsOnly(new Position(4, 6), new Position(6, 6));
    }
}
