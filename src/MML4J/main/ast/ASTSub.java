package MML4J.main.ast;

import MML4J.main.exceptions.TypingException;
import MML4J.main.typist.EquationGenerator;
import MML4J.main.typist.equation_graph.Node;

import java.util.Map;
import java.util.Objects;

public class ASTSub extends ASTExpr {

    // ----- Attributes -----


    private final ASTExpr left;
    private final ASTExpr right;


    // ----- Constructors -----


    public ASTSub(ASTExpr left, ASTExpr right) {
        this.left = left;
        this.right = right;
    }


    // ----- Getters -----


    public ASTExpr getLeft() {
        return left;
    }

    public ASTExpr getRight() {
        return right;
    }


    // ----- Override methods -----


    @Override
    public void acceptEqGenerator(EquationGenerator gen, Node target, Map<String, Node> context) throws TypingException {
        gen.generate(this, target, context);
    }

    @Override
    public String toString() {
        return left + " - " + right;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ASTSub astSub = (ASTSub) o;
        return Objects.equals(left, astSub.left) && Objects.equals(right, astSub.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }

}
