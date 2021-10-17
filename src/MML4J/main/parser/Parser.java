package MML4J.main.parser;

import MML4J.main.parser.antlr.MMLLexer;
import MML4J.main.parser.antlr.MMLParser;
import MML4J.main.ast.AST;
import MML4J.main.exceptions.ParsingException;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

public class Parser {

    // ----- Class methods -----

    public AST parseString(String toParse) throws ParsingException {
        // Create the lexer and parser
        MMLLexer lexer = new MMLLexer(CharStreams.fromString(toParse));
        MMLParser parser = new MMLParser(new CommonTokenStream(lexer));
        parser.removeErrorListeners();
        parser.addErrorListener(new ParserErrorListener());

        // Get the parse tree
        try {
            MMLParser.ProgramContext prog = parser.program();
            Visitor visitor = new Visitor();

            // Visit the tree and return the result
            return prog.accept(visitor);
        } catch(Exception e) {
            throw new ParsingException("Cannot parse the string : " + e.getMessage());
        }
    }

}