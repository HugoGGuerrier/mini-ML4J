package MML4J.test;

import MML4J.main.ast.ASTExpr;
import MML4J.main.exceptions.TypingException;
import MML4J.main.parser.Parser;
import MML4J.main.typist.Typist;
import MML4J.main.typist.type.*;
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
     * Test untypable terms
     */
    @Test
    void testError() {
        try {
            ASTExpr expr = (ASTExpr) parser.parseString("fn(a){a(a)}");
            assertThrows(TypingException.class, () -> typist.typeExpression(expr));
        } catch (Exception e) {
            fail(e);
        }
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

    /**
     * Test the int typing
     */
    @Test
    void testInt() {
        try {
            ASTExpr expr = (ASTExpr) parser.parseString("fn(a){42}");
            Type real = typist.typeExpression(expr);

            Type expected = new ArrowType(new SimpleType(0), IntType.getInstance());
            assertEquals(expected, real);
        } catch (Exception e) {
            fail(e);
        }
    }

    /**
     * Test the add and sub operations
     */
    @Test
    void testAddSub() {
        try {
            ASTExpr expr1 = (ASTExpr) parser.parseString("fn(a){fn(b){a + b}}");
            Type real1 = typist.typeExpression(expr1);

            ASTExpr expr2 = (ASTExpr) parser.parseString("fn(a){fn(b){a - b}}");
            Type real2 = typist.typeExpression(expr2);

            Type expected = new ArrowType(IntType.getInstance(), new ArrowType(IntType.getInstance(), IntType.getInstance()));
            assertEquals(expected, real1);
            assertEquals(expected, real2);
        } catch (Exception e) {
            fail(e);
        }
    }

    /**
     * Test the nil keyword
     */
    @Test
    void testNil() {
        try {
            ASTExpr expr = (ASTExpr) parser.parseString("cons(1, cons(2, nil))");
            Type real = typist.typeExpression(expr);

            Type expected = new ListType(IntType.getInstance());
            assertEquals(expected, real);
        } catch (Exception e) {
            fail(e);
        }
    }

    /**
     * Test the cons functions
     */
    @Test
    void testCons() {
        try {
            ASTExpr expr = (ASTExpr) parser.parseString("fn(a){fn(b){cons(a, b)}}");
            Type real = typist.typeExpression(expr);

            SimpleType t0 = new SimpleType(0);
            ArrowType right = new ArrowType(new ListType(t0), new ListType(t0));
            Type expected = new ArrowType(t0, right);
            assertEquals(expected, real);
        } catch (Exception e) {
            fail(e);
        }
    }

    /**
     * Test the head function
     */
    @Test
    void testHead() {
        try {
            ASTExpr expr = (ASTExpr) parser.parseString("fn(a){head(a)}");
            Type real = typist.typeExpression(expr);

            SimpleType t0 = new SimpleType(0);
            Type expected = new ArrowType(new ListType(t0), t0);
            assertEquals(expected, real);
        } catch (Exception e) {
            fail(e);
        }
    }

    /**
     * Test the tail function
     */
    @Test
    void testTail() {
        try {
            ASTExpr expr = (ASTExpr) parser.parseString("fn(a){tail(a)}");
            Type real = typist.typeExpression(expr);

            SimpleType t0 = new SimpleType(0);
            Type expected = new ArrowType(new ListType(t0), new ListType(t0));
            assertEquals(expected, real);
        } catch (Exception e) {
            fail(e);
        }
    }

    /**
     * Test the if empty structure
     */
    @Test
    void testIfem() {
        try {
            ASTExpr expr = (ASTExpr) parser.parseString("fn(a){fn(b){ifem(a){b} else{a}}}");
            Type real = typist.typeExpression(expr);

            SimpleType t0 = new SimpleType(0);
            ListType l0 = new ListType(t0);
            Type expected = new ArrowType(l0, new ArrowType(l0, l0));
            assertEquals(expected, real);
        } catch (Exception e) {
            fail(e);
        }
    }

    /**
     * Test the if zero structure
     */
    @Test
    void testIfz() {
        try {
            ASTExpr expr = (ASTExpr) parser.parseString("fn(a){fn(b){ifz(a) {b} else{a}}}");
            Type real = typist.typeExpression(expr);

            Type expected = new ArrowType(IntType.getInstance(), new ArrowType(IntType.getInstance(), IntType.getInstance()));
            assertEquals(expected, real);
        } catch (Exception e) {
            fail(e);
        }
    }

    /**
     * Test a simple let structure
     */
    @Test
    void testLet1() {
        try {
            ASTExpr expr = (ASTExpr) parser.parseString("let a = 42 in a + 15");
            Type real = typist.typeExpression(expr);

            Type expected = IntType.getInstance();
            assertEquals(expected, real);
        } catch (Exception e) {
            fail(e);
        }
    }

    /**
     * Test a let structure
     */
    @Test
    void testLet2() {
        try {
            ASTExpr expr = (ASTExpr) parser.parseString("let a = fn(a){a + 15} in a(42)");
            Type real = typist.typeExpression(expr);

            Type expected = IntType.getInstance();
            assertEquals(expected, real);
        } catch (Exception e) {
            fail(e);
        }
    }

    /**
     * Test a complex let structure
     */
    @Test
    void testLet3() {
        try {
            ASTExpr expr = (ASTExpr) parser.parseString("let x = fn(a){fn(b){a(b)}} in fn(a){fn(b){x(a)(b)}}");
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
     * Test a more complex let structure
     */
    @Test
    void testLet4() {
        try {
            ASTExpr expr = (ASTExpr) parser.parseString("fn(a){ let x = fn(b){ a(b) } in x(42) }");
            Type real = typist.typeExpression(expr);

            SimpleType t0 = new SimpleType(0);
            ArrowType expected = new ArrowType(new ArrowType(IntType.getInstance(), t0), t0);
            assertEquals(expected, real);
        } catch (Exception e) {
            fail(e);
        }
    }

}
