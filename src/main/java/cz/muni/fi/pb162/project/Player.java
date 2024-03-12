package cz.muni.fi.pb162.project;

/**
 * Record Player to represent players. Name and color sets once and never changes.
 *
 * @param name  name of a player.
 * @param color color of game pieces of a player.
 * @author Azizbek Toshpulatov
 */
public record Player(String name, Color color) {
    /**
     * Overridden method toString that returns string representation
     * of a player, e.g. "Eva-WHITE", "Tom-BLACK".
     *
     * @return {name}-{color}.
     */
    @Override
    public String toString() {
        return name + "-" + color;
    }
}
