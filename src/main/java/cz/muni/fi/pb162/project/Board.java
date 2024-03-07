package cz.muni.fi.pb162.project;

/**
 * Class Board represents a board with sides length of specific size.
 *
 * @author Azizbek Toshpulatov
 */
public class Board {
    private final Piece[][] board;

    /**
     * Constructs a Board object with the specified size.
     *
     * @param size the size of the board
     */
    public Board(int size) {
        board = new Piece[size][size];
    }

    /**
     * Returns the size of the board.
     *
     * @return the size of the board
     */
    public int getSize() {
        return board.length;
    }

    /**
     * Checks if a position is within the bounds of the board.
     *
     * @param position the position to check
     * @return <code>true</code> if the position is within the bounds of the board, otherwise <code>false</code>.
     */
    public boolean inRange(Position position) {
        int column = position.getColumn();
        int line = position.getLine();

        return (column >= 0 && column < getSize()) && (line >= 0 && line < getSize());
    }

    /**
     * Checks if a position on the board is empty or position is out of bounds.
     *
     * @param position the position to check
     * @return <code>true</code> if the position is within
     *         the bounds of the board and empty, otherwise <code>false</code>.
     */
    public boolean isEmpty(Position position) {
        return getPiece(position) == null;
    }

    /**
     * Gets the piece at the specified position on the board.
     *
     * @param position the position to get the piece from
     * @return the piece at the specified position, or <code>null</code> if there is no piece.
     */
    public Piece getPiece(Position position) {
        return inRange(position) ? board[position.getColumn()][position.getLine()] : null;
    }

    /**
     * Places a piece on the board at the specified position.
     *
     * @param position the position to place the piece
     * @param piece    the piece to place on the board
     */
    public void putPieceOnBoard(Position position, Piece piece) {
        board[position.getColumn()][position.getLine()] = piece;
    }
}
