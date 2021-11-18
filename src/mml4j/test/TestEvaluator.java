package mml4j.test;

import mml4j.main.ast.abstracts.ASTExpr;
import mml4j.main.evaluator.Evaluator;
import mml4j.main.evaluator.values.*;
import mml4j.main.evaluator.values.abstracts.Value;
import mml4j.main.parser.Parser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TestEvaluator {

    // ----- Attributes -----

    private static Parser parser;
    private static Evaluator evaluator;

    // ----- Init methods -----

    /**
     * Initialize the test suite
     */
    @BeforeAll
    static void init() {
        parser = new Parser();
        evaluator = new Evaluator();
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
     * Test the int evaluation
     */
    @Test
    void testInt() {
        try {
            ASTExpr expr = (ASTExpr) parser.parseString("42");
            Value result = expr.acceptEvaluator(evaluator, new HashMap<>());

            assertInstanceOf(IntValue.class, result);
            assertEquals(42, ((IntValue) result).getValue());
        } catch (Exception e) {
            fail(e);
        }
    }

    /**
     * Test the unit value evaluation
     */
    @Test
    void testUnit() {
        try {
            ASTExpr expr = (ASTExpr) parser.parseString("()");
            Value result = expr.acceptEvaluator(evaluator, new HashMap<>());

            assertInstanceOf(UnitValue.class, result);
        } catch (Exception e) {
            fail(e);
        }
    }

    /**
     * Test the nil value evaluation
     */
    @Test
    void testNil() {
        try {
            ASTExpr expr = (ASTExpr) parser.parseString("nil");
            Value result = expr.acceptEvaluator(evaluator, new HashMap<>());

            assertInstanceOf(NilValue.class, result);
            assertInstanceOf(ListValue.class, result);
        } catch (Exception e) {
            fail(e);
        }
    }

    /**
     * Test the add evaluation
     */
    @Test
    void testAdd() {
        try {
            ASTExpr expr = (ASTExpr) parser.parseString("10 + 5");
            Value result = expr.acceptEvaluator(evaluator, new HashMap<>());

            assertInstanceOf(IntValue.class, result);
            assertEquals(15, ((IntValue) result).getValue());
        } catch (Exception e) {
            fail(e);
        }
    }

    /**
     * Test the sub evaluation
     */
    @Test
    void testSub() {
        try {
            ASTExpr expr = (ASTExpr) parser.parseString("10 - 5");
            Value result = expr.acceptEvaluator(evaluator, new HashMap<>());

            assertInstanceOf(IntValue.class, result);
            assertEquals(5, ((IntValue) result).getValue());
        } catch (Exception e) {
            fail(e);
        }
    }

    /**
     * Test the list construction
     */
    @Test
    void testCons() {
        try {
            ASTExpr expr = (ASTExpr) parser.parseString("[1, 2, 3]");
            Value result = expr.acceptEvaluator(evaluator, new HashMap<>());

            assertInstanceOf(ListValue.class, result);
            ListValue listRes = (ListValue) result;
            assertInstanceOf(IntValue.class, listRes.getHead());
            assertInstanceOf(IntValue.class, listRes.getTail().getHead());
            assertInstanceOf(IntValue.class, listRes.getTail().getTail().getHead());
            assertInstanceOf(NilValue.class, listRes.getTail().getTail().getTail());
            assertEquals(1, ((IntValue) listRes.getHead()).getValue());
            assertEquals(2, ((IntValue) listRes.getTail().getHead()).getValue());
            assertEquals(3, ((IntValue) listRes.getTail().getTail().getHead()).getValue());
        } catch (Exception e) {
            fail(e);
        }
    }

    /**
     * Test the head build in
     */
    @Test
    void testHead() {
        try {
            ASTExpr expr = (ASTExpr) parser.parseString("head([1, 2, 3])");
            Value result = expr.acceptEvaluator(evaluator, new HashMap<>());

            assertInstanceOf(IntValue.class, result);
            assertEquals(1, ((IntValue) result).getValue());
        } catch (Exception e) {
            fail(e);
        }
    }

    /**
     * Test the tail build in
     */
    @Test
    void testTail() {
        try {
            ASTExpr expr = (ASTExpr) parser.parseString("tail([1, 2, 3])");
            Value result = expr.acceptEvaluator(evaluator, new HashMap<>());

            assertInstanceOf(ListValue.class, result);
            ListValue listRes = (ListValue) result;
            assertInstanceOf(IntValue.class, listRes.getHead());
            assertInstanceOf(IntValue.class, listRes.getTail().getHead());
            assertInstanceOf(NilValue.class, listRes.getTail().getTail());
            assertEquals(2, ((IntValue) listRes.getHead()).getValue());
            assertEquals(3, ((IntValue) listRes.getTail().getHead()).getValue());
        } catch (Exception e) {
            fail(e);
        }
    }

    /**
     * Test the ref operation
     */
    @Test
    void testRef() {
        try {
            ASTExpr expr = (ASTExpr) parser.parseString("@5");
            Value result = expr.acceptEvaluator(evaluator, new HashMap<>());

            assertInstanceOf(RefValue.class, result);
            assertEquals("#0", ((RefValue) result).getReferenceName());
        } catch (Exception e) {
            fail(e);
        }
    }

    /**
     * Test the deref operation
     */
    @Test
    void testDeref() {
        try {
            ASTExpr expr = (ASTExpr) parser.parseString("!@5");
            Value result = expr.acceptEvaluator(evaluator, new HashMap<>());

            assertInstanceOf(IntValue.class, result);
            assertEquals(5, ((IntValue) result).getValue());
        } catch (Exception e) {
            fail(e);
        }
    }

    /**
     * Test the assign operation
     */
    @Test
    void testAssign() {
        try {
            ASTExpr expr = (ASTExpr) parser.parseString("@5 := 42");
            Map<String, Value> ctx = new HashMap<>();
            Value result = expr.acceptEvaluator(new Evaluator(), ctx);

            assertInstanceOf(UnitValue.class, result);
            assertInstanceOf(IntValue.class, ctx.get("#0"));
            assertEquals(42, ((IntValue) ctx.get("#0")).getValue());
        } catch (Exception e) {
            fail(e);
        }
    }

    /**
     * Test the abstraction
     */
    @Test
    void testAbs() {
        try {
            ASTExpr expr = (ASTExpr) parser.parseString("fn(a){a + 5}");
            Value result = expr.acceptEvaluator(evaluator, new HashMap<>());

            assertInstanceOf(FunctionalValue.class, result);
            FunctionalValue funcRes = (FunctionalValue) result;
            assertEquals("a", funcRes.getParam());
            assertEquals(parser.parseString("a + 5"), funcRes.getBody());
        } catch (Exception e) {
            fail(e);
        }
    }

    /**
     * Test the recursive abstraction
     */
    @Test
    void testAbsRec() {
        try {
            ASTExpr expr = (ASTExpr) parser.parseString("rec my_rec (a) {my_rec(a - 1)}");
            Value result = expr.acceptEvaluator(evaluator, new HashMap<>());

            assertInstanceOf(RecursiveFunctionalValue.class, result);
            RecursiveFunctionalValue funcRes = (RecursiveFunctionalValue) result;
            assertEquals("my_rec", funcRes.getName());
            assertEquals("a", funcRes.getParam());
            assertEquals(parser.parseString("my_rec(a - 1)"), funcRes.getBody());
        } catch (Exception e) {
            fail(e);
        }
    }

    /**
     * Test the application
     */
    @Test
    void testApp() {
        try {
            ASTExpr expr = (ASTExpr) parser.parseString("fn(a, b){a + b}(4)(6)");
            Value result = expr.acceptEvaluator(evaluator, new HashMap<>());

            assertInstanceOf(IntValue.class, result);
            assertEquals(10, ((IntValue) result).getValue());
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
            ASTExpr expr = (ASTExpr) parser.parseString("ifem([]) {42} else {0}");
            Value result = expr.acceptEvaluator(evaluator, new HashMap<>());

            assertInstanceOf(IntValue.class, result);
            assertEquals(42, ((IntValue) result).getValue());

            expr = (ASTExpr) parser.parseString("ifem(nil) {32} else {0}");
            result = expr.acceptEvaluator(evaluator, new HashMap<>());

            assertInstanceOf(IntValue.class, result);
            assertEquals(32, ((IntValue) result).getValue());

            expr = (ASTExpr) parser.parseString("ifem([1, 2, 3]) {32} else {0}");
            result = expr.acceptEvaluator(evaluator, new HashMap<>());

            assertInstanceOf(IntValue.class, result);
            assertEquals(0, ((IntValue) result).getValue());
        } catch (Exception e) {
            fail(e);
        }
    }

    /**
     * Test the if zero
     */
    @Test
    void testIfz() {
        try {
            ASTExpr expr = (ASTExpr) parser.parseString("ifz(0) {42} else {0}");
            Value result = expr.acceptEvaluator(evaluator, new HashMap<>());

            assertInstanceOf(IntValue.class, result);
            assertEquals(42, ((IntValue) result).getValue());

            expr = (ASTExpr) parser.parseString("ifz(3) {32} else {0}");
            result = expr.acceptEvaluator(evaluator, new HashMap<>());

            assertInstanceOf(IntValue.class, result);
            assertEquals(0, ((IntValue) result).getValue());
        } catch (Exception e) {
            fail(e);
        }
    }

    /**
     * Test the let
     */
    @Test
    void testLet() {
        try {
            ASTExpr expr = (ASTExpr) parser.parseString("let a = 15 and b = 6 in a + b");
            Value result = expr.acceptEvaluator(evaluator, new HashMap<>());

            assertInstanceOf(IntValue.class, result);
            assertEquals(21, ((IntValue) result).getValue());
        } catch (Exception e) {
            fail(e);
        }
    }

    /**
     * Test the sum function
     */
    @Test
    void testSum() {
        try {
            ASTExpr expr = (ASTExpr) parser.parseString("rec sum (a) {ifz(a) {0} else {a + sum(a - 1)}}(5)");
            Value result = expr.acceptEvaluator(evaluator, new HashMap<>());

            assertInstanceOf(IntValue.class, result);
            assertEquals(5 + 4 + 3 + 2 + 1, ((IntValue) result).getValue());
        } catch (Exception e) {
            fail(e);
        }
    }

}
