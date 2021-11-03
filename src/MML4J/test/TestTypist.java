package MML4J.test;

import MML4J.main.ast.ASTExpr;
import MML4J.main.exceptions.TypingException;
import MML4J.main.parser.Parser;
import MML4J.main.typist.Typist;
import MML4J.main.typist.type.ArrowType;
import MML4J.main.typist.type.SimpleType;
import MML4J.main.typist.type.Type;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestTypist {

    // ----- Attributes for the tests -----

    private static Parser parser;
    private static Typist typist;

    // ----- Init methods -----

    /**
     * Initialize the test suite
     */
    @BeforeAll
    static void init() {
        parser = new Parser();
        typist = new Typist();
    }

    // ----- Test methods -----

    /**
     * Test the availability of the test suite
     */
    @Test
    void testAvailable() {
        assertTrue(true);
    }

    /**
     * Test a simple term
     */
    @Test
    void test1() {
        try {
            ASTExpr expr = (ASTExpr) parser.parseString("fn(a){a}");
            Type real = typist.typeExpression(expr);

            Type expected = new ArrowType(new SimpleType(0), new SimpleType(0));
            assertEquals(expected, real);
        } catch (Exception e) {
            fail(e);
        }
    }

    /**
     * Test a less simple term
     */
    @Test
    void test2() {
        try {
            ASTExpr expr = (ASTExpr) parser.parseString("fn(a){fn(b){ a(b) }}");
            Type real = typist.typeExpression(expr);

            SimpleType t0 = new SimpleType(0);
            SimpleType t1 = new SimpleType(1);
            ArrowType left = new ArrowType(t0, t1);
            ArrowType right = new ArrowType(t0, t1);
            Type expected = new ArrowType(left, right);
            assertEquals(expected, real);
        } catch (Exception e) {
            fail(e);
        }
    }

    /**
     * Test a complex term
     */
    @Test
    void test3() {
        try {
            ASTExpr expr = (ASTExpr) parser.parseString("fn(x){fn(y){fn(z){ (x(z))(y(z)) }}}");
            Type real = typist.typeExpression(expr);

            SimpleType t0 = new SimpleType(0);
            SimpleType t1 = new SimpleType(1);
            SimpleType t2 = new SimpleType(2);
            ArrowType left = new ArrowType(t0, new ArrowType(t1, t2));
            ArrowType right = new ArrowType(new ArrowType(t0, t1), new ArrowType(t0, t2));
            Type expected = new ArrowType(left, right);
            assertEquals(expected, real);
        } catch (Exception e) {
            fail(e);
        }
    }

    /**
     * Test a more complex term
     */
    @Test
    void test4() {
        try {
            ASTExpr expr = (ASTExpr) parser.parseString("fn(a){fn(b){ a(a(b)) }}");
            Type real = typist.typeExpression(expr);

            SimpleType t0 = new SimpleType(0);
            ArrowType left = new ArrowType(t0, t0);
            ArrowType right = new ArrowType(t0, t0);
            Type expected = new ArrowType(left, right);
            assertEquals(expected, real);
        } catch (Exception e) {
            fail(e);
        }
    }

    @Test
    void testError() {
        try {
            ASTExpr expr = (ASTExpr) parser.parseString("fn(a){a(a)}");
            assertThrows(TypingException.class, () -> typist.typeExpression(expr));
        } catch (Exception e) {
            fail(e);
        }
    }

}
