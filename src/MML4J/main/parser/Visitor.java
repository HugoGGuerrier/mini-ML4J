package MML4J.main.parser;

import MML4J.main.antlr.MMLParser;
import MML4J.main.ast.*;

class Visitor extends BaseVisitor {

    @Override
    public AST visitProg(MMLParser.ProgContext ctx) {
        return ctx.body.accept(this);
    }

    @Override
    public AST visitVariable(MMLParser.VariableContext ctx) {
        return new ASTVar(ctx.getText());
    }

    @Override
    public AST visitPriorised(MMLParser.PriorisedContext ctx) {
        return ctx.inside.accept(this);
    }

    @Override
    public AST visitAbstraction(MMLParser.AbstractionContext ctx) {
        return new ASTAbs(ctx.param.getText(), (ASTExpr) ctx.body.accept(this));
    }

    @Override
    public AST visitApplication(MMLParser.ApplicationContext ctx) {
        return new ASTApp((ASTExpr) ctx.func.accept(this), (ASTExpr) ctx.arg.accept(this));
    }

}
