package cz.muni.fi.pb162.project.moves;

import cz.muni.fi.pb162.project.*;
import cz.muni.fi.pb162.project.helper.BasicRulesTester;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Alzbeta Strompova
 */
class JumpTest {

    private final Player player1 = new Player("xxx", Color.WHITE);
    private final Player player2 = new Player("yyy", Color.BLACK);

    @Test
    void attributesAndMethods() {
        BasicRulesTester.attributesAmount(Jump.class, 2);
        BasicRulesTester.methodsAmount(Jump.class, 1);
    }

    @Test
    void inheritance() {
        BasicRulesTester.testInheritance(Move.class, Jump.class);
    }
}
