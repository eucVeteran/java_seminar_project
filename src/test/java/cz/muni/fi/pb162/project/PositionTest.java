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
        BasicRulesTester.methodsAmount(Position.class, 6);
        BasicRulesTester.attributesFinal(Position.class, 3);

    }

    @Test
    void testValues() {
        assertEquals(0, new Position('a', 8).column());
        assertEquals(6, new Position('g', 8).column());
        assertEquals(7, new Position('a', 8).line());
        assertEquals(7, new Position('g', 8).line());
    }

    @Test
    void testToString() {
        assertEquals("a8", new Position('a', 8).toString());
        assertEquals("g6", new Position('g', 6).toString());
    }

    @Test
    void add() {
        var result = four.add(five);
        assertEquals(15, result.column());
        assertEquals(-27, result.line());
        var result2 = five.add(six);
        assertEquals(8, result2.column());
        assertEquals(3, result2.line());
        var result3 = six.add(five);
        assertEquals(result2.column(), result3.column());
        assertEquals(result2.line(), result3.line());
    }

}
