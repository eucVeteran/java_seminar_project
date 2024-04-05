package cz.muni.fi.pb162.project;

/**
 * Serves as a blueprint for creating instances of a specific type of game.
 *
 * @param <T> the type of game to be built, extending the abstract class Game.
 * @author Azizbek Toshpulatov
 */
public abstract class GameBuilder<T extends Game> {
    private Player playerOneBuilder;
    private Player playerTwoBuilder;
    private final Board boardBuilder = new Board();

    /**
     * Adds a {@link Player} to the game being built. If the first {@link Player} has already been added,
     * the added {@link Player} is considered the second {@link Player}.
     *
     * @param player the {@link Player} to be added to the game.
     * @return instance of the {@link GameBuilder}, allowing method chaining.
     */
    public GameBuilder<T> addPlayer(Player player) {
        if (playerOneBuilder != null) {
            playerTwoBuilder = player;
            return this;
        }
        playerOneBuilder = player;
        return this;
    }

    /**
     * Adds a piece to the builder board at the specified position.
     *
     * @param pos   {@link Position} on the board where the piece will be placed.
     * @param piece {@link Piece} to be placed on the board.
     * @return an instance of the GameBuilder, allowing method chaining.
     */
    public GameBuilder<T> addPieceToBoard(Position pos, Piece piece) {
        boardBuilder.putPieceOnBoard(pos, piece);
        return this;
    }

    /**
     * Builds and returns an instance of the game with the specified configurations.
     *
     * @return an instance of the game, based on the configurations provided.
     */
    public abstract T build();

    public Board getBoardBuilder() {
        return boardBuilder;
    }

    public Player getPlayerOneBuilder() {
        return playerOneBuilder;
    }

    public Player getPlayerTwoBuilder() {
        return playerTwoBuilder;
    }
}
