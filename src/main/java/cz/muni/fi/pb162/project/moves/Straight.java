package cz.muni.fi.pb162.project.moves;

import cz.muni.fi.pb162.project.Board;
import cz.muni.fi.pb162.project.Color;
import cz.muni.fi.pb162.project.Game;
import cz.muni.fi.pb162.project.Position;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * A generic movement in the straight direction (forth, back, left, and right).
 *
 * @author Alzbeta Strompova
 */
public class Straight implements Move {

    private final int maxRange;

    /**
     * Constructor.
     */
    public Straight() {
        this(Move.BOARD_MAX_SIZE);
    }

    /**
     * Constructor
     *
     * @param maxRange max distance (number of diagonal squares) to move on
     */
    public Straight(int maxRange) {
        this.maxRange = maxRange;
    }

    @Override
    public Set<Position> getAllowedMoves(Game game, Position position) {
        Board board = game.getBoard();
        Set<Position> result = new HashSet<>();
        Color color = board.getPiece(position).getColor();

        var coordinates = new HashSet<>(Arrays.asList(
                Pair.of(0, 1),
                Pair.of(0, -1),
                Pair.of(1, 0),
                Pair.of(-1, 0)));

        for (Pair<Integer, Integer> movement : coordinates) {
            for (int i = 1; i <= maxRange; i++) {
                var left = position.column() + i * movement.getLeft();
                var right = position.line() + i * movement.getRight();
                var coordinate = new Position(left, right);
                if (!board.inRange(coordinate)) {
                    break;
                }
                if (board.getPiece(coordinate) == null) {
                    result.add(coordinate);
                } else {
                    if (color.getOppositeColor().equals(board.getPiece(coordinate).getColor())) {
                        result.add(coordinate);
                    }
                    break;
                }
            }
        }
        return result;
    }

}
