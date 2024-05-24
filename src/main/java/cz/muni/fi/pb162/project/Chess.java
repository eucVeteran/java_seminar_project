package cz.muni.fi.pb162.project;

import cz.muni.fi.pb162.project.exceptions.MissingPlayerException;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import static cz.muni.fi.pb162.project.Color.WHITE;
import static cz.muni.fi.pb162.project.Color.BLACK;
import static cz.muni.fi.pb162.project.PieceType.KING;
import static cz.muni.fi.pb162.project.PieceType.QUEEN;
import static cz.muni.fi.pb162.project.PieceType.PAWN;
import static cz.muni.fi.pb162.project.PieceType.ROOK;
import static cz.muni.fi.pb162.project.PieceType.KNIGHT;
import static cz.muni.fi.pb162.project.PieceType.BISHOP;
import static cz.muni.fi.pb162.project.StateOfGame.BLACK_PLAYER_WIN;
import static cz.muni.fi.pb162.project.StateOfGame.WHITE_PLAYER_WIN;

/**
 * Represent a game of chess.
 *
 * @author Azizbek Toshpulatov
 */
public class Chess extends Game implements GameWritable {

    /**
     * Constructs a chess game with 2 players and a board of size {@link Board#DEF_SIZE} x {@link Board#DEF_SIZE}.
     *
     * @param playerOne first player.
     * @param playerTwo second player.
     */
    public Chess(Player playerOne, Player playerTwo) {
        super(playerOne, playerTwo);
        setInitialSet();
    }

    /**
     * Constructs a chess game with 2 players and a given board.
     *
     * @param playerOne first player.
     * @param playerTwo second player.
     * @param board     given board.
     */
    Chess(Player playerOne, Player playerTwo, Board board) {
        super(playerOne, playerTwo, board);
    }

    /**
     * Moves the piece from the old position to the new position on the board.
     * If the moved piece is a pawn and is to be placed on the last (opposite) line of the board,
     * then the piece turns into a queen.
     * If there is no piece at the source position or the positions are wrong, then the method does nothing.
     *
     * @param oldPosition old position.
     * @param newPosition new position.
     */
    @Override
    public void move(Position oldPosition, Position newPosition) {
        Piece currentPiece = getBoard().getPiece(oldPosition);

        if (inTheEnd(newPosition, currentPiece) && currentPiece.getPieceType() == PAWN) {
            getBoard().putPieceOnBoard(oldPosition, new Piece(currentPiece.getColor(), QUEEN));
        }

        super.move(oldPosition, newPosition);
    }

    /**
     * Checks whether the game has finished and then changes the status (the winner) of the game appropriately.
     * The game ends if there is only one king on the board.
     */
    @Override
    public void updateStatus() {
//        boolean whiteKing = false;
//        boolean blackKing = false;
//        Piece[] allPieces = getBoard().getAllPiecesFromBoard();
//
//        for (Piece currentPiece : allPieces) {
//            if (currentPiece.getPieceType() == KING) {
//                if (currentPiece.getColor() == WHITE) {
//                    whiteKing = true;
//                } else {
//                    blackKing = true;
//                }
//            }
//        }
//
//        if (!whiteKing) {
//            setStateOfGame(BLACK_PLAYER_WIN);
//        } else if (!blackKing) {
//            setStateOfGame(WHITE_PLAYER_WIN);
//        }

        ArrayList<Piece> kings = Arrays.stream(getBoard().getAllPiecesFromBoard())
                .filter(piece -> piece.getPieceType() == KING)
                .collect(Collectors.toCollection(ArrayList::new));

        if (kings.size() == 1) {
            setStateOfGame(kings.get(0).getColor() == WHITE ? WHITE_PLAYER_WIN : BLACK_PLAYER_WIN);
        }
    }

    /**
     * Sets initial pieces of the game.
     */
    private void setInitialSet() {
        getBoard().putPieceOnBoard(new Position('e', 1), new Piece(WHITE, KING));
        getBoard().putPieceOnBoard(new Position('d', 1), new Piece(WHITE, QUEEN));
        getBoard().putPieceOnBoard(new Position('a', 1), new Piece(WHITE, ROOK));
        getBoard().putPieceOnBoard(new Position('h', 1), new Piece(WHITE, ROOK));
        getBoard().putPieceOnBoard(new Position('b', 1), new Piece(WHITE, KNIGHT));
        getBoard().putPieceOnBoard(new Position('g', 1), new Piece(WHITE, KNIGHT));
        getBoard().putPieceOnBoard(new Position('c', 1), new Piece(WHITE, BISHOP));
        getBoard().putPieceOnBoard(new Position('f', 1), new Piece(WHITE, BISHOP));

        getBoard().putPieceOnBoard(new Position('e', 8), new Piece(BLACK, KING));
        getBoard().putPieceOnBoard(new Position('d', 8), new Piece(BLACK, QUEEN));
        getBoard().putPieceOnBoard(new Position('a', 8), new Piece(BLACK, ROOK));
        getBoard().putPieceOnBoard(new Position('h', 8), new Piece(BLACK, ROOK));
        getBoard().putPieceOnBoard(new Position('b', 8), new Piece(BLACK, KNIGHT));
        getBoard().putPieceOnBoard(new Position('g', 8), new Piece(BLACK, KNIGHT));
        getBoard().putPieceOnBoard(new Position('c', 8), new Piece(BLACK, BISHOP));
        getBoard().putPieceOnBoard(new Position('f', 8), new Piece(BLACK, BISHOP));

        for (int i = 0; i < getBoard().getSize(); i++) {
            getBoard().putPieceOnBoard(new Position(i, 1), new Piece(WHITE, PAWN));
            getBoard().putPieceOnBoard(new Position(i, 6), new Piece(BLACK, PAWN));
        }
    }

