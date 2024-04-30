package cz.muni.fi.pb162.project;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Represents a {@link Game} {@link Tournament}, which stores the {@link Game} {@link Board} history
 * and allows to find some player from all games.
 *
 * @author Azizbek Toshpulatov
 */
public class Tournament {
    private final Map<Game, Collection<Board>> tournament = new HashMap<>();

    /**
     * Returns a history of a {@link Game} {@link Board}.
     *
     * @param game a {@link Game} whose history needs to be returned.
     * @return a history of a {@link Game} {@link Board}.
     */
    public Collection<Board> getGameHistory(Game game) {
        return tournament.get(game) == null ? new ArrayList<>() : tournament.get(game);
    }

    /**
     * Stores a current {@link Game} {@link Board} layout in that games history.
     *
     * @param game a {@link Game} whose layout will be stored.
     */
    public void storeGameState(Game game) {
        Board currentBoardState = game.getBoard().makeClone();
        tournament.computeIfAbsent(game, k -> new ArrayList<>());
        var statesOfGame = tournament.get(game);
        statesOfGame.add(currentBoardState);
    }

    /**
     * Returns a list of games in which a certain {@link Player} participates.
     *
     * @param name a name of a {@link Player}.
     * @return a list of games in which the {@link Player} is participating.
     */
    public Collection<Game> findGamesOfPlayer(String name) {
//        Collection<Game> gamesWithThisPlayer = new ArrayList<>();
//        for (Game game : tournament.keySet()) {
//            if (name.equals(game.getPlayerOne().name()) || name.equals(game.getPlayerTwo().name())) {
//                gamesWithThisPlayer.add(game);
//            }
//        }
//        return gamesWithThisPlayer;

        return tournament.keySet()
                .stream()
                .filter(game ->
                        name.equals(game.getPlayerOne().name())
                                || name.equals(game.getPlayerTwo().name()))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
