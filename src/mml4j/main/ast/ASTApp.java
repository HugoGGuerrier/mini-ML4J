package mml4j.main.ast;

import mml4j.main.ast.abstracts.ASTExpr;
import mml4j.main.exceptions.TypingException;
import mml4j.main.typist.EquationGenerator;
import mml4j.main.typist.equation_system.nodes.abstracts.Node;

import java.util.Map;
import java.util.Objects;

public class ASTApp extends ASTExpr {

    // ----- Attributes -----


    protected final ASTExpr function;
    protected final ASTExpr arg;


    // ----- Constructors -----


    public ASTApp(ASTExpr function, ASTExpr arg) {
        this.function = function;
        this.arg = arg;
    }


    // ----- Getters -----


    public ASTExpr getFunction() {
        return function;
    }

    public ASTExpr getArg() {
        return arg;
    }


    // ----- Override methods -----


    @Override
    public void acceptEqGenerator(EquationGenerator gen, Node target, Map<String, Node> context) throws TypingException {
        gen.generate(this, target, context);
    }

    @Override
    public String toString() {
        return function.toString() + '(' + arg.toString() + ')';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ASTApp app = (ASTApp) o;
        return Objects.equals(function, app.function) && Objects.equals(arg, app.arg);
    }

    @Override
    public int hashCode() {
        return Objects.hash(function, arg);
    }

}
