package MML4J.main.ast;

import MML4J.main.exceptions.TypingException;
import MML4J.main.typist.EquationGenerator;
import MML4J.main.typist.equation_graph.Node;

import java.util.Map;
import java.util.Objects;

public class ASTTail extends ASTExpr {

    // ----- Attributes -----


    private final ASTExpr list;


    // ----- Constructors -----


    public ASTTail(ASTExpr list) {
        this.list = list;
    }


    // ----- Getters -----


    public ASTExpr getList() {
        return list;
    }


    // ----- Override methods -----


    @Override
    public void acceptEqGenerator(EquationGenerator gen, Node target, Map<String, Node> context) throws TypingException {
        gen.generate(this, target, context);
    }

    @Override
    public String toString() {
        return "tail(" + list + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ASTTail astTail = (ASTTail) o;
        return Objects.equals(list, astTail.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(list);
    }

}
