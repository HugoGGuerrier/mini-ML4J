package MML4J.main.typist;

import MML4J.main.Utils;
import MML4J.main.ast.ASTExpr;
import MML4J.main.exceptions.TypingException;
import MML4J.main.typist.equation_system.nodes.Node;
import MML4J.main.typist.equation_system.EquationSystem;
import MML4J.main.typist.types.Type;
import MML4J.main.typist.utils.EquationGenerator;
import MML4J.main.typist.utils.TypeTranslator;

import java.util.HashMap;

public class Typist {

    // ----- Class methods -----


    /**
     * Get the inferred type for the given expression
     *
     * @param expr The expresion
     * @return The inferred type
     */
    public static Type typeExpression(ASTExpr expr) throws TypingException {
        // Create the equation system from the ast
        EquationSystem equationSystem = new EquationSystem();
        EquationGenerator generator = new EquationGenerator(equationSystem);
        expr.acceptEqGenerator(generator, equationSystem.getInitNode(), new HashMap<>());

        // Do debug print
        if(Utils.DEBUG) {
            System.out.println("=== Typing the expression " + expr + "\n");
            System.out.println("Equation system :");
            System.out.println(equationSystem);
        }

        // Unify the equation system
        Node theNode = equationSystem.unify();

        // Do debug print
        if(Utils.DEBUG) System.out.println("Unified equation system : " + theNode);

        // Translate the node and return the type
        return theNode.acceptTranslator(new TypeTranslator());
    }

}
