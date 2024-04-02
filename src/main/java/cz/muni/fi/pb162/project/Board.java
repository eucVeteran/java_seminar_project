package cz.muni.fi.pb162.project;

import java.util.Arrays;

/**
 * Class Board represents a board with sides length of specific size.
 *
 * @author Azizbek Toshpulatov
 */
public class Board {
    /**
     * Default size of the board
     */
    public static final int DEF_SIZE = 8;

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
     * Constructs a board with a default size of {@link Board#DEF_SIZE}.
     */
    public Board() {
        this(DEF_SIZE);
    }

    /**
     * Returns a position of a piece with the same id on the board.
     *
     * @param id id of a piece to find.
     * @return position of a piece with the same id.
     */
    public Position findCoordinatesOfPieceById(long id) {
        for (int column = 0; column < getSize(); column++) {
            for (int line = 0; line < getSize(); line++) {
                Position currentPos = new Position(column, line);
                Piece piece = getPiece(currentPos);

                if (piece != null && piece.getId() == id) {
                    return currentPos;
                }
            }
        }
        return null;
    }

    /**
     * Checks if a position is within the bounds of the board.
     *
     * @param position the position to check
     * @return <code>true</code> if the position is within the bounds of the board, otherwise <code>false</code>.
     */
    public boolean inRange(Position position) {
        int column = position.column();
        int line = position.line();

        return (column >= 0 && column < getSize()) && (line >= 0 && line < getSize());
    }

    /**
     * Checks if a position on the board is empty or position is out of bounds.
     *
     * @param position the position to check
     * @return <code>true</code> if the position is within
     * the bounds of the board and empty or the position is out of the bounds of the board,
     * otherwise <code>false</code>.
     */
    public boolean isEmpty(Position position) {
        return getPiece(position) == null;
    }

    /**
     * Places a piece on the board at the specified position.
     *
     * @param position the position to place the piece
     * @param piece    the piece to place on the board
     */
    public void putPieceOnBoard(Position position, Piece piece) {
        board[position.column()][position.line()] = piece;
    }

    /**
     * Returns the current layout of pieces on the board.
     *
     * @return the current layout of pieces on the board.
     */
    @Override
    public String toString() {
        var result = new StringBuilder();

        letterColumns(result);
        addRestLayout(result);

        return result.toString();
    }

    /**
     * Helper for {@link Board#toString()}.
     * Adds top of the board, i.e. letters of columns.
     *
     * @param result not completed layout with completed first line.
     */
    private void letterColumns(StringBuilder result) {
        result.append(' ');
        for (int j = 0; j < getSize(); j++) {
            char letter = (char) ('A' + j);
            result.append("   ").append(letter);
        }
        result.append(' ').append(System.lineSeparator());
        result.append("  ").append("----".repeat(getSize())).append(System.lineSeparator());
    }

    /**
     * Helper for {@link Board#toString()}.
     * Adds rest of the board, i.e. line numbers, board squares, pieces.
     *
     * @param result completed layout of a board.
     */
    private void addRestLayout(StringBuilder result) {
        for (int i = 1; i < getSize() + 1; i++) {
            int line = getSize() + 1 - i;
            result.append(getSize() - i + 1).append(" |");
            for (int j = 0; j < getSize(); j++) {
                var currentPiece = getPiece(new Position(j, line - 1));
                if (currentPiece != null) {
                    result.append(' ').append(currentPiece);
                } else {
                    result.append("  ");
                }
                result.append(" |");
            }
            result.append(System.lineSeparator());
            result.append("  ").append("----".repeat(getSize())).append(System.lineSeparator());
        }
        result.deleteCharAt(result.length() - 1);
    }

    /**
     * Returns all the pieces on the board.
     *
     * @return all pieces on the board.
     */
    public Piece[] getAllPiecesFromBoard() {
        var allPieces = new Piece[getSize() * getSize()];
        int index = 0;
        for (int i = 0; i < getSize(); i++) {
            for (int j = 0; j < getSize(); j++) {
                Piece currentPiece = board[i][j];
                if (currentPiece != null) {
                    allPieces[index] = currentPiece;
                    index++;
                }
            }
        }
        return Arrays.copyOf(allPieces, index);
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
     * Gets the piece at the specified position on the board.
     *
     * @param position the position to get the piece from
     * @return the piece at the specified position, or <code>null</code> if there is no piece.
     */
    public Piece getPiece(Position position) {
        return inRange(position) ? board[position.column()][position.line()] : null;
    }
}
