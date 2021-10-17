package MML4J.test;

import MML4J.main.ast.ASTAbs;
import MML4J.main.ast.ASTApp;
import MML4J.main.ast.ASTVar;
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

}
