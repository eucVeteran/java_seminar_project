package cz.muni.fi.pb162.project;

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
