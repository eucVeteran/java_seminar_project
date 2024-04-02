package cz.muni.fi.pb162.project;

/**
 * Functional interface for the {@code Prototype} design pattern.
 *
 * @param <T> what type we want to copy.
 * @author Alzbeta Strompova
 */
public interface Prototype<T> {

    /**
     * Method creates copy.
     *
     * @return copy of an object with new unique ID.
     */
    T makeClone();
}
