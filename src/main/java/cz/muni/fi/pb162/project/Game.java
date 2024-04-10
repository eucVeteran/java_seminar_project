package cz.muni.fi.pb162.project;

import java.util.Scanner;

import static cz.muni.fi.pb162.project.Color.BLACK;
import static cz.muni.fi.pb162.project.Color.WHITE;
import static cz.muni.fi.pb162.project.StateOfGame.PLAYING;

/**
 * Represents a game of two players.
 * Each game is played by two players on the board of size {@link Board#DEF_SIZE} x {@link Board#DEF_SIZE}.
 * Neither the player nor the board is changed during the game.
 *
 * @author Azizbek Toshpulatov
 */
public abstract class Game implements Playable {
    private final Player playerOne;
    private final Player playerTwo;
    private final Board board;
    private StateOfGame stateOfGame = PLAYING;
    private int round = 0;
    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * Constructs a game with 2 players and a board of size {@link Board#DEF_SIZE} x {@link Board#DEF_SIZE}.
     *
     * @param playerOne first player.
     * @param playerTwo second player.
     */
    public Game(Player playerOne, Player playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        board = new Board();
    }

    /**
     * Constructs a game with 2 players and a given board.
     *
     * @param playerOne first player.
     * @param playerTwo second player.
     * @param board built board.
     */
    public Game(Player playerOne, Player playerTwo, Board board) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.board = board;
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
     * Returns {@code true} if the piece is either on the first line or the last line.
     *
     * @param newPosition  new {@link Position}.
     * @param currentPiece current {@link Piece}.
     * @return {@code true} if the piece is either on the first line or the last line.
     */
    protected boolean inTheEnd(Position newPosition, Piece currentPiece) {
        return (newPosition.line() + 1 == getBoard().getSize() && currentPiece.getColor() == WHITE) ||
                (newPosition.line() == 0 && currentPiece.getColor() == BLACK);
    }

    /**
     * Returns input from a player, e.g. a2 => new {@link Position} object.
     *
     * @return input from a player, e.g. a2 => new {@link Position} object.
     */
    private Position getInputFromPlayer() {
        var position = SCANNER.next().trim();
        char column = position.charAt(0);
        int line = Integer.parseInt(String.valueOf(position.charAt(1)));
        return new Position(column, line);
    }

    @Override
    public void play() {
        while (stateOfGame == PLAYING) {
            System.out.println("State of game: " + stateOfGame);

            Player currentPlayer = getCurrentPlayer();
            System.out.println(currentPlayer.toString() + "'s turn.");

            Position from = getInputFromPlayer();
            Position to = getInputFromPlayer();

            round++;
            move(from, to);
            updateStatus();
        }
        System.out.println("Game is ended. " + stateOfGame.toString());
    }

    /**
     * Checks whether the game has finished and then changes the status (the winner) of the game appropriately.
     */
    protected abstract void updateStatus();

    /**
     * Returns the player whose turn it is.
     *
     * @return the player whose turn it is.
     */
    public Player getCurrentPlayer() {
        if (round % 2 == 0) {
            return playerOne.color() == WHITE ? playerOne : playerTwo;
        }
        return playerOne.color() == BLACK ? playerOne : playerTwo;
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

    /**
     * Returns {@code true} if the first and second players are the same. Game state and board state do not matter.
     *
     * @param obj object to compare.
     * @return {@code true} if the first and second players are the same. Game state and board state do not matter.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Game)) {
            return false;
        }

        return getPlayerOne() == ((Game) obj).getPlayerOne() && getPlayerTwo() == ((Game) obj).getPlayerTwo();
    }

    @Override
    public int hashCode() {
        int result = 17;
        if (getPlayerOne() != null) {
            result = 31 * result + getPlayerOne().hashCode();
        }
        if (getPlayerTwo() != null) {
            result = 31 * result + getPlayerTwo().hashCode();
        }
        return result;
    }
}
