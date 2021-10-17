package MML4J.main.parser;

import MML4J.main.parser.antlr.MMLBaseVisitor;
import MML4J.main.parser.antlr.MMLParser;
import MML4J.main.ast.AST;

/**
 * This class define all visit methods for the visitor pattern
 */
class BaseVisitor extends MMLBaseVisitor<AST> {
    /**
     * Visit a program context
     *
     * @param ctx The context
     * @return The AST returned by the visit
     */
    public AST visit(MMLParser.ProgContext ctx) {return visitProg(ctx);}

    /**
     * Visit a variable context
     *
     * @param ctx The context
     * @return The AST returned by the visit
     */
    public AST visit(MMLParser.VariableContext ctx) {return visitVariable(ctx);}

    /**
     * Visit a priorised context
     *
     * @param ctx The context
     * @return The AST returned by the visit
     */
    public AST visit(MMLParser.PriorisedContext ctx) {return visitPriorised(ctx);}

    /**
     * Visit an abstraction context
     *
     * @param ctx The context
     * @return The AST returned by the visit
     */
    public AST visit(MMLParser.AbstractionContext ctx) {return visitAbstraction(ctx);}

    /**
     * Visit an application context
     *
     * @param ctx The context
     * @return The AST returned by the visit
     */
    public AST visit(MMLParser.ApplicationContext ctx) {return visitApplication(ctx);}
}

