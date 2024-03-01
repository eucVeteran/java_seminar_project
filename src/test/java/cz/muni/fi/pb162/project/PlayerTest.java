package cz.muni.fi.pb162.project;

import cz.muni.fi.pb162.project.helper.BasicRulesTester;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTest {

    @Test
    void attributesAndMethods() {
        BasicRulesTester.attributesAmount(Player.class, 1);
        BasicRulesTester.methodsAmount(Player.class, 1);
    }

    @Test
    void getName() {
        var player = new Player("test");
        assertEquals("test", new Player("test").getName());
        assertEquals("", new Player("").getName());
    }

}
