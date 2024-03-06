package cz.muni.fi.pb162.project;

/**
 * Class Player to represent players. Name sets once and never changes.
 *
 * @author Azizbek Toshpulatov
 */
public class Player {
    private String name;

    /**
     * Class Player to represent players. Name sets once and never changes.
     *
     * @author Azizbek Toshpulatov
     */
    public Player(String name) {
        setName(name);
    }

    /**
     * Returns player's name.
     *
     * @return name of player.
     */
    public String getName(){
        return name;
    }

    /**
     * Sets player's name.
     *
     * @param name name of player.
     */
    private void setName(String name){
        this.name = name;
    }
}
