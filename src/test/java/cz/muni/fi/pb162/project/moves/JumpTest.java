package cz.muni.fi.pb162.project.moves;

import cz.muni.fi.pb162.project.Color;
import cz.muni.fi.pb162.project.Position;
import cz.muni.fi.pb162.project.Draughts;
import cz.muni.fi.pb162.project.Game;
import cz.muni.fi.pb162.project.Piece;
import cz.muni.fi.pb162.project.PieceType;
import cz.muni.fi.pb162.project.helper.BasicRulesTester;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Alzbeta Strompova
 */
class JumpTest {

    @Test
    void attributesAndMethods() {
        BasicRulesTester.attributesAmount(Jump.class, 2);
        BasicRulesTester.methodsAmount(Jump.class, 1);
    }

    @Test
    void inheritance() {
        BasicRulesTester.testInheritance(Move.class, Jump.class);
    }

    @Test
    void getAllowedMoves() {
        Game game = new Draughts.Builder().build(); // empty board
        var jump = new Jump();
        var jump2 = new Jump(true);
        game.getBoard().putPieceOnBoard(new Position(1, 1), new Piece(Color.WHITE, PieceType.DRAUGHTS_MAN));
        game.getBoard().putPieceOnBoard(new Position(0, 0), new Piece(Color.BLACK, PieceType.DRAUGHTS_MAN));
        Assertions.assertThat(jump.getAllowedMoves(game, new Position(0, 0)))
                .containsOnly(new Position(2, 2));
        Assertions.assertThat(jump2.getAllowedMoves(game, new Position(0, 0))).isEmpty();
        game = new Draughts(null, null);
        Assertions.assertThat(jump.getAllowedMoves(game, new Position(1, 1))).isEmpty();
        Assertions.assertThat(jump2.getAllowedMoves(game, new Position(2, 2))).isEmpty();
        game.getBoard().putPieceOnBoard(new Position(5, 3), new Piece(Color.BLACK, PieceType.DRAUGHTS_MAN));
        Assertions.assertThat(jump.getAllowedMoves(game, new Position(6, 2)))
                .containsOnly(new Position(4, 4));
        Assertions.assertThat(jump2.getAllowedMoves(game, new Position(6, 2)))
                .containsOnly(new Position(4, 4));
        Assertions.assertThat(jump2.getAllowedMoves(game, new Position(5, 3))).isEmpty();
    }

}