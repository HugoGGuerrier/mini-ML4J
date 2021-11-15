package MML4J.main.ast;

import MML4J.main.exceptions.TypingException;
import MML4J.main.typist.EquationGenerator;
import MML4J.main.typist.equation_system.Node;

import java.util.Map;

public class ASTUnit extends ASTExpr {

    // ----- Override methods -----


    @Override
    public void acceptEqGenerator(EquationGenerator gen, Node target, Map<String, Node> context) throws TypingException {
        gen.generate(this, target, context);
    }

    @Override
    public String toString() {
        return "()";
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof ASTUnit;
    }

}
