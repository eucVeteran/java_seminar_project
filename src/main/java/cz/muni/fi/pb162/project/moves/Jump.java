package cz.muni.fi.pb162.project.moves;

import cz.muni.fi.pb162.project.*;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashSet;
import java.util.Set;

/**
 * A diagonal jump of a "man" piece in a draughts game.
 * Returned positions are those where we can jump over opponent's pieces in a diagonal direction.
 *
 * @author Alzbeta Strompova
 */
public class Jump implements Move {

    private boolean onlyForward = false;

    /**
     * Constructor with the {@code onlyForward} set to {@code false}
     */
    public Jump() {
    }

    /**
     * Constructor
     *
     * @param onlyForward If {@code true}, then the only forward movement is considered
     */
    public Jump(boolean onlyForward) {
        this.onlyForward = onlyForward;
    }


    @Override
    public Set<Position> getAllowedMoves(Game game, Position position) {
        Board board = game.getBoard();
        Set<Position> result = new HashSet<>();
        Piece piece = board.getPiece(position);
        Color color = (piece == null) ? null : piece.getColor();

        var coordinates = Move.getDiagonalShift(onlyForward, color);
        for (Pair<Integer, Integer> movement : coordinates) {
            var leftToJump = position.column() + movement.getLeft();
            var rightToJump = position.line() + movement.getRight();
            var leftGoal = leftToJump + movement.getLeft();
            var rightGoal = rightToJump + movement.getRight();
            Position targetJumpPos = new Position(leftToJump, rightToJump);
            Position targetPos = new Position(leftGoal, rightGoal);
            piece = board.getPiece(targetJumpPos);
            if (color != null && color.getOppositeColor().equals((piece == null) ? null : piece.getColor())
                    && board.getPiece(targetPos) == null
                    && board.inRange(targetPos)) {
                result.add(targetPos);
            }
        }
        return result;
    }

}
