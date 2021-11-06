package MML4J.main.parser;

import MML4J.main.exceptions.ParsingException;
import MML4J.main.parser.antlr.MMLBaseVisitor;
import MML4J.main.parser.antlr.MMLParser;
import MML4J.main.ast.*;

class Visitor extends MMLBaseVisitor<AST> {

    // ----- Attributes -----


    private Parser parser;


    // ----- Constructors -----


    public Visitor(Parser parser) {
        this.parser = parser;
    }


    // ----- Visit methods -----


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


    // Visit a sole argument node
    @Override
    public AST visitSoleArgs(MMLParser.SoleArgsContext ctx) {
        return new ASTArgs((ASTExpr) ctx.arg.accept(this));
    }

    // Visit a multi arg node
    @Override
    public AST visitMultiArgs(MMLParser.MultiArgsContext ctx) {
        ASTArgs tail = (ASTArgs) ctx.tail.accept(this);
        tail.addArg((ASTExpr) ctx.arg.accept(this));
        return tail;
    }


    // Visit an abstraction node
    @Override
    public AST visitAbstraction(MMLParser.AbstractionContext ctx) {
        return new ASTAbs(
                ctx.param.getText(),
                (ASTExpr) ctx.body.accept(this)
        );
    }

    // Visit a recursive abstraction node
    @Override
    public AST visitRecAbstraction(MMLParser.RecAbstractionContext ctx) {
        return new ASTAbsRec(
                ctx.param.getText(),
                (ASTExpr) ctx.body.accept(this)
        );
    }

    // Visit an application node
    @Override
    public AST visitApplication(MMLParser.ApplicationContext ctx) {
        return new ASTApp(
                (ASTExpr) ctx.func.accept(this),
                (ASTExpr) ctx.arg.accept(this)
        );
    }

    // Visit a binary operator
    @Override
    public AST visitBinOp(MMLParser.BinOpContext ctx) {
        String opName = ctx.op.getText();
        ASTExpr left = (ASTExpr) ctx.left.accept(this);
        ASTExpr right = (ASTExpr) ctx.right.accept(this);

        switch (opName) {
            case "+":
                return new ASTAdd(left, right);

            case "-":
                return new ASTSub(left, right);
        }

        return null;
    }

    // Visit a build in function node
    @Override
    public AST visitBuildIn(MMLParser.BuildInContext ctx) {
        String buildInName = ctx.name.getText();
        ASTArgs args = (ASTArgs) ctx.arguments.accept(this);

        switch (buildInName) {
            case "cons":
                if(args.size() == 2) return new ASTCons(args.get(0), args.get(1));
                else parser.signalException("\"cons\" build-in takes exactly 2 arguments");
                break;

            case "head":
                if(args.size() == 1) return new ASTHead(args.get(0));
                else parser.signalException("\"head\" build-in takes exactly 1 argument");
                break;

            case "tail":
                if(args.size() == 1) return new ASTTail(args.get(0));
                else parser.signalException("\"tail\" build-in takes exactly 1 argument");
                break;
        }

        return null;
    }

    // Visit an if empty node
    @Override
    public AST visitIfEmpty(MMLParser.IfEmptyContext ctx) {
        return new ASTIfem(
                (ASTExpr) ctx.cond.accept(this),
                (ASTExpr) ctx.cons.accept(this),
                (ASTExpr) ctx.altern.accept(this)
        );
    }

    // Visit an if zero node
    @Override
    public AST visitIfZero(MMLParser.IfZeroContext ctx) {
        return new ASTIfz(
                (ASTExpr) ctx.cond.accept(this),
                (ASTExpr) ctx.cons.accept(this),
                (ASTExpr) ctx.altern.accept(this)
        );
    }

    // Visit an integer
    @Override
    public AST visitInteger(MMLParser.IntegerContext ctx) {
        return new ASTInt(Integer.parseInt(ctx.getText()));
    }

    // Visit a let in node
    @Override
    public AST visitLetIn(MMLParser.LetInContext ctx) {
        return new ASTLet(
                ctx.name.getText(),
                (ASTExpr) ctx.value.accept(this),
                (ASTExpr) ctx.body.accept(this)
        );
    }

    // Visit a nil node
    @Override
    public AST visitNil(MMLParser.NilContext ctx) {
        return new ASTNil();
    }

    // Visit a variable node
    @Override
    public AST visitVariable(MMLParser.VariableContext ctx) {
        return new ASTVar(ctx.getText());
    }

}
