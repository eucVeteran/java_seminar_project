package cz.muni.fi.pb162.project;

/**
 * Represents a basic game interface.
 *
 * @author Azizbek Toshpulatov
 */
public interface Playable {
    /**
     * Moves the piece from the old position to the new position on the board.
     * If there is no piece at the source position or the positions are wrong, then the method does nothing
     *
     * @param oldPosition old position.
     * @param newPosition new position.
     */
    void move(Position oldPosition, Position newPosition);
}
