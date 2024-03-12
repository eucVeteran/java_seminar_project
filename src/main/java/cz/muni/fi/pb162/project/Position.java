package cz.muni.fi.pb162.project;


/**
 * Position represents position (column, line). Takes first argument as a number or a letter.
 *
 * @param column the column of the position starting from 0.
 * @param line   the line of the position starting from 0.
 * @author Azizbek Toshpulatov
 */
public record Position(int column, int line) {
    private static final int UNICODE_A_VAL = 97;

    /**
     * Constructs a Position object with the specified column character and line number.
     *
     * @param columnChar the column character (a-z) of the position
     * @param line       the line of the position starting from 1
     */
    public Position(char columnChar, int line) {
        this(columnChar - UNICODE_A_VAL, line - 1);
    }

    /**
     * Adds 2 positions and returns a new position, e.g. (1, 3) + (1, 3) = (2, 6).
     *
     * @param toAdd position to add.
     * @return a new position.
     */
    public Position add(Position toAdd) {
        return new Position(this.column + toAdd.column, this.line + toAdd.line);
    }

    /**
     * Returns a human-readable string representation of the position, e.g., "a1"
     *
     * @return a string representation of the position
     */
    @Override
    public String toString() {
        return String.valueOf((char) (this.column() + UNICODE_A_VAL)) + (this.line() + 1);
    }
}