package MML4J.test;

import MML4J.main.ast.ASTExpr;
import MML4J.main.exceptions.TypingException;
import MML4J.main.parser.Parser;
import MML4J.main.typist.Typist;
import MML4J.main.typist.equation_graph.ArrowNode;
import MML4J.main.typist.equation_graph.Node;
import MML4J.main.typist.equation_graph.SimpleNode;
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
            // Test the equation generation
            SimpleNode t0 = new SimpleNode(0);
            SimpleNode t1 = new SimpleNode(1);
            SimpleNode t2 = new SimpleNode(2);
            t0.addChild(new ArrowNode(t1, t2));
            t1.addChild(t2);

            ASTExpr expr = (ASTExpr) parser.parseString("fn(a){a}");
            Node real = typist.getEquationGraph(expr);
            assertTrue(real.structEquals(t0));

            // Test the equation unification
            SimpleType type0 = new SimpleType("T0");
            Type expectedType = new ArrowType(type0, type0);

            Type realType = typist.unify(real);
            assertEquals(expectedType, realType);
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
            // Test the equation generation
            SimpleNode t0 = new SimpleNode(0);
            SimpleNode t1 = new SimpleNode(1);
            SimpleNode t2 = new SimpleNode(2);
            SimpleNode t3 = new SimpleNode(3);
            SimpleNode t4 = new SimpleNode(4);
            SimpleNode t5 = new SimpleNode(5);
            t0.addChild(new ArrowNode(t1, t2));
            t1.addChild(new ArrowNode(t5, t4));
            t2.addChild(new ArrowNode(t3, t4));
            t3.addChild(t5);

            ASTExpr expr = (ASTExpr) parser.parseString("fn(a){fn(b){ a(b) }}");
            Node real = typist.getEquationGraph(expr);
            assertTrue(real.structEquals(t0));

            // Test the equation unification
            SimpleType type0 = new SimpleType("T0");
            SimpleType type1 = new SimpleType("T1");
            Type expectedType = new ArrowType(new ArrowType(type0, type1), new ArrowType(type0, type1));

            Type realType = typist.unify(real);
            assertEquals(expectedType, realType);
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
            // Test the equation generation
            SimpleNode t0 = new SimpleNode(0);
            SimpleNode t1 = new SimpleNode(1);
            SimpleNode t2 = new SimpleNode(2);
            SimpleNode t3 = new SimpleNode(3);
            SimpleNode t4 = new SimpleNode(4);
            SimpleNode t5 = new SimpleNode(5);
            SimpleNode t6 = new SimpleNode(6);
            SimpleNode t7 = new SimpleNode(7);
            SimpleNode t8 = new SimpleNode(8);
            SimpleNode t9 = new SimpleNode(9);
            t0.addChild(new ArrowNode(t1, t2));
            t1.addChild(new ArrowNode(t9, new ArrowNode(t7, t6)));
            t2.addChild(new ArrowNode(t3, t4));
            t3.addChild(new ArrowNode(t8, t7));
            t4.addChild(new ArrowNode(t5, t6));
            t5.addChild(t8);
            t5.addChild(t9);

            ASTExpr expr = (ASTExpr) parser.parseString("fn(x){fn(y){fn(z){ (x(z))(y(z)) }}}");
            Node real = typist.getEquationGraph(expr);
            assertTrue(real.structEquals(t0));

            // Test the equation unification
            SimpleType type0 = new SimpleType("T0");
            SimpleType type1 = new SimpleType("T1");
            SimpleType type2 = new SimpleType("T2");
            Type expectedType = new ArrowType(
                    new ArrowType(type0, new ArrowType(type1, type2)),
                    new ArrowType(new ArrowType(type0, type1), new ArrowType(type0, type2))
            );

            Type realType = typist.unify(real);
            assertEquals(expectedType, realType);
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
            SimpleNode t0 = new SimpleNode(0);
            SimpleNode t1 = new SimpleNode(1);
            SimpleNode t2 = new SimpleNode(2);
            SimpleNode t3 = new SimpleNode(3);
            SimpleNode t4 = new SimpleNode(4);
            SimpleNode t5 = new SimpleNode(5);
            SimpleNode t6 = new SimpleNode(6);
            t0.addChild(new ArrowNode(t1, t2));
            t1.addChild(new ArrowNode(t6, t5));
            t1.addChild(new ArrowNode(t5, t4));
            t2.addChild(new ArrowNode(t3, t4));
            t3.addChild(t6);

            ASTExpr expr = (ASTExpr) parser.parseString("fn(a){fn(b){ a(a(b)) }}");
            Node real = typist.getEquationGraph(expr);
            assertTrue(real.structEquals(t0));

            // Test the equation unification
            SimpleType type0 = new SimpleType("T0");
            Type expectedType = new ArrowType(
                    new ArrowType(type0, type0),
                    new ArrowType(type0, type0)
            );

            Type realType = typist.unify(real);
            assertEquals(expectedType, realType);
        } catch (Exception e) {
            fail(e);
        }
    }

    @Test
    void testError() {
        try {
            // Test the equation generation
            SimpleNode t0 = new SimpleNode(0);
            SimpleNode t1 = new SimpleNode(1);
            SimpleNode t2 = new SimpleNode(2);
            SimpleNode t3 = new SimpleNode(3);
            t0.addChild(new ArrowNode(t1, t2));
            t1.addChild(t3);
            t1.addChild(new ArrowNode(t3, t2));

            ASTExpr expr = (ASTExpr) parser.parseString("fn(a){a(a)}");
            Node real = typist.getEquationGraph(expr);

            // Test the unification
            assertThrows(TypingException.class, () -> typist.unify(real));
        } catch (Exception e) {
            fail(e);
        }
    }

}
