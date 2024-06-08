package cz.muni.fi.pb162.project.moves;

import cz.muni.fi.pb162.project.*;
import cz.muni.fi.pb162.project.helper.BasicRulesTester;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Alzbeta Strompova
 */
class StraightTest {

    private final Player player1 = new Player("xxx", Color.WHITE);
    private final Player player2 = new Player("yyy", Color.BLACK);

    @Test
    void inheritance() {
        BasicRulesTester.testInheritance(Move.class, Straight.class);
    }

    @Test
    void attributesAndMethods() {
        BasicRulesTester.attributesAmount(Straight.class, 1);
        BasicRulesTester.methodsAmount(Straight.class, 1);
    }

    @Test
    void getAllowedMovesStepOne() {
        Game game = new Chess(null, null); // board with initial layout
        var straight = new Straight(1);
        Assertions.assertThat(straight.getAllowedMoves(game, new Position(1, 1)))
                .containsOnly(new Position(1, 2));
        Assertions.assertThat(straight.getAllowedMoves(game, new Position(5, 6)))
                .containsOnly(new Position(5, 5));
        game.getBoard().putPieceOnBoard(new Position(4, 4), new Piece(Color.WHITE, PieceType.ROOK));
        Assertions.assertThat(straight.getAllowedMoves(game, new Position(4, 4)))
                .containsOnly(new Position(4, 5), new Position(4, 3),
                        new Position(5, 4), new Position(3, 4));
    }
}
