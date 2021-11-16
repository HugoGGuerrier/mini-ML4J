package MML4J.main.ast;

import MML4J.main.exceptions.TypingException;
import MML4J.main.typist.utils.EquationGenerator;
import MML4J.main.typist.equation_system.nodes.Node;

import java.util.Map;

public abstract class ASTExpr extends AST {

    // Accept the equation generator
    public abstract void acceptEqGenerator(EquationGenerator gen, Node target, Map<String, Node> context) throws TypingException;

}
