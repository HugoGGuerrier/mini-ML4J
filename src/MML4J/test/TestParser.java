package MML4J.test;

import MML4J.main.ast.*;
import MML4J.main.parser.Parser;
import MML4J.main.exceptions.ParsingException;
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

            assertThrows(ParsingException.class, () -> parser.parseString("fn(a)"));
            assertThrows(ParsingException.class, () -> parser.parseString("fn(a){a"));
        } catch (ParsingException e) {
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
        } catch (ParsingException e) {
            fail(e);
        }
    }

    /**
     * Test the addition parsing
     */
    @Test
    void testAdd() {
        try {
            assertEquals(parser.parseString("a + b"), new ASTAdd(new ASTVar("a"), new ASTVar("b")));

            assertThrows(ParsingException.class, () -> parser.parseString(" + b"));
            assertThrows(ParsingException.class, () -> parser.parseString("a +"));
            assertThrows(ParsingException.class, () -> parser.parseString("+"));
        } catch (ParsingException e) {
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

            assertThrows(ParsingException.class, () -> parser.parseString("a(b"));
        } catch (ParsingException e) {
            fail(e);
        }
    }

    /**
     * Test the list cons parsing
     */
    @Test
    void testCons() {
        try {
            assertEquals(parser.parseString("cons(a, b)"), new ASTCons(new ASTVar("a"), new ASTVar("b")));

            assertThrows(ParsingException.class, () -> parser.parseString("cons(a)"));
            assertThrows(ParsingException.class, () -> parser.parseString("cons(a, b, c)"));
            assertThrows(ParsingException.class, () -> parser.parseString("cons(a"));
        } catch (ParsingException e) {
            fail(e);
        }
    }

    /**
     * Test the list head operator parsing
     */
    @Test
    void testHead() {
        try {
            assertEquals(parser.parseString("head(a)"), new ASTHead(new ASTVar("a")));

            assertThrows(ParsingException.class, () -> parser.parseString("head()"));
            assertThrows(ParsingException.class, () -> parser.parseString("head(a, b, c)"));
            assertThrows(ParsingException.class, () -> parser.parseString("head(a"));
        } catch (ParsingException e) {
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
        } catch (ParsingException e) {
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
        } catch (ParsingException e) {
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
        } catch (ParsingException e) {
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

            assertThrows(ParsingException.class, () -> parser.parseString("let a = in c"));
            assertThrows(ParsingException.class, () -> parser.parseString("let a = b in"));
            assertThrows(ParsingException.class, () -> parser.parseString("let = b in c"));
        } catch (ParsingException e) {
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
        } catch (ParsingException e) {
            fail(e);
        }
    }

    /**
     * Test the addition parsing
     */
    @Test
    void testSub() {
        try {
            assertEquals(parser.parseString("a - b"), new ASTSub(new ASTVar("a"), new ASTVar("b")));

            assertThrows(ParsingException.class, () -> parser.parseString(" - b"));
            assertThrows(ParsingException.class, () -> parser.parseString("a -"));
            assertThrows(ParsingException.class, () -> parser.parseString("-"));
        } catch (ParsingException e) {
            fail(e);
        }
    }

    /**
     * Test the list tail operator parsing
     */
    @Test
    void testTail() {
        try {
            assertEquals(parser.parseString("tail(a)"), new ASTTail(new ASTVar("a")));

            assertThrows(ParsingException.class, () -> parser.parseString("tail()"));
            assertThrows(ParsingException.class, () -> parser.parseString("tail(a, b, c)"));
            assertThrows(ParsingException.class, () -> parser.parseString("tail(a"));
        } catch (ParsingException e) {
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
        } catch (ParsingException e) {
            fail(e);
        }
    }

}
