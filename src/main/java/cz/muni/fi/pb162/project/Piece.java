package cz.muni.fi.pb162.project;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Class Piece represents a generic piece of a game, that has its color, type and id.
 *
 * @author Azizbek Toshpulatov
 */
public class Piece {
    private static final AtomicLong ID_VALUES = new AtomicLong(0);

    private final Color color;
    private final PieceType pieceType;
    private final long id = ID_VALUES.incrementAndGet();

    /**
     * Creates a new instance of Piece with given {@code color} and {@code pieceType}.
     * It also automatically sets a unique id number to the attribute {@link Piece#id}.
     *
     * @param color     color of a piece.
     * @param pieceType piece type.
     */
    public Piece(Color color, PieceType pieceType) {
        this.color = color;
        this.pieceType = pieceType;
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
}
