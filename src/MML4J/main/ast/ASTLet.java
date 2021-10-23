package MML4J.main.ast;

import MML4J.main.exceptions.TypingException;
import MML4J.main.typist.EquationGenerator;
import MML4J.main.typist.equation_graph.Node;

import java.util.Map;

public class ASTLet extends ASTExpr {

    // ----- Attributes -----

    private final String name;
    private final ASTExpr value;
    private final ASTExpr in;

    // ----- Constructors -----

    public ASTLet(String name, ASTExpr value, ASTExpr in) {
        this.name = name;
        this.value = value;
        this.in = in;
    }

    // ----- Getters -----

    public String getName() {
        return name;
    }

    public ASTExpr getValue() {
        return value;
    }

    public ASTExpr getIn() {
        return in;
    }

    // ----- Override methods -----

    @Override
    public void acceptEqGenerator(EquationGenerator gen, Node target, Map<String, Node> context) throws TypingException {
        gen.generate(this, target, context);
    }

    @Override
    public String toString() {
        return "let " + name + " = " + value + " in " + in;
    }
}
