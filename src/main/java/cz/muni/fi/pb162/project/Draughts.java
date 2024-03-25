package cz.muni.fi.pb162.project;

import static cz.muni.fi.pb162.project.PieceType.*;

/**
 * Represent a game of draughts.
 *
 * @author Azizbek Toshpulatov
 */
public class Draughts extends Game {
    /**
     * Constructs a game with 2 players and a board of size {@link Board#DEF_SIZE} x {@link Board#DEF_SIZE}.
     *
     * @param playerOne first player.
     * @param playerTwo second player.
     */
    public Draughts(Player playerOne, Player playerTwo) {
        super(playerOne, playerTwo);
        setInitialSet();
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
        if ((((newPosition.line() + 1 == getBoard().getSize()) && (currentPiece.getColor() == Color.WHITE)) ||
                ((newPosition.line() == 0) && (currentPiece.getColor() == Color.BLACK)))
                && (currentPiece.getPieceType() == DRAUGHTS_MAN)) {
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
            if (currentPiece.getColor() == Color.WHITE) {
                whites++;
            } else {
                blacks++;
            }
        }

        if (blacks == 0) {
            setStateOfGame(StateOfGame.WHITE_PLAYER_WIN);
        } else if (whites == 0) {
            setStateOfGame(StateOfGame.BLACK_PLAYER_WIN);
        }
    }

    /**
     * Sets initial pieces of the game.
     */
    private void setInitialSet() {
        for (int i = 0; i < getBoard().getSize(); i += 2) {
            getBoard().putPieceOnBoard(new Position(i, 0), new Piece(Color.WHITE, PieceType.DRAUGHTS_MAN));
            getBoard().putPieceOnBoard(new Position(i, 2), new Piece(Color.WHITE, PieceType.DRAUGHTS_MAN));
            getBoard().putPieceOnBoard(new Position(i, 6), new Piece(Color.BLACK, PieceType.DRAUGHTS_MAN));
        }
        for (int i = 1; i < getBoard().getSize(); i += 2) {
            getBoard().putPieceOnBoard(new Position(i, 1), new Piece(Color.WHITE, PieceType.DRAUGHTS_MAN));
            getBoard().putPieceOnBoard(new Position(i, 5), new Piece(Color.BLACK, PieceType.DRAUGHTS_MAN));
            getBoard().putPieceOnBoard(new Position(i, 7), new Piece(Color.BLACK, PieceType.DRAUGHTS_MAN));
        }
    }
}