    @Override
    public void write(OutputStream os) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os));

        writer.write(getPlayerOne().name() + "-" + getPlayerOne().color() + ";");
        writer.write(getPlayerOne().name() + "-" + getPlayerTwo().color());
        writer.newLine();

        for (int column = 0; column < getBoard().getSize(); column++) {
            for (int line = 0; line < getBoard().getSize(); line++) {
                Piece piece = getBoard().getPiece(new Position(column, line));
                if (piece == null) {
                    writer.write("_");
                } else {
                    writer.write(piece.getPieceType() + "," + piece.getColor());
                }

                if (line < getBoard().getSize() - 1) {
                    writer.write(";");
                }
            }
            writer.newLine();
        }
        writer.flush();
        writer.close();
    }

    @Override
    public void write(File file) throws IOException {
        write(new FileOutputStream(file));
    }

    /**
     * Returns a builder for {@link Chess} class.
     *
     * @author Azizbek Toshpulatov
     */
    public static class Builder extends GameBuilder<Chess> implements GameReadable {
        @Override
        public Chess build() {
            if (getPlayerOne() == null || getPlayerTwo() == null) {
                throw new MissingPlayerException("A game must have 2 players");
            }
            return new Chess(getPlayerOne(), getPlayerTwo(), getBoard());
        }

        private void readAndPutPieces(int column, int line, String pieceToRead) {
            Position positionToPut = new Position(column, line);

            if (pieceToRead.isEmpty() || pieceToRead.charAt(0) == '_') {
                getBoard().putPieceOnBoard(positionToPut, null);
                return;
            }

            String[] typeAndColor = pieceToRead.split(",");

            PieceType pieceType = PieceType.valueOf(typeAndColor[0]);
            Color pieceColor = Color.valueOf(typeAndColor[1]);

            getBoard().putPieceOnBoard(positionToPut, new Piece(pieceColor, pieceType));
        }

        private void readColumnOfBoard(String[] piecesInColumn, int columnNum) {
            for (int i = 0; i < piecesInColumn.length; i++) {
                readAndPutPieces(columnNum, i, piecesInColumn[i]);
            }
        }

        private void takePlayersFromHeader(BufferedReader br) throws IOException {
            String header = br.readLine();
            String[] firstAndSecond = header.split(";");

            if (firstAndSecond.length < 2) {
                throw new IOException("There has to be at least 2 players.");
            }

            for (int i = 0; i < 2; i++) {
                String[] currentPlayerData = firstAndSecond[0].split("-");
                if (currentPlayerData.length < 2) {
                    throw new IOException("Player must have a name and color.");
                }

                String playerName = currentPlayerData[0];
                Color playerColor;

                try {
                    playerColor = Color.valueOf(currentPlayerData[1]);
                } catch (IllegalArgumentException iae) {
                    throw new IOException("Incorrect color for player" + (i + 1));
                }

                addPlayer(new Player(playerName, playerColor));
            }
        }

        @Override
        public Builder read(InputStream is, boolean hasHeader) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            if (hasHeader) {
                try {
                    takePlayersFromHeader(br);
                } catch (Exception e) {
                    throw new IOException(e);
                }
            }

            for (int columnIndex = 0; columnIndex < getBoard().getSize(); columnIndex++) {
                String[] column;
                try {
                    column = br.readLine().split(";");
                } catch (Exception e) {
                    throw new IOException("Wrong line size in given data about the board", e);
                }

                if (column.length != getBoard().getSize()) {
                    throw new IOException("Wrong column size in given data about the board");
                }

                try {
                    readColumnOfBoard(column, columnIndex);
                } catch (Exception e) {
                    throw new IOException("There was an error while reading a column of given board: ", e);
                }
            }

            return this;
        }

        @Override
        public Builder read(InputStream is) throws IOException {
            return read(is, false);
        }

        @Override
        public Builder read(File file) throws IOException {
            return read(new FileInputStream(file), false);
        }

        @Override
        public Builder read(File file, boolean hasHeader) throws IOException {
            return read(new FileInputStream(file), hasHeader);
        }
    }
}
