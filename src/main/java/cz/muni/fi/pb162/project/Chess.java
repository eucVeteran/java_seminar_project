package cz.muni.fi.pb162.project;

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
            getBoard().putPieceOnBoard(new Position(i, 1), new Piece(Color.WHITE, PieceType.PAWN));
            getBoard().putPieceOnBoard(new Position(i, 6), new Piece(Color.BLACK, PieceType.PAWN));
        }
    }
}
