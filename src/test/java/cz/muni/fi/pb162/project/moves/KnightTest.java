package cz.muni.fi.pb162.project.moves;

import cz.muni.fi.pb162.project.Chess;
import cz.muni.fi.pb162.project.Color;
import cz.muni.fi.pb162.project.Position;
import cz.muni.fi.pb162.project.Piece;
import cz.muni.fi.pb162.project.PieceType;
import cz.muni.fi.pb162.project.helper.BasicRulesTester;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Alzbeta Strompova
 */
class KnightTest {

    @Test
    void attributesAndMethods() {
        BasicRulesTester.attributesAmount(Knight.class, 0);
        BasicRulesTester.methodsAmount(Knight.class, 1);
    }

    @Test
    void inheritance() {
        BasicRulesTester.testInheritance(Move.class, Knight.class);
    }

    @Test
    void getAllowedMoves() {
        var game = new Chess(null, null);
        var knight = new Knight();
        Assertions.assertThat(knight.getAllowedMoves(game, new Position(1, 0)))
                .containsOnly(new Position(0, 2), new Position(2, 2));
        game.getBoard().putPieceOnBoard(new Position(4, 3), new Piece(Color.BLACK, PieceType.KNIGHT));
        Assertions.assertThat(knight.getAllowedMoves(game, new Position(4, 3)))
                .containsOnly(new Position(3, 1),
                        new Position(5, 1),
                        new Position(3, 5),
                        new Position(5, 5),
                        new Position(2, 2),
                        new Position(2, 4),
                        new Position(6, 2),
                        new Position(6, 4));
    }

}
