package MML4J.main.ast;

import MML4J.main.exceptions.TypingException;
import MML4J.main.typist.EquationGenerator;
import MML4J.main.typist.equation_graph.Node;

import java.util.Map;
import java.util.Objects;

public class ASTAbsRec extends ASTExpr {

    // ----- Attributes -----


    private final String param;
    private final ASTExpr body;


    // ----- Constructors -----

    public ASTAbsRec(String param, ASTExpr body) {
        this.param = param;
        this.body = body;
    }


    // ----- Getters -----


    public String getParam() {
        return param;
    }

    public ASTExpr getBody() {
        return body;
    }


    // ----- Override methods -----


    @Override
    public void acceptEqGenerator(EquationGenerator gen, Node target, Map<String, Node> context) throws TypingException {
        gen.generate(this, target, context);
    }

    @Override
    public String toString() {
        return "fnrec(" + param + "){ " + body.toString() + " }";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ASTAbsRec astAbsRec = (ASTAbsRec) o;
        return Objects.equals(param, astAbsRec.param) && Objects.equals(body, astAbsRec.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(param, body);
    }

}
