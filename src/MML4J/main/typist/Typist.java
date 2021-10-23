package MML4J.main.typist;

import MML4J.main.Utils;
import MML4J.main.ast.ASTExpr;
import MML4J.main.exceptions.TypingException;
import MML4J.main.typist.equation_graph.Node;
import MML4J.main.typist.equation_graph.SimpleNode;
import MML4J.main.typist.type.Type;

import java.util.HashMap;

public class Typist {

    // ----- Class methods -----


    /**
     * Get the inferred type for the given expression
     *
     * @param expr The expresion
     * @return The inferred type
     */
    public Type typeExpression(ASTExpr expr) throws TypingException {
        // Generate the graph
        Node theGraph = this.generateEquations(expr);

        // Return the unified type
        return unifyEquations(theGraph);
    }

    /**
     * Get the equation graph from an AST expression
     *
     * @param expr The AST representing the expression
     * @return The equation graph extracted from the expression
     */
    public Node generateEquations(ASTExpr expr) throws TypingException {
        // Verify the non nullity of the expr
        if(expr == null) throw new TypingException("Cannot generate equations from a null AST");

        // Create the equation generator and visit the ast
        EquationGenerator generator = new EquationGenerator();
        SimpleNode baseNode = generator.getNextNode();
        expr.acceptEqGenerator(generator, baseNode, new HashMap<>());

        // Return the base node
        return baseNode;
    }

    /**
     * Unify the equation graph and get the final type
     *
     * @param graph The equation graph to unify
     * @return The final type
     * @throws TypingException If the graph cannot be unified
     */
    public Type unifyEquations(Node graph) throws TypingException {
        // Unify the node
        Node theNode = graph.unify();

        // Make the debug print
        if(Utils.DEBUG) {
            System.out.println("Result of unification : " + theNode);
        }

        // Turn the graph into a type
        TypeTranslator translator = new TypeTranslator();
        return theNode.acceptTranslator(translator);
    }

}
