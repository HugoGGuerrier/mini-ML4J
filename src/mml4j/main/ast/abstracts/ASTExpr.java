package mml4j.main.ast.abstracts;

import mml4j.main.exceptions.TypingException;
import mml4j.main.typist.EquationGenerator;
import mml4j.main.typist.equation_system.nodes.abstracts.Node;

import java.util.Map;

public abstract class ASTExpr extends AST {

    // Accept the equation generator
    public abstract void acceptEqGenerator(EquationGenerator gen, Node target, Map<String, Node> context) throws TypingException;

}
