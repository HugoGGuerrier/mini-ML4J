package mml4j.main.parser;

import mml4j.main.Pair;
import mml4j.main.ast.abstracts.AST;
import mml4j.main.ast.abstracts.ASTExpr;
import mml4j.main.ast.utils.ASTAffect;
import mml4j.main.ast.utils.ASTExprs;
import mml4j.main.ast.utils.ASTParams;
import mml4j.main.parser.antlr.MMLBaseVisitor;
import mml4j.main.parser.antlr.MMLParser;
import mml4j.main.ast.*;

import java.util.List;

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

    @Override
    public AST visitSingleAffect(MMLParser.SingleAffectContext ctx) {
        return new ASTAffect(new Pair<>(ctx.name.getText(), (ASTExpr) ctx.value.accept(this)));
    }

    @Override
    public AST visitMultipleAffect(MMLParser.MultipleAffectContext ctx) {
        ASTAffect affect = (ASTAffect) ctx.next.accept(this);
        affect.addAffect(new Pair<>(ctx.name.getText(), (ASTExpr) ctx.value.accept(this)));
        return affect;
    }

    // --- Syntactic sugars

    @Override
    public AST visitListSugar(MMLParser.ListSugarContext ctx) {
        ASTExprs listContent = (ASTExprs) ctx.inside.accept(this);
        return processListSugar(listContent.getExprs());
    }

    protected ASTApp processListSugar(List<ASTExpr> listContent) {
        if(listContent.size() == 0) {
            return new ASTApp(new ASTApp(new ASTVar("cons"), new ASTVar("--weak")), new ASTNil());
        } else if(listContent.size() == 1) {
            return new ASTApp(new ASTApp(new ASTVar("cons"), listContent.get(0)), new ASTNil());
        } else {
            return new ASTApp(new ASTApp(new ASTVar("cons"), listContent.get(0)), processListSugar(listContent.subList(1, listContent.size())));
        }
    }

    @Override
    public AST visitSeqSugar(MMLParser.SeqSugarContext ctx) {
        return new ASTLet("--ignored", (ASTExpr) ctx.ignored.accept(this), (ASTExpr) ctx.real.accept(this));
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
                return new ASTApp(new ASTVar("!"), val);

            case "@":
                return new ASTApp(new ASTVar("@"), val);
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
                return new ASTApp(new ASTApp(new ASTVar("+"), left), right);

            case "-":
                return new ASTApp(new ASTApp(new ASTVar("-"), left), right);

            case ":=":
                return new ASTApp(new ASTApp(new ASTVar(":="), left), right);
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
                if(args.size() == 2) return new ASTApp(new ASTApp(new ASTVar("cons"), args.get(0)), args.get(1));
                else parser.signalException("\"cons\" build-in takes exactly 2 arguments");
                break;

            case "head":
                if(args.size() == 1) return new ASTApp(new ASTVar("head"), args.get(0));
                else parser.signalException("\"head\" build-in takes exactly 1 argument");
                break;

            case "tail":
                if(args.size() == 1) return new ASTApp(new ASTVar("tail"), args.get(0));
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
        ASTAffect affect = (ASTAffect) ctx.affect.accept(this);
        ASTExpr res = (ASTExpr) ctx.body.accept(this);

        for(Pair<String, ASTExpr> curAff : affect.getAffects()) {
            res = new ASTLet(curAff.getLeft(), curAff.getRight(), res);
        }

        return res;
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
