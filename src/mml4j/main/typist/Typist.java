package mml4j.main.typist;

import mml4j.main.Utils;
import mml4j.main.ast.abstracts.ASTExpr;
import mml4j.main.exceptions.TypingException;
import mml4j.main.typist.equation_system.nodes.abstracts.Node;
import mml4j.main.typist.equation_system.EquationSystem;
import mml4j.main.typist.types.abstracts.Type;
import mml4j.main.typist.utils.TypeTranslator;

import java.util.HashMap;

/**
 * This class is the front end of the typing system
 *
 * @author Hugo GUERRIER
 */
public class Typist {

    // ----- Class methods -----


    /**
     * Get the inferred type for the given expression
     *
     * @param expr The expression
     * @return The inferred type
     * @throws TypingException if the expression cannot be typed
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
