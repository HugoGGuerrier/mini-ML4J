package MML4J.main.parser;

import MML4J.main.parser.antlr.MMLBaseVisitor;
import MML4J.main.parser.antlr.MMLParser;
import MML4J.main.ast.*;

class Visitor extends MMLBaseVisitor<AST> {

    // ----- Attributes -----


    private final Parser parser;


    // ----- Constructors -----


    public Visitor(Parser parser) {
        this.parser = parser;
    }


    // ----- Visit methods -----


    // --- Miscallenous

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


    // --- Utils structures

    // Visit a void expressions node
    @Override
    public AST visitVoidExprs(MMLParser.VoidExprsContext ctx) {
        return new ASTExprs();
    }

    // Visit a single expression node
    @Override
    public AST visitSingleExprs(MMLParser.SingleExprsContext ctx) {
        return new ASTExprs((ASTExpr) ctx.head.accept(this));
    }

    // Visit a multi expressions node
    @Override
    public AST visitMultiExprs(MMLParser.MultiExprsContext ctx) {
        ASTExprs args = (ASTExprs) ctx.tail.accept(this);
        args.addExpr((ASTExpr) ctx.head.accept(this));
        return args;
    }

    // Visit a sole parameter node
    @Override
    public AST visitSingleParams(MMLParser.SingleParamsContext ctx) {
        return new ASTParams(ctx.head.getText());
    }

    // Visit a multi parameter node
    @Override
    public AST visitMultipleParams(MMLParser.MultipleParamsContext ctx) {
        ASTParams params = (ASTParams) ctx.tail.accept(this);
        params.addParam(ctx.head.getText());
        return params;
    }


    // --- Syntactic sugars

    @Override
    public AST visitListSugar(MMLParser.ListSugarContext ctx) {
        ASTExprs listContent = (ASTExprs) ctx.inside.accept(this);
        return new ASTCons(listContent.getExprs());
    }


    // --- Function definition and application

    // Visit an abstraction node
    @Override
    public AST visitAbstraction(MMLParser.AbstractionContext ctx) {
        ASTParams params = (ASTParams) ctx.parameters.accept(this);
        ASTExpr res = (ASTExpr) ctx.body.accept(this);

        for(String param : params.getParams()) {
            res = new ASTAbs(param, res);
        }

        return res;
    }

    // Visit a recursive abstraction node
    @Override
    public AST visitRecAbstraction(MMLParser.RecAbstractionContext ctx) {
        return new ASTAbsRec(
                ctx.name.getText(),
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


    // --- Operators and build-in

    // Visit a unary operator
    @Override
    public AST visitUnOp(MMLParser.UnOpContext ctx) {
        String opName = ctx.op.getText();
        ASTExpr val = (ASTExpr) ctx.arg.accept(this);

        switch (opName) {
            case "!":
                return new ASTDeref(val);

            case "@":
                return new ASTRef(val);
        }

        parser.signalException("Unknown unary operation");
        return null;
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

            case ":=":
                return new ASTAssign(left, right);
        }

        parser.signalException("Unknown binary operation");
        return null;
    }

    // Visit a build in function node
    @Override
    public AST visitBuildIn(MMLParser.BuildInContext ctx) {
        String buildInName = ctx.name.getText();
        ASTExprs args = (ASTExprs) ctx.arguments.accept(this);

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

        parser.signalException("Unknown build-in name");
        return null;
    }


    // --- Control structures

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

    // Visit a let in node
    @Override
    public AST visitLetIn(MMLParser.LetInContext ctx) {
        return new ASTLet(
                ctx.name.getText(),
                (ASTExpr) ctx.value.accept(this),
                (ASTExpr) ctx.body.accept(this)
        );
    }


    // --- Base expressions (int, Nil, var...)

    // Visit an integer
    @Override
    public AST visitInteger(MMLParser.IntegerContext ctx) {
        return new ASTInt(Integer.parseInt(ctx.getText()));
    }

    // Visit a nil node
    @Override
    public AST visitNil(MMLParser.NilContext ctx) {
        return new ASTNil();
    }

    // Visit a unit node
    @Override
    public AST visitUnit(MMLParser.UnitContext ctx) {
        return new ASTUnit();
    }

    // Visit a variable node
    @Override
    public AST visitVariable(MMLParser.VariableContext ctx) {
        return new ASTVar(ctx.getText());
    }

}
