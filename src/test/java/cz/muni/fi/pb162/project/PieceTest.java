package cz.muni.fi.pb162.project;

import cz.muni.fi.pb162.project.helper.BasicRulesTester;
import org.junit.jupiter.api.Test;

/**
 * @author Alzbeta Strompova
 */
public class PieceTest {

    @Test
    void attributesAndMethodsAmount() {
        BasicRulesTester.attributesAmount(Piece.class, 0);
        BasicRulesTester.methodsAmount(Piece.class, 0);
    }
}
