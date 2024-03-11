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
     * Default constructor of a player.
     */
    public Player {
    }

    /**
     * Overridden method toString that returns "-".
     *
     * @return "-".
     */
    @Override
    public String toString() {
        return name + "-" + color;
    }
}
