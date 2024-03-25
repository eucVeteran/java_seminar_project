package cz.muni.fi.pb162.project;

import java.util.Scanner;

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

    // returns a new position object from a user input. 0 is from, 3 is to.
    private Position getInputFromPlayer(int i) {
        Scanner scanner = new Scanner(System.in);
        var position = scanner.next().trim();
        char column = position.charAt(i);
        int line = Integer.parseInt(String.valueOf(position.charAt(i + 1)));
        return new Position(column, line);
    }

    /**
     * Demonstrates the gameplay. It loops until the game is ended.
     * Each loop it finds out which player is next, increases the round by one
     * and makes a move. The state of the game is updated automatically ({@link Game#updateStatus()}).
     */
    @Override
    public void play() {
        Position from = getInputFromPlayer(0);
        Position to = getInputFromPlayer(3);
        move(from, to);
        round++;

        if (stateOfGame == StateOfGame.PLAYING) {
            play();
        }
    }

    /**
     * Checks whether the game has finished and then changes the status (the winner) of the game appropriately.
     */
    public abstract void updateStatus();

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
