package cz.muni.fi.pb162.project.moves;

import cz.muni.fi.pb162.project.Board;
import cz.muni.fi.pb162.project.Game;
import cz.muni.fi.pb162.project.Position;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Movement of a chess knight.
 *
 * @author Alzbeta Strompova
 */
public class Knight implements Move {

    @Override
    public Set<Position> getAllowedMoves(Game game, Position position) {
        Board board = game.getBoard();
        Set<Position> result = new HashSet<>();

        Set<Pair<Integer, Integer>> coordinates = new HashSet<>(Arrays.asList(
                Pair.of(1, 2),
                Pair.of(1, -2),
                Pair.of(-1, 2),
                Pair.of(-1, -2),
                Pair.of(2, 1),
                Pair.of(2, -1),
                Pair.of(-2, 1),
                Pair.of(-2, -1)));

        for (Pair<Integer, Integer> movement : coordinates) {
            var targetCol = position.column() + movement.getLeft();
            var targetLine = position.line() + movement.getRight();
            var targetPosition = new Position(targetCol, targetLine);
            if (board.inRange(targetPosition) &&
                    (board.getPiece(targetPosition) == null ||
                            board.getPiece(position).getColor()
                                    .equals(board.getPiece(targetPosition).getColor().getOppositeColor()))) {
                result.add(targetPosition);
            }
        }
        return result;
    }
}
