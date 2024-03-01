package cz.muni.fi.pb162.project;

import cz.muni.fi.pb162.project.helper.BasicRulesTester;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Alzbeta Strompova
 */
public class PositionTest {

    private final Position one = new Position(1, 3);
    private final Position two = new Position(-2, 0);
    private final Position three = new Position(3, 9);
    private final Position four = new Position(0, -23);
    private final Position five = new Position(15, -4);
    private final Position six = new Position(-7, 7);

    @Test
    void attributesAndMethodsAmount() {
        BasicRulesTester.attributesAmount(Position.class, 2);
        BasicRulesTester.methodsAmount(Position.class, 4);
    }

    @Test
    void testValues() {
        assertEquals(0, new Position('a', 8).getColumn());
        assertEquals(6, new Position('g', 8).getColumn());
        assertEquals(7, new Position('a', 8).getLine());
        assertEquals(7, new Position('g', 8).getLine());
    }

    @Test
    void testToString() {
        assertEquals("a8", new Position('a', 8).toString());
        assertEquals("g6", new Position('g', 6).toString());
    }
}
