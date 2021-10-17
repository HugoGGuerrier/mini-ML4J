package MML4J.main.ast;

import java.util.Objects;

public class ASTApp extends ASTExpr {

    // ----- Attributes -----

    private final ASTExpr function;
    private final ASTExpr arg;

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
