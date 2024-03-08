package cz.muni.fi.pb162.project;

/**
 * Functional interface for the {@code Prototype} design pattern.
 *
 * @author Alzbeta Strompova
 */
public interface Prototype {

    /**
     * Method creates copy.
     *
     * @return copy of an object with new unique ID.
     */
    Piece makeClone();
}
