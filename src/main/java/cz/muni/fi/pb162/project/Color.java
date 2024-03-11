package cz.muni.fi.pb162.project;

/**
 * Enum Color represents 2 game colors: WHITE and BLACK.
 *
 * @author Azizbek Toshpulatov
 */
public enum Color {
    BLACK, WHITE;

    /**
     * Method getOppositeColor returns the opposite color.
     *
     * @return opposite color.
     */
    public Color getOppositeColor() {
        return (this == BLACK) ? WHITE : BLACK;
    }
}
