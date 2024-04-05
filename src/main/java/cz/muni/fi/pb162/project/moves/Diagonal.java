package cz.muni.fi.pb162.project.moves;

import cz.muni.fi.pb162.project.*;

import java.util.HashSet;
import java.util.Set;
import org.apache.commons.lang3.tuple.Pair;

/**
 * A generic movement in the diagonal direction.
 *
 * @author Alzbeta Strompova
 */
public class Diagonal implements Move {
    private final int maxRange;
    private boolean onlyForward = false;

    /**
     * Constructor
     */
    public Diagonal() {
        this(Move.BOARD_MAX_SIZE);
    }

    /**
     * Constructor with the {@code onlyForward} set to {@code false}
     *
     * @param maxRange max distance (number of diagonal squares) to move on
     */
    public Diagonal(int maxRange) {
        this.maxRange = maxRange;
    }

    /**
     * Constructor.
     *
     * @param maxRange Max distance (number of diagonal squares) to move on
     * @param onlyForward If {@code true}, then the only forward movement is considered
     */
    public Diagonal(int maxRange, boolean onlyForward) {
        this(maxRange);
        this.onlyForward = onlyForward;
    }

    @Override
    public Set<Position> getAllowedMoves(Game game, Position position) {
        Board board = game.getBoard();
        Set<Position> result = new HashSet<>();
        Piece piece = board.getPiece(position);
        Color color = (piece == null) ? null : piece.getColor();
        Color goal;

        var coordinates = Move.getDiagonalShift(onlyForward, color);
        for (Pair<Integer, Integer> movement : coordinates) {
            for (int i = 1; i <= maxRange; i++) {
                var targetCol = position.column() + i * movement.getLeft();
                var targetLine = position.line() + i * movement.getRight();
                Position targetPosition = new Position(targetCol, targetLine);
                piece = board.getPiece(targetPosition);
                goal = (piece == null) ? null : piece.getColor();
                if (!board.inRange(targetPosition)) {
                    break;
                }
                if (goal == null) {
                    result.add(targetPosition);
                    continue;
                }
                if (color != null && color.getOppositeColor().equals(goal)) {
                    result.add(targetPosition);
                }
                break;
            }
        }
        return result;
    }

}
