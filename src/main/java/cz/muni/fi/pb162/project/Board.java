package cz.muni.fi.pb162.project;

import java.util.*;

/**
 * Class Board represents a board with sides length of specific size.
 *
 * @author Azizbek Toshpulatov
 */
public class Board implements Prototype<Board> {
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

    private void removeSimilarPieces(Piece pieceToRemove, List<Position> removedPositions) {
        for (int i = 0; i < getSize(); i++) {
            for (int j = 0; j < getSize(); j++) {
                Position positionToCheck = new Position(i, j);
                Piece currentPiece = getPiece(positionToCheck);
                if (currentPiece != null && currentPiece.equals(pieceToRemove)) {
                    putPieceOnBoard(positionToCheck, null);
                    removedPositions.add(positionToCheck);
                }
            }
        }
    }

    /**
     * Removes the same pieces from the board.
     *
     * @param toRemove pieces to remove.
     * @return a list of position of removed pieces.
     */
    public List<Position> removePieces(Set<Piece> toRemove) {
        List<Position> removedPositions = new ArrayList<>();
        for (Piece pieceToRemove : toRemove) {
            removeSimilarPieces(pieceToRemove, removedPositions);
        }
        return removedPositions.isEmpty() ? Collections.emptyList() : removedPositions;
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
     * Adds to given {@link SortedSet} of {@link Position}s an occupied positions on the {@link Board}.
     *
     * @param positions positions set to be updated.
     */
    protected void addOccupiedPositions(SortedSet<Position> positions) {
        for (int i = 0; i < getSize(); i++) {
            for (int j = 0; j < getSize(); j++) {
                Position pos = new Position(i, j);
                if (getPiece(pos) != null) {
                    positions.add(pos);
                }
            }
        }
    }

    /**
     * Returns a {@link SortedSet} of occupied positions, that are reverse sorted.
     *
     * @return a {@link SortedSet} of occupied positions, that are reverse sorted.
     */
    public SortedSet<Position> getOccupiedPositionsA() {
        PositionInverseComparator inverseComparator = new PositionInverseComparator();
        SortedSet<Position> result = new TreeSet<>(inverseComparator);
        addOccupiedPositions(result);
        return result;
    }

    /**
     * Returns a {@link SortedSet} of occupied positions, that are reverse sorted.
     *
     * @return a {@link SortedSet} of occupied positions, that are reverse sorted.
     */
    public SortedSet<Position> getOccupiedPositionsB() {
        Comparator<Position> comparator = new Comparator<Position>() {
            @Override
            public int compare(Position o1, Position o2) {
                return -1 * o1.compareTo(o2);
            }
        };
        SortedSet<Position> result = new TreeSet<>(comparator);
        addOccupiedPositions(result);
        return result;
    }

    /**
     * Returns a {@link SortedSet} of occupied positions, that are reverse sorted.
     *
     * @return a {@link SortedSet} of occupied positions, that are reverse sorted.
     */
    public SortedSet<Position> getOccupiedPositionsC() {
        SortedSet<Position> result = new TreeSet<>((x, y) -> -1 * x.compareTo(y));
        addOccupiedPositions(result);
        return result;
    }

    /**
     * Returns a {@link SortedSet} of occupied positions, that are reverse sorted.
     *
     * @return a {@link SortedSet} of occupied positions, that are reverse sorted.
     */
    public SortedSet<Position> getOccupiedPositionsD() {
        var reversed = Collections.reverseOrder();
        SortedSet<Position> result = new TreeSet<>(reversed);
        addOccupiedPositions(result);
        return result;
    }

    /**
     * Returns all the pieces on the board.
     *
     * @return all pieces on the board.
     */
    public Piece[] getAllPiecesFromBoard() {
//        var allPieces = new Piece[getSize() * getSize()];
//        int index = 0;
//        for (int i = 0; i < getSize(); i++) {
//            for (int j = 0; j < getSize(); j++) {
//                Piece currentPiece = board[i][j];
//                if (currentPiece != null) {
//                    allPieces[index] = currentPiece;
//                    index++;
//                }
//            }
//        }
//        return Arrays.copyOf(allPieces, index);

        return Arrays.stream(board)
                .flatMap(Arrays::stream)
                .filter(Objects::nonNull)
                .toArray(Piece[]::new);
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

    /**
     * Returns {@code true} if they are the same size and the same pieces (type and color) are at the same positions.
     *
     * @param obj board to compare.
     * @return true if they have the same size and the same pieces that are at the same position.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        Board otherBoard = (Board) obj;
        return this.getSize() == otherBoard.getSize() && Arrays.deepEquals(this.board, otherBoard.board);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getSize());
        result = 31 * result + Arrays.deepHashCode(board);
        return result;
    }

    @Override
    public Board makeClone() {
        Board cloneBoard = new Board(getSize());
        for (int i = 0; i < getSize(); i++) {
            for (int j = 0; j < getSize(); j++) {
                Piece currentPiece = board[i][j];
                if (currentPiece != null) {
                    cloneBoard.putPieceOnBoard(new Position(i, j), currentPiece.makeClone());
                }
            }
        }
        return cloneBoard;
    }
}
