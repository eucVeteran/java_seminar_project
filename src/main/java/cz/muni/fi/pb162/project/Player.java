package cz.muni.fi.pb162.project;

/**
 * Class Player to represent players. Name sets once and never changes.
 *
 * @author Azizbek Toshpulatov
 */
public class Player {
    private String name;
    Player(String newName){
        setName(newName);
    }

    public String getName(){
        return name;
    }
    private void setName(String newName){
        name = newName;
    }
}
