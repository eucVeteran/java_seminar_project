package cz.muni.fi.pb162.project;

import java.util.Comparator;

/**
 * Represents an inverse comparator to a {@link Position} comparator.
 *
 * @author Azizbek Toshpulatov
 */
public class PositionInverseComparator implements Comparator<Position> {
    @Override
    public int compare(Position o1, Position o2) {
        return -1 * o1.compareTo(o2);
    }
}
