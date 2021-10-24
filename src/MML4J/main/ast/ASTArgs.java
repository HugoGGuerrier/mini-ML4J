package MML4J.main.ast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ASTArgs extends AST {

    // ----- Attributes -----


    private final List<ASTExpr> args;


    // ----- Constructors -----


    public ASTArgs(ASTExpr sole) {
        this.args = new ArrayList<>();
        this.args.add(sole);
    }


    // ----- Getters -----


    public List<ASTExpr> getArgs() {
        return args;
    }

    public ASTExpr get(int index) {
        return args.get(index);
    }

    public int size() {
        return args.size();
    }


    // ----- Setters -----


    public void addArg(ASTExpr arg) {
        args.add(0, arg);
    }


    // ----- Override methods -----


    @Override
    public String toString() {
        return this.args.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ASTArgs astArgs = (ASTArgs) o;
        return Objects.equals(args, astArgs.args);
    }

    @Override
    public int hashCode() {
        return Objects.hash(args);
    }

}
