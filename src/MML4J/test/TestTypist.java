package MML4J.test;

import MML4J.main.ast.AST;
import MML4J.main.ast.ASTExpr;
import MML4J.main.parser.Parser;
import MML4J.main.typist.Typist;
import MML4J.main.typist.equation_graph.Node;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

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
            Node equations = typist.getEquationGraph(expr);
            System.out.println(equations.getEquationsString());
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
            Node equations = typist.getEquationGraph(expr);
            System.out.println(equations.getEquationsString());
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
            Node equations = typist.getEquationGraph(expr);
            System.out.println(equations.getEquationsString());
        } catch (Exception e) {
            fail(e);
        }
    }

}
