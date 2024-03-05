package cz.muni.fi.pb162.project;

/**
 * Class Position represents position (column, line) using int
 *
 * @author Azizbek Toshpulatov
 */
public class Position {
    private final int column;
    private final int line;

    /**
     * Constructs a Position object with the specified column and line.
     *
     * @param column the column of the position
     * @param line   the line of the position
     */
    public Position(int column, int line) {
        this.column = column;
        this.line = line;
    }

    /**
     * Constructs a Position object with the specified column character and line number.
     *
     * @param columnChar the column character (a-z) of the position
     * @param line       the line of the position
     */
    public Position(char columnChar, int line) {
        this(columnChar - 97, line - 1);
    }

    /**
     * Returns a string representation of the position in the format "columnLine".
     *
     * @return a string representation of the position
     */
    @Override
    public String toString() {
        return (String.valueOf((char) (this.column + 97))) + (this.line + 1);
    }

    /**
     * Returns the column of the position.
     *
     * @return the column of the position
     */
    public int getColumn() {
        return this.column;
    }

    /**
     * Returns the line of the position.
     *
     * @return the line of the position
     */
    public int getLine() {
        return this.line;
    }
}
