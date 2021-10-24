package MML4J.main.ast;

import MML4J.main.exceptions.TypingException;
import MML4J.main.typist.EquationGenerator;
import MML4J.main.typist.equation_graph.Node;

import java.util.Map;
import java.util.Objects;

public class ASTHead extends ASTExpr {

    // ----- Attributes -----


    private final ASTExpr list;


    // ----- Constructors -----


    public ASTHead(ASTExpr list) {
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
        return "head(" + list + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ASTHead astHead = (ASTHead) o;
        return Objects.equals(list, astHead.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(list);
    }

}
