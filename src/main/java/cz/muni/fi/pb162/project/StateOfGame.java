package cz.muni.fi.pb162.project;

/**
 * Represents game states.
 *
 * @author Azizbek Toshpulatov
 */
public enum StateOfGame {
    WHITE_PLAYER_WIN, BLACK_PLAYER_WIN, PAT, PLAYING;

    /**
     * Returns the state in lowercase, e.g. PLAYING => "playing".
     *
     * @return the state in lowercase.
     */
    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
