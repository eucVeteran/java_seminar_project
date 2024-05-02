package cz.muni.fi.pb162.project;

import cz.muni.fi.pb162.project.exceptions.MissingPlayerException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import static cz.muni.fi.pb162.project.Color.WHITE;
import static cz.muni.fi.pb162.project.Color.BLACK;
import static cz.muni.fi.pb162.project.PieceType.KING;
import static cz.muni.fi.pb162.project.PieceType.QUEEN;
import static cz.muni.fi.pb162.project.PieceType.PAWN;
import static cz.muni.fi.pb162.project.PieceType.ROOK;
import static cz.muni.fi.pb162.project.PieceType.KNIGHT;
import static cz.muni.fi.pb162.project.PieceType.BISHOP;
import static cz.muni.fi.pb162.project.StateOfGame.BLACK_PLAYER_WIN;
import static cz.muni.fi.pb162.project.StateOfGame.WHITE_PLAYER_WIN;

/**
 * Represent a game of chess.
 *
 * @author Azizbek Toshpulatov
 */
public class Chess extends Game {

    /**
     * Constructs a chess game with 2 players and a board of size {@link Board#DEF_SIZE} x {@link Board#DEF_SIZE}.
     *
     * @param playerOne first player.
     * @param playerTwo second player.
     */
    public Chess(Player playerOne, Player playerTwo) {
        super(playerOne, playerTwo);
        setInitialSet();
    }

    /**
     * Constructs a chess game with 2 players and a given board.
     *
     * @param playerOne first player.
     * @param playerTwo second player.
     * @param board     given board.
     */
    private Chess(Player playerOne, Player playerTwo, Board board) {
        super(playerOne, playerTwo, board);
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

        if (inTheEnd(newPosition, currentPiece) && currentPiece.getPieceType() == PAWN) {
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
//        boolean whiteKing = false;
//        boolean blackKing = false;
//        Piece[] allPieces = getBoard().getAllPiecesFromBoard();
//
//        for (Piece currentPiece : allPieces) {
//            if (currentPiece.getPieceType() == KING) {
//                if (currentPiece.getColor() == WHITE) {
//                    whiteKing = true;
//                } else {
//                    blackKing = true;
//                }
//            }
//        }
//
//        if (!whiteKing) {
//            setStateOfGame(BLACK_PLAYER_WIN);
//        } else if (!blackKing) {
//            setStateOfGame(WHITE_PLAYER_WIN);
//        }

        ArrayList<Piece> kings = Arrays.stream(getBoard().getAllPiecesFromBoard())
                .filter(piece -> piece.getPieceType() == KING)
                .collect(Collectors.toCollection(ArrayList::new));

        if (kings.size() == 1) {
            setStateOfGame(kings.get(0).getColor() == WHITE ? WHITE_PLAYER_WIN : BLACK_PLAYER_WIN);
        }
    }

    /**
     * Sets initial pieces of the game.
     */
    private void setInitialSet() {
        getBoard().putPieceOnBoard(new Position('e', 1), new Piece(WHITE, KING));
        getBoard().putPieceOnBoard(new Position('d', 1), new Piece(WHITE, QUEEN));
        getBoard().putPieceOnBoard(new Position('a', 1), new Piece(WHITE, ROOK));
        getBoard().putPieceOnBoard(new Position('h', 1), new Piece(WHITE, ROOK));
        getBoard().putPieceOnBoard(new Position('b', 1), new Piece(WHITE, KNIGHT));
        getBoard().putPieceOnBoard(new Position('g', 1), new Piece(WHITE, KNIGHT));
        getBoard().putPieceOnBoard(new Position('c', 1), new Piece(WHITE, BISHOP));
        getBoard().putPieceOnBoard(new Position('f', 1), new Piece(WHITE, BISHOP));

        getBoard().putPieceOnBoard(new Position('e', 8), new Piece(BLACK, KING));
        getBoard().putPieceOnBoard(new Position('d', 8), new Piece(BLACK, QUEEN));
        getBoard().putPieceOnBoard(new Position('a', 8), new Piece(BLACK, ROOK));
        getBoard().putPieceOnBoard(new Position('h', 8), new Piece(BLACK, ROOK));
        getBoard().putPieceOnBoard(new Position('b', 8), new Piece(BLACK, KNIGHT));
        getBoard().putPieceOnBoard(new Position('g', 8), new Piece(BLACK, KNIGHT));
        getBoard().putPieceOnBoard(new Position('c', 8), new Piece(BLACK, BISHOP));
        getBoard().putPieceOnBoard(new Position('f', 8), new Piece(BLACK, BISHOP));

        for (int i = 0; i < getBoard().getSize(); i++) {
            getBoard().putPieceOnBoard(new Position(i, 1), new Piece(WHITE, PAWN));
            getBoard().putPieceOnBoard(new Position(i, 6), new Piece(BLACK, PAWN));
        }
    }

    /**
     * Returns a builder for {@link Chess} class.
     *
     * @author Azizbek Toshpulatov
     */
    public static class Builder extends GameBuilder<Chess> {
        @Override
        public Chess build() {
            if (getPlayerOne() == null || getPlayerTwo() == null) {
                throw new MissingPlayerException("A game must have 2 players");
            }
            return new Chess(getPlayerOne(), getPlayerTwo(), getBoard());
        }
    }
}
