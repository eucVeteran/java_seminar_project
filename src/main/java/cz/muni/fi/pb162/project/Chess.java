package cz.muni.fi.pb162.project;

import static cz.muni.fi.pb162.project.PieceType.*;

/**
 * Represent a game of chess.
 *
 * @author Azizbek Toshpulatov
 */
public class Chess extends Game {

    /**
     * Constructs a game with 2 players and a board of size {@link Board#DEF_SIZE} x {@link Board#DEF_SIZE}.
     *
     * @param playerOne first player.
     * @param playerTwo second player.
     */
    public Chess(Player playerOne, Player playerTwo) {
        super(playerOne, playerTwo);
        setInitialSet();
    }

    /**
     * Moves the piece from the old position to the new position on the board.
     * If the moved piece is a pawn and is to be placed on the last (opposite) line of the board,
     * then the piece turns into a queen.
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
                && (currentPiece.getPieceType() == PAWN)) {
            getBoard().putPieceOnBoard(oldPosition, new Piece(currentPiece.getColor(), QUEEN));
        }

        super.move(oldPosition, newPosition);
    }

    /**
     * Checks whether the game has finished and then changes the status (the winner) of the game appropriately.
     * The game ends if there is only one king on the board.
     */
    @Override
    public void updateStatus() {
        boolean whiteKing = false;
        boolean blackKing = false;
        Piece[] allPieces = getBoard().getAllPiecesFromBoard();

        for (Piece currentPiece : allPieces) {
            if (currentPiece.getPieceType() == KING) {
                if (currentPiece.getColor() == Color.WHITE) {
                    whiteKing = true;
                } else {
                    blackKing = true;
                }
            }
        }

        if (!whiteKing) {
            setStateOfGame(StateOfGame.BLACK_PLAYER_WIN);
        } else if (!blackKing) {
            setStateOfGame(StateOfGame.WHITE_PLAYER_WIN);
        }
    }

    /**
     * Sets initial pieces of the game.
     */
    private void setInitialSet() {
        getBoard().putPieceOnBoard(new Position('e', 1), new Piece(Color.WHITE, PieceType.KING));
        getBoard().putPieceOnBoard(new Position('d', 1), new Piece(Color.WHITE, PieceType.QUEEN));
        getBoard().putPieceOnBoard(new Position('a', 1), new Piece(Color.WHITE, PieceType.ROOK));
        getBoard().putPieceOnBoard(new Position('h', 1), new Piece(Color.WHITE, PieceType.ROOK));
        getBoard().putPieceOnBoard(new Position('b', 1), new Piece(Color.WHITE, PieceType.KNIGHT));
        getBoard().putPieceOnBoard(new Position('g', 1), new Piece(Color.WHITE, PieceType.KNIGHT));
        getBoard().putPieceOnBoard(new Position('c', 1), new Piece(Color.WHITE, PieceType.BISHOP));
        getBoard().putPieceOnBoard(new Position('f', 1), new Piece(Color.WHITE, PieceType.BISHOP));

        getBoard().putPieceOnBoard(new Position('e', 8), new Piece(Color.BLACK, PieceType.KING));
        getBoard().putPieceOnBoard(new Position('d', 8), new Piece(Color.BLACK, PieceType.QUEEN));
        getBoard().putPieceOnBoard(new Position('a', 8), new Piece(Color.BLACK, PieceType.ROOK));
        getBoard().putPieceOnBoard(new Position('h', 8), new Piece(Color.BLACK, PieceType.ROOK));
        getBoard().putPieceOnBoard(new Position('b', 8), new Piece(Color.BLACK, PieceType.KNIGHT));
        getBoard().putPieceOnBoard(new Position('g', 8), new Piece(Color.BLACK, PieceType.KNIGHT));
        getBoard().putPieceOnBoard(new Position('c', 8), new Piece(Color.BLACK, PieceType.BISHOP));
        getBoard().putPieceOnBoard(new Position('f', 8), new Piece(Color.BLACK, PieceType.BISHOP));

        for (int i = 0; i < getBoard().getSize(); i++) {
            getBoard().putPieceOnBoard(new Position(i, 1), new Piece(Color.WHITE, PAWN));
            getBoard().putPieceOnBoard(new Position(i, 6), new Piece(Color.BLACK, PAWN));
        }
    }
}
