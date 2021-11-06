package MML4J.main.parser;

import MML4J.main.parser.antlr.MMLLexer;
import MML4J.main.parser.antlr.MMLParser;
import MML4J.main.ast.AST;
import MML4J.main.exceptions.ParsingException;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

public class Parser {

    // ----- Attributes -----


    private ParsingException exception;


    // ----- Constructors -----


    public Parser() {
        this.exception = null;
    }


    // ----- Getters -----


    public ParsingException getException() {
        return exception;
    }


    // ----- Internal methods -----


    private void reset() {
        exception = null;
    }


    // ----- Class methods -----


    public void signalException(String msg) {
        if(exception == null) exception = new ParsingException(msg);
    }

    public AST parseString(String toParse) throws ParsingException {
        // Reset the parser
        reset();

        // Create the lexer and parser
        MMLLexer lexer = new MMLLexer(CharStreams.fromString(toParse));
        MMLParser parser = new MMLParser(new CommonTokenStream(lexer));
        parser.removeErrorListeners();
        parser.addErrorListener(new ParserErrorListener());

        // Get the parse tree
        try {
            MMLParser.ProgramContext prog = parser.program();
            Visitor visitor = new Visitor(this);

            // Visit the tree and get the result
            AST result = prog.accept(visitor);

            // If there is no exception return the result
            if(exception ==  null) return result;
            throw exception;
        } catch(Exception e) {
            throw new ParsingException("Cannot parse the string : " + e.getMessage());
        }
    }

}