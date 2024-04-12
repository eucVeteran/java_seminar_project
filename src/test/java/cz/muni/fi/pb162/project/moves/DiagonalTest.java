package cz.muni.fi.pb162.project.moves;

import cz.muni.fi.pb162.project.*;
import cz.muni.fi.pb162.project.helper.BasicRulesTester;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Alzbeta Strompova
 */
class DiagonalTest {

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

    @Test
    void getAllowedMovesStepBoardSize() {
        Game game = new Chess.Builder().build(); // empty board
        game.getBoard().putPieceOnBoard(new Position(3, 3), new Piece(Color.WHITE, PieceType.QUEEN));
        var diagonal = new Diagonal();
        var diagonal2 = new Diagonal(game.getBoard().getSize(), true);
        Assertions.assertThat(diagonal.getAllowedMoves(game, new Position(3, 3)))
                .containsOnly(new Position(0, 0),
                        new Position(1, 1),
                        new Position(2, 2),
                        new Position(4, 4),
                        new Position(5, 5),
                        new Position(6, 6),
                        new Position(7, 7),
                        new Position(2, 4),
                        new Position(4, 2),
                        new Position(1, 5),
                        new Position(5, 1),
                        new Position(0, 6),
                        new Position(6, 0));
        Assertions.assertThat(diagonal2.getAllowedMoves(game, new Position(3, 3)))
                .containsOnly(new Position(4, 4),
                        new Position(5, 5),
                        new Position(6, 6),
                        new Position(7, 7),
                        new Position(2, 4),
                        new Position(1, 5),
                        new Position(0, 6));
        game.getBoard().putPieceOnBoard(new Position(3, 3), new Piece(Color.BLACK, PieceType.QUEEN));
        Assertions.assertThat(diagonal2.getAllowedMoves(game, new Position(3, 3)))
                .containsOnly(new Position(0, 0),
                        new Position(1, 1),
                        new Position(2, 2),
                        new Position(4, 2),
                        new Position(5, 1),
                        new Position(6, 0));
        game = new Chess(null, null);
        Assertions.assertThat(diagonal.getAllowedMoves(game, new Position(0, 0))).isEmpty();
    }

}
