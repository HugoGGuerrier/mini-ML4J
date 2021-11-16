package MML4J.main.ast;

import MML4J.main.exceptions.TypingException;
import MML4J.main.typist.utils.EquationGenerator;
import MML4J.main.typist.equation_system.nodes.Node;

import java.util.Map;
import java.util.Objects;

public class ASTAbs extends ASTExpr {

    // ----- Attributes -----


    protected final String param;
    protected final ASTExpr body;


    // ----- Constructors -----


    public ASTAbs(String param, ASTExpr body) {
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
        return "fn(" + param + "){ " + body.toString() + " }";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ASTAbs abs = (ASTAbs) o;
        return Objects.equals(param, abs.param) && Objects.equals(body, abs.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(param, body);
    }

}
