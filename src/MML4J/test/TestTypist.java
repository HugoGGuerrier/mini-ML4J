package MML4J.test;

import MML4J.main.ast.ASTExpr;
import MML4J.main.exceptions.TypingException;
import MML4J.main.parser.Parser;
import MML4J.main.typist.Typist;
import MML4J.main.typist.types.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestTypist {

    // ----- Attributes for the tests -----

    private static Parser parser;

    // ----- Init methods -----

    /**
     * Initialize the test suite
     */
    @BeforeAll
    static void init() {
        parser = new Parser();
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
            ASTExpr wrong1 = (ASTExpr) parser.parseString("fn(a){a(a)}");
            assertThrows(TypingException.class, () -> Typist.typeExpression(wrong1));

            ASTExpr wrong2 = (ASTExpr) parser.parseString("let a = @[15, 3, 5] in let _ = a := 5 in a");
            assertThrows(TypingException.class, () -> Typist.typeExpression(wrong2));

            ASTExpr wrong3 = (ASTExpr) parser.parseString("fn(a, b){ let x = a + 5 in a(b) }");
            assertThrows(TypingException.class, () -> Typist.typeExpression(wrong3));

            ASTExpr wrong4 = (ASTExpr) parser.parseString("let l = @[fn(x){x}] in let _ = l := [fn(x){x}] in head(!l) + 2");
            assertThrows(TypingException.class, () -> Typist.typeExpression(wrong4));

            ASTExpr wrong5 = (ASTExpr) parser.parseString("let a = let b = let c = @5 in c in b in let _ = a := [5] in a");
            assertThrows(TypingException.class, () -> Typist.typeExpression(wrong5));
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
            Type real = Typist.typeExpression(expr);

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
            Type real = Typist.typeExpression(expr);

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
            Type real = Typist.typeExpression(expr);

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
            Type real = Typist.typeExpression(expr);

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
     * Test the abstraction recursive typing
     */
    @Test
    void testAbsRec() {
        try {
            ASTExpr expr = (ASTExpr) parser.parseString("rec x (a) { x(a - 1) }");
            Type real = Typist.typeExpression(expr);

            SimpleType t0 = new SimpleType(0);
            ArrowType expected = new ArrowType(IntType.getInstance(), t0);
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
            Type real = Typist.typeExpression(expr);

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
            Type real1 = Typist.typeExpression(expr1);

            ASTExpr expr2 = (ASTExpr) parser.parseString("fn(a){fn(b){a - b}}");
            Type real2 = Typist.typeExpression(expr2);

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
            Type real = Typist.typeExpression(expr);

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
            Type real = Typist.typeExpression(expr);

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
            Type real = Typist.typeExpression(expr);

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
            Type real = Typist.typeExpression(expr);

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
            Type real = Typist.typeExpression(expr);

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
            Type real = Typist.typeExpression(expr);

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
            Type real = Typist.typeExpression(expr);

            Type expected = IntType.getInstance();
            assertEquals(expected, real);
        } catch (Exception e) {
            fail(e);
        }
    }

    /**
     * Test an alternative simple let structure
     */
    @Test
    void testLet1_2() {
        try {
            ASTExpr expr = (ASTExpr) parser.parseString("let a = 42 + 15 in a");
            Type real = Typist.typeExpression(expr);

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
            Type real = Typist.typeExpression(expr);

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
            Type real = Typist.typeExpression(expr);

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
            Type real = Typist.typeExpression(expr);

            SimpleType t0 = new SimpleType(0);
            ArrowType expected = new ArrowType(new ArrowType(IntType.getInstance(), t0), t0);
            assertEquals(expected, real);
        } catch (Exception e) {
            fail(e);
        }
    }

    /**
     * Test a very complex let structure
     */
    @Test
    void testLet5() {
        try {
            ASTExpr expr = (ASTExpr) parser.parseString("fn(a){ let x = let y = a(42) in y in x }");
            Type real = Typist.typeExpression(expr);

            SimpleType t0 = new SimpleType(0);
            ArrowType expected = new ArrowType(new ArrowType(IntType.getInstance(), t0), t0);
            assertEquals(expected, real);
        } catch (Exception e) {
            fail(e);
        }
    }

    /**
     * Test the unit
     */
    @Test
    void testUnit() {
        try {
            ASTExpr expr = (ASTExpr) parser.parseString("let a = () in a");
            Type real = Typist.typeExpression(expr);

            UnitType expected = UnitType.getInstance();
            assertEquals(expected, real);
        } catch (Exception e) {
            fail(e);
        }
    }

    /**
     * Test the reference and de-reference
     */
    @Test
    void testRefDeRef() {
        try {
            ASTExpr expr = (ASTExpr) parser.parseString("let a = @[1] in !a");
            Type real = Typist.typeExpression(expr);

            ListType expected = new ListType(IntType.getInstance());
            assertEquals(expected, real);
        } catch (Exception e) {
            fail(e);
        }
    }

    /**
     * Test the assignment
     */
    @Test
    void testAssign() {
        try {
            ASTExpr expr = (ASTExpr) parser.parseString("let a = @8 in let _ = a := (10 + 5) in a");
            Type real = Typist.typeExpression(expr);

            Type expected = new RefType(IntType.getInstance());
            assertEquals(expected, real);
        } catch (Exception e) {
            fail(e);
        }
    }
}
