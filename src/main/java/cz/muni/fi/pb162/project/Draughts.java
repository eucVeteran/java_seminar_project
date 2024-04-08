package cz.muni.fi.pb162.project;

import static cz.muni.fi.pb162.project.Color.BLACK;
import static cz.muni.fi.pb162.project.Color.WHITE;
import static cz.muni.fi.pb162.project.PieceType.DRAUGHTS_KING;
import static cz.muni.fi.pb162.project.PieceType.DRAUGHTS_MAN;
import static cz.muni.fi.pb162.project.StateOfGame.BLACK_PLAYER_WIN;
import static cz.muni.fi.pb162.project.StateOfGame.WHITE_PLAYER_WIN;

/**
 * Represent a game of draughts.
 *
 * @author Azizbek Toshpulatov
 */
public class Draughts extends Game {
    /**
     * Constructs a draughts game with 2 players and a board of size {@link Board#DEF_SIZE} x {@link Board#DEF_SIZE}.
     *
     * @param playerOne first player.
     * @param playerTwo second player.
     */
    public Draughts(Player playerOne, Player playerTwo) {
        super(playerOne, playerTwo);
        setInitialSet();
    }

    /**
     * Constructs a draughts game with 2 players and a given board.
     *
     * @param playerOne first player.
     * @param playerTwo second player.
     * @param board given board.
     */
    private Draughts(Player playerOne, Player playerTwo, Board board) {
        super(playerOne, playerTwo, board);
    }

    /**
     * Moves the piece from the old position to the new position on the board.
     * If the moved piece is a man and is to be placed on the last (opposite) line of the board,
     * then the man is replaced with a king.
     * If there is no piece at the source position or the positions are wrong, then the method does nothing.
     *
     * @param oldPosition old position.
     * @param newPosition new position.
     */
    @Override
    public void move(Position oldPosition, Position newPosition) {
        Piece currentPiece = getBoard().getPiece(oldPosition);

        if (inTheEnd(newPosition, currentPiece) && currentPiece.getPieceType() == DRAUGHTS_MAN) {
            getBoard().putPieceOnBoard(oldPosition, new Piece(currentPiece.getColor(), DRAUGHTS_KING));
        }

        super.move(oldPosition, newPosition);
    }

    /**
     * Checks whether the game has finished and then changes the status (the winner) of the game appropriately.
     * The game ends if there are no pieces of white or black color on the board.
     */
    @Override
    public void updateStatus() {
        int whites = 0;
        int blacks = 0;
        Piece[] allPieces = getBoard().getAllPiecesFromBoard();

        for (Piece currentPiece : allPieces) {
            if (currentPiece.getColor() == WHITE) {
                whites++;
            } else {
                blacks++;
            }
        }

        if (blacks == 0) {
            setStateOfGame(WHITE_PLAYER_WIN);
        } else if (whites == 0) {
            setStateOfGame(BLACK_PLAYER_WIN);
        }
    }

    /**
     * Sets initial pieces of the game.
     */
    private void setInitialSet() {
        for (int i = 0; i < getBoard().getSize(); i += 2) {
            getBoard().putPieceOnBoard(new Position(i, 0), new Piece(WHITE, DRAUGHTS_MAN));
            getBoard().putPieceOnBoard(new Position(i, 2), new Piece(WHITE, DRAUGHTS_MAN));
            getBoard().putPieceOnBoard(new Position(i, 6), new Piece(BLACK, DRAUGHTS_MAN));
        }
        for (int i = 1; i < getBoard().getSize(); i += 2) {
            getBoard().putPieceOnBoard(new Position(i, 1), new Piece(WHITE, DRAUGHTS_MAN));
            getBoard().putPieceOnBoard(new Position(i, 5), new Piece(BLACK, DRAUGHTS_MAN));
            getBoard().putPieceOnBoard(new Position(i, 7), new Piece(BLACK, DRAUGHTS_MAN));
        }
    }

    /**
     * Returns a builder for {@link Draughts} class.
     *
     * @author Azizbek Toshpulatov
     */
    public static class Builder extends GameBuilder<Draughts> {
        @Override
        public Draughts build() {
            return new Draughts(getPlayerOne(), getPlayerTwo(), getBoard());
        }
    }
}
