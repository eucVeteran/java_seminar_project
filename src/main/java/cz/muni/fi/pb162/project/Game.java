package cz.muni.fi.pb162.project;

/**
 * Represents a game of two players.
 * Each game is played by two players on the board of size {@link Board#DEF_SIZE} x {@link Board#DEF_SIZE}.
 * Neither the player nor the board is changed during the game.
 *
 * @author Azizbek Toshpulatov
 */
public class Game implements Playable {
    private final Player playerOne;
    private final Player playerTwo;
    private final Board board;
    private StateOfGame stateOfGame = StateOfGame.PLAYING;
    private int round = 0;

    /**
     * Constructs a game with 2 players and a board of size {@link Board#DEF_SIZE} x {@link Board#DEF_SIZE}.
     *
     * @param playerOne first player.
     * @param playerTwo second player.
     */
    public Game(Player playerOne, Player playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.board = new Board();
    }

    /**
     * Moves the piece from the old position to the new position on the board.
     * If there is no piece at the source position or the positions are wrong, then the method does nothing
     *
     * @param oldPosition old position.
     * @param newPosition new position.
     */
    @Override
    public void move(Position oldPosition, Position newPosition) {
        if (board.getPiece(oldPosition) == null) {
            return;
        }

        Piece piece = board.getPiece(oldPosition);
        board.putPieceOnBoard(oldPosition, null);
        board.putPieceOnBoard(newPosition, piece);
    }

    /**
     * Returns the player whose turn it is.
     *
     * @return the player whose turn it is.
     */
    public Player getCurrentPlayer() {
        if (round % 2 == 0) {
            return playerOne.color() == Color.WHITE ? playerOne : playerTwo;
        }
        return playerOne.color() == Color.BLACK ? playerOne : playerTwo;
    }

    public StateOfGame getStateOfGame() {
        return stateOfGame;
    }

    public void setStateOfGame(StateOfGame stateOfGame) {
        this.stateOfGame = stateOfGame;
    }

    public Board getBoard() {
        return board;
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }
}
