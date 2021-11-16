package MML4J.main.ast;

import MML4J.main.exceptions.TypingException;
import MML4J.main.typist.utils.EquationGenerator;
import MML4J.main.typist.equation_system.nodes.Node;

import java.util.Map;
import java.util.Objects;

public class ASTAssign extends ASTExpr {

    // ----- Attributes -----


    protected final ASTExpr left;
    protected final ASTExpr right;


    // ----- Constructors -----


    public ASTAssign(ASTExpr left, ASTExpr right) {
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
        return left + " := " + right;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ASTAssign astAssign = (ASTAssign) o;
        return Objects.equals(left, astAssign.left) && Objects.equals(right, astAssign.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }

}
