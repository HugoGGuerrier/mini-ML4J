package MML4J.main.ast;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class ASTExprs extends AST {

    // ----- Attributes -----


    protected final List<ASTExpr> exprs;


    // ----- Constructors -----


    public ASTExprs() {
        this.exprs = new LinkedList<>();
    }

    public ASTExprs(ASTExpr single) {
        this.exprs = new LinkedList<>();
        this.exprs.add(single);
    }


    // ----- Getters -----


    public List<ASTExpr> getExprs() {
        return exprs;
    }

    public ASTExpr get(int index) {
        return exprs.get(index);
    }

    public int size() {
        return exprs.size();
    }


    // ----- Setters -----


    public void addExpr(ASTExpr arg) {
        exprs.add(0, arg);
    }


    // ----- Override methods -----


    @Override
    public String toString() {
        return exprs.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ASTExprs astArgs = (ASTExprs) o;
        return Objects.equals(exprs, astArgs.exprs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(exprs);
    }

}
