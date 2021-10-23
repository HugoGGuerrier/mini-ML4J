package MML4J.main.parser;

import MML4J.main.parser.antlr.MMLBaseVisitor;
import MML4J.main.parser.antlr.MMLParser;
import MML4J.main.ast.*;

class Visitor extends MMLBaseVisitor<AST> {

    // Visit a program node

    @Override
    public AST visitProg(MMLParser.ProgContext ctx) {
        return ctx.body.accept(this);
    }

    // Visit a priorised node
    @Override
    public AST visitPriorised(MMLParser.PriorisedContext ctx) {
        return ctx.inside.accept(this);
    }


    // Visit an abstraction node
    @Override
    public AST visitAbstraction(MMLParser.AbstractionContext ctx) {
        return new ASTAbs(ctx.param.getText(), (ASTExpr) ctx.body.accept(this));
    }

    // Visit an application node
    @Override
    public AST visitApplication(MMLParser.ApplicationContext ctx) {
        return new ASTApp((ASTExpr) ctx.func.accept(this), (ASTExpr) ctx.arg.accept(this));
    }

    // Visit a variable node
    @Override
    public AST visitVariable(MMLParser.VariableContext ctx) {
        return new ASTVar(ctx.getText());
    }

}
