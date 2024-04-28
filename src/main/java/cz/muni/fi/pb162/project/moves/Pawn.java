package cz.muni.fi.pb162.project.moves;

import cz.muni.fi.pb162.project.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Movement of a chess pawn.
 *
 * @author Alzbeta Strompova
 */
public class Pawn implements Move {

    @Override
    public Set<Position> getAllowedMoves(Game game, Position position) {
        Board board = game.getBoard();
        Set<Position> result = new HashSet<>();
        Piece piece = board.getPiece(position);
        if (piece != null && piece.getColor().equals(Color.WHITE)) {
            movesByWhite(board, position, result);
        }
        if (piece != null && piece.getColor().equals(Color.BLACK)) {
            movesByBlack(board, position, result);
        }
        return result;
    }

    private void movesByWhite(Board board, Position position, Set<Position> result) {
        result.add(new Position(position.column(), position.line() + 1));
        if (position.line() == 1) {
            result.add(new Position(position.column(), 3));
        }
        Position p1 = new Position(position.column() + 1, position.line() + 1);
        if (!board.isEmpty(p1) && board.getPiece(p1).getColor().equals(Color.BLACK)) {
            result.add(p1);
        }
        Position p2 = new Position(position.column() - 1, position.line() + 1);
        if (!board.isEmpty(p2) && board.getPiece(p2).getColor().equals(Color.BLACK)) {
            result.add(p2);
        }
    }

    private void movesByBlack(Board board, Position position, Set<Position> result) {
        result.add(new Position(position.column(), position.line() - 1));
        if (position.line() == 6) {
            result.add(new Position(position.column(), 4));
        }
        Position p1 = new Position(position.column() + 1, position.line() - 1);
        if (!board.isEmpty(p1) && board.getPiece(p1).getColor().equals(Color.WHITE)) {
            result.add(p1);
        }
        Position p2 = new Position(position.column() - 1, position.line() - 1);
        if (!board.isEmpty(p2) && board.getPiece(p2).getColor().equals(Color.WHITE)) {
            result.add(p2);
        }
    }

}
