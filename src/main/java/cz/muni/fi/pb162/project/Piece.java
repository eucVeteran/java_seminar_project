package cz.muni.fi.pb162.project;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

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

    /**
     * Return a first letter of a piece type, e.g. KING => "K".
     *
     * @return a first letter of a piece type.
     */
    @Override
    public String toString() {
        return String.valueOf(pieceType.toString().charAt(0));
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

    /**
     * Returns {@code true} if they are of the same {@link Color} and {@link PieceType}, regardless of the id.
     * Otherwise, returns false.
     *
     * @param obj {@code object} to compare.
     * @return {@code true} if they have same {@link Color} and {@link PieceType}.
     */
    @Override
    public boolean equals(Object obj) {
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        return (this.getColor() == ((Piece) obj).getColor() &&
                this.getPieceType() == ((Piece) obj).getPieceType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getColor(), getPieceType());
    }
}
