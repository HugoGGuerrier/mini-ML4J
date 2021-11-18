package mml4j.test;

import mml4j.main.ast.*;
import mml4j.main.ast.abstracts.ASTExpr;
import mml4j.main.parser.Parser;
import mml4j.main.exceptions.ParsingException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestParser {

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

    @Test
    void testException() {
        assertThrows(ParsingException.class, () -> parser.parseString(""));
    }

    /**
     * Test the abstraction parsing
     */
    @Test
    void testAbs() {
        try {
            assertEquals(parser.parseString("fn(a){a}"), new ASTAbs("a", new ASTVar("a")));
            assertEquals(parser.parseString("fn(a, b){a(b)}"), parser.parseString("fn(a){fn(b){a(b)}}"));

            assertThrows(ParsingException.class, () -> parser.parseString("fn(a)"));
            assertThrows(ParsingException.class, () -> parser.parseString("fn(a){a"));
        } catch (Exception e) {
            fail(e);
        }
    }

    /**
     * Test the recursive abstraction parsing
     */
    @Test
    void testAbsRec() {
        try {
            assertEquals(parser.parseString("rec func(a){a}"), new ASTAbsRec("func" ,"a", new ASTVar("a")));

            assertThrows(ParsingException.class, () -> parser.parseString("rec f(a)"));
            assertThrows(ParsingException.class, () -> parser.parseString("rec f(a){a"));
        } catch (Exception e) {
            fail(e);
        }
    }

    /**
     * Test the addition parsing
     */
    @Test
    void testAdd() {
        try {
            assertEquals(parser.parseString("a + b"), new ASTApp(new ASTApp(new ASTVar("+"), new ASTVar("a")), new ASTVar("b")));

            assertThrows(ParsingException.class, () -> parser.parseString(" + b"));
            assertThrows(ParsingException.class, () -> parser.parseString("a +"));
            assertThrows(ParsingException.class, () -> parser.parseString("+"));
        } catch (Exception e) {
            fail(e);
        }
    }

    /**
     * Test the application parsing
     */
    @Test
    void testApp() {
        try {
            assertEquals(parser.parseString("a(b)"), new ASTApp(new ASTVar("a"), new ASTVar("b")));
            assertEquals(parser.parseString("a(b)(c)"), new ASTApp(new ASTApp(new ASTVar("a"), new ASTVar("b")), new ASTVar("c")));
            assertEquals(parser.parseString("a(b, c)"), parser.parseString("a(b)(c)"));
            assertEquals(parser.parseString("a(b, c, d)"), parser.parseString("a(b)(c)(d)"));

            assertThrows(ParsingException.class, () -> parser.parseString("a(b"));
            assertThrows(ParsingException.class, () -> parser.parseString("a()"));
        } catch (Exception e) {
            fail(e);
        }
    }

    /**
     * Test the assign term
     */
    @Test
    void testAssign() {
        try {
            assertEquals(parser.parseString("a := b"), new ASTApp(new ASTApp(new ASTVar(":="), new ASTVar("a")), new ASTVar("b")));

            assertThrows(ParsingException.class, () -> parser.parseString(":="));
            assertThrows(ParsingException.class, () -> parser.parseString("a := "));
            assertThrows(ParsingException.class, () -> parser.parseString(":= b"));
        } catch (Exception e) {
            fail(e);
        }
    }

    /**
     * Test the list cons parsing
     */
    @Test
    void testCons() {
        try {
            assertEquals(parser.parseString("cons(a, b)"), new ASTApp(new ASTApp(new ASTVar("cons"), new ASTVar("a")), new ASTVar("b")));
            assertEquals(parser.parseString("[]"), parser.parseString("nil"));
            assertEquals(parser.parseString("[a]"), parser.parseString("cons(a, nil)"));
            assertEquals(parser.parseString("[a, b]"), parser.parseString("cons(a, cons(b, nil))"));
            assertEquals(parser.parseString("[a, b, c, d]"), parser.parseString("cons(a, cons(b, cons(c, cons(d, nil))))"));

            assertThrows(ParsingException.class, () -> parser.parseString("cons(a)"));
            assertThrows(ParsingException.class, () -> parser.parseString("cons(a, b, c)"));
            assertThrows(ParsingException.class, () -> parser.parseString("cons(a"));
            assertThrows(ParsingException.class, () -> parser.parseString("[a, b, c"));
        } catch (Exception e) {
            fail(e);
        }
    }

    /**
     * Test the de-referencing parsing
     */
    @Test
    void testDeref() {
        try {
            assertEquals(parser.parseString("!a"), new ASTApp(new ASTVar("!"), new ASTVar("a")));
            assertEquals(parser.parseString("!(a + b)"), new ASTApp(
                    new ASTVar("!"),
                    new ASTApp(new ASTApp(new ASTVar("+"), new ASTVar("a")), new ASTVar("b"))
            ));

            assertThrows(ParsingException.class, () -> parser.parseString("!"));
            assertThrows(ParsingException.class, () -> parser.parseString("!(a"));
        } catch (Exception e) {
            fail(e);
        }
    }

    /**
     * Test the list head operator parsing
     */
    @Test
    void testHead() {
        try {
            assertEquals(parser.parseString("head(a)"), new ASTApp(new ASTVar("head"), new ASTVar("a")));

            assertThrows(ParsingException.class, () -> parser.parseString("head()"));
            assertThrows(ParsingException.class, () -> parser.parseString("head(a, b, c)"));
            assertThrows(ParsingException.class, () -> parser.parseString("head(a"));
        } catch (Exception e) {
            fail(e);
        }
    }

    /**
     * Test the if empty parsing
     */
    @Test
    void testIfem() {
        try {
            assertEquals(parser.parseString("ifem(a) {b} else {c}"), new ASTIfem(new ASTVar("a"), new ASTVar("b"), new ASTVar("c")));

            assertThrows(ParsingException.class, () -> parser.parseString("ifem()"));
            assertThrows(ParsingException.class, () -> parser.parseString("ifem(a) {b}"));
            assertThrows(ParsingException.class, () -> parser.parseString("ifem(a) {} else {c}"));
            assertThrows(ParsingException.class, () -> parser.parseString("ifem(a) {b} else {}"));
        } catch (Exception e) {
            fail(e);
        }
    }

    /**
     * Test the if zero parsing
     */
    @Test
    void testIfz() {
        try {
            assertEquals(parser.parseString("ifz(a) {b} else {c}"), new ASTIfz(new ASTVar("a"), new ASTVar("b"), new ASTVar("c")));

            assertThrows(ParsingException.class, () -> parser.parseString("ifz()"));
            assertThrows(ParsingException.class, () -> parser.parseString("ifz(a) {b}"));
            assertThrows(ParsingException.class, () -> parser.parseString("ifz(a) {} else {c}"));
            assertThrows(ParsingException.class, () -> parser.parseString("ifz(a) {b} else {}"));
        } catch (Exception e) {
            fail(e);
        }
    }

    /**
     * Test the integer parsing
     */
    @Test
    void testInt() {
        try {
            assertEquals(parser.parseString("1"), new ASTInt(1));
            assertEquals(parser.parseString("-1"), new ASTInt(-1));
        } catch (Exception e) {
            fail(e);
        }
    }

    /**
     * Test the let in parsing
     */
    @Test
    void testLet() {
        try {
            assertEquals(parser.parseString("let a = b in c"), new ASTLet("a", new ASTVar("b"), new ASTVar("c")));
            assertEquals(parser.parseString("let a = b and c = d in a + c"), parser.parseString("let a = b in let c = d in a + c"));
            assertEquals(parser.parseString("let a = @5 in a := (12 + 5) ; b ; !a"),
                    new ASTLet("a", (ASTExpr) parser.parseString("@5"),
                        new ASTLet("--ignored", (ASTExpr) parser.parseString("a := (12 + 5)"),
                        new ASTLet("--ignored", new ASTVar("b"), (ASTExpr) parser.parseString("!a"))
                        )
                    )
            );

            assertThrows(ParsingException.class, () -> parser.parseString("let a = in c"));
            assertThrows(ParsingException.class, () -> parser.parseString("let a = b in"));
            assertThrows(ParsingException.class, () -> parser.parseString("let = b in c"));
        } catch (Exception e) {
            fail(e);
        }
    }

    /**
     * Test the nil parsing
     */
    @Test
    void testNil() {
        try {
            assertEquals(parser.parseString("nil"), new ASTNil());
        } catch (Exception e) {
            fail(e);
        }
    }

    /**
     * Test the referencing parsing
     */
    @Test
    void testRef() {
        try {
            assertEquals(parser.parseString("@a"), new ASTApp(new ASTVar("@"), new ASTVar("a")));
            assertEquals(parser.parseString("@(a + b)"), new ASTApp(
                    new ASTVar("@"),
                    new ASTApp(new ASTApp(new ASTVar("+"), new ASTVar("a")), new ASTVar("b"))
            ));

            assertThrows(ParsingException.class, () -> parser.parseString("@"));
            assertThrows(ParsingException.class, () -> parser.parseString("@(f"));
        } catch (Exception e) {
            fail(e);
        }
    }

    /**
     * Test the addition parsing
     */
    @Test
    void testSub() {
        try {
            assertEquals(parser.parseString("a - b"), new ASTApp(new ASTApp(new ASTVar("-"), new ASTVar("a")), new ASTVar("b")));

            assertThrows(ParsingException.class, () -> parser.parseString(" - b"));
            assertThrows(ParsingException.class, () -> parser.parseString("a -"));
            assertThrows(ParsingException.class, () -> parser.parseString("-"));
        } catch (Exception e) {
            fail(e);
        }
    }

    /**
     * Test the list tail operator parsing
     */
    @Test
    void testTail() {
        try {
            assertEquals(parser.parseString("tail(a)"), new ASTApp(new ASTVar("tail"), new ASTVar("a")));

            assertThrows(ParsingException.class, () -> parser.parseString("tail()"));
            assertThrows(ParsingException.class, () -> parser.parseString("tail(a, b, c)"));
            assertThrows(ParsingException.class, () -> parser.parseString("tail(a"));
        } catch (Exception e) {
            fail(e);
        }
    }

    /**
     * Test the unit term parsing
     */
    @Test
    void testUnit() {
        try {
            assertEquals(parser.parseString("()"), new ASTUnit());
            assertEquals(parser.parseString("let a = () in a"), new ASTLet("a", new ASTUnit(), new ASTVar("a")));

            assertThrows(ParsingException.class, () -> parser.parseString("("));
        } catch (Exception e) {
            fail(e);
        }
    }

    /**
     * Test the variable term parsing
     */
    @Test
    void testVar() {
        try {
            assertEquals(parser.parseString("a"), new ASTVar("a"));
            assertEquals(parser.parseString("(a)"), new ASTVar("a"));
            assertEquals(parser.parseString("(((a)))"), new ASTVar("a"));
            assertEquals(parser.parseString("coucou"), new ASTVar("coucou"));

            assertThrows(ParsingException.class, () -> parser.parseString("a b"));
            assertThrows(ParsingException.class, () -> parser.parseString("(a"));
            assertThrows(ParsingException.class, () -> parser.parseString("(a))"));
        } catch (Exception e) {
            fail(e);
        }
    }

}
