package cz.muni.fi.pb162.project;

import cz.muni.fi.pb162.project.moves.*;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * Class Piece represents a generic piece of a game, that has its color, type and id.
 *
 * @author Azizbek Toshpulatov
 */
public class Piece implements Prototype<Piece> {
    private static final AtomicLong ID_VALUES = new AtomicLong(0);

    private final Color color;
    private final PieceType pieceType;
    private final long id = ID_VALUES.incrementAndGet();
    private final List<Move> movementStrategies;
    private static final Map<PieceType, Map<Color, String>> PIECE_ICONS = new HashMap<>();
    private static final String PIECES_FILE = "pieces.txt";

    /**
     * Creates a new instance of Piece with given {@code color} and {@code pieceType}.
     * It also automatically sets a unique id number to the attribute {@link Piece#id}.
     *
     * @param color     color of a piece.
     * @param pieceType piece type.
     */
    public Piece(Color color, PieceType pieceType) throws NullPointerException {
        this.color = Objects.requireNonNull(color);
        this.pieceType = Objects.requireNonNull(pieceType);
        switch (pieceType) {
            case KING -> movementStrategies = List.of(new Straight(1), new Diagonal(1));
            case QUEEN -> movementStrategies = List.of(new Straight(), new Diagonal());
            case BISHOP -> movementStrategies = List.of(new Diagonal());
            case ROOK -> movementStrategies = List.of(new Straight());
            case KNIGHT -> movementStrategies = List.of(new Knight());
            case PAWN -> movementStrategies = List.of(new Pawn());
            case DRAUGHTS_KING -> movementStrategies = List.of(new Diagonal(1), new Jump());
            default -> movementStrategies = List.of(new Diagonal(1, true), new Jump(true));
        }
    }

    /**
     * Creates a copied instance of another piece, but ID is unique.
     *
     * @param pattern another piece to be cloned.
     */
    public Piece(Piece pattern) {
        this(pattern.color, pattern.pieceType);
    }

    @Override
    public Piece makeClone() {
        return new Piece(this);
    }

    private void readIcons() throws IOException {
        FileInputStream is = new FileInputStream(PIECES_FILE);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line;
        while ((line = br.readLine()) != null) {
            String[] currentLine = line.split(":");
            Color color = Color.valueOf(currentLine[0]);
            PieceType pieceType = PieceType.valueOf(currentLine[1]);
            if (!PIECE_ICONS.containsKey(pieceType)) {
                PIECE_ICONS.put(pieceType, new HashMap<>());
            }
            PIECE_ICONS.get(pieceType).put(color, currentLine[2]);
        }
    }

    /**
     * Returns a {@link Piece} icon.
     *
     * @return a {@link Piece} icon.
     */
    @Override
    public String toString() {
        if (PIECE_ICONS.isEmpty()) {
            try {
                readIcons();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return PIECE_ICONS.get(pieceType).get(color);
//        return String.valueOf(pieceType.toString().charAt(0));
    }

    public Color getColor() {
        return color;
    }

    public long getId() {
        return this.id;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public List<Move> getMovementStrategies() {
        return movementStrategies;
    }

    /**
     * Finds and returns valid target positions on the game's board, taking into account the position of the piece.
     *
     * @param game game to work on.
     * @return a set of valid positions.
     */
    public Set<Position> getAllPossibleMoves(Game game) {
        Position currentPos = game.getBoard().findCoordinatesOfPieceById(getId());

//        Set<Position> result = new HashSet<>();
//
//        for (Move strategy : movementStrategies) {
//            Set<Position> allowedMoves = strategy.getAllowedMoves(game, currentPos);
//            result.addAll(allowedMoves);
//        }
//
//        return result;

        return movementStrategies
                .stream()
                .flatMap(strategy -> strategy.getAllowedMoves(game, currentPos).stream())
                .collect(Collectors.toSet());
    }


    /**
     * Returns {@code true} if they are of the same {@link Color} and {@link PieceType}, regardless of the id.
     * Otherwise, returns false.
     *
     * @param obj {@code object} to compare.
     * @return {@code true} if they have same {@link Color} and {@link PieceType}.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        Piece otherPiece = (Piece) obj;
        return (this.getColor() == otherPiece.getColor() &&
                this.getPieceType() == otherPiece.getPieceType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getColor(), getPieceType());
    }
}
