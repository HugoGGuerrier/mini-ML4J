package mml4j.main.ast;

import mml4j.main.ast.abstracts.ASTExpr;
import mml4j.main.exceptions.TypingException;
import mml4j.main.typist.EquationGenerator;
import mml4j.main.typist.equation_system.nodes.abstracts.Node;

import java.util.Map;
import java.util.Objects;

public class ASTAbsRec extends ASTAbs {

    // ----- Attributes -----


    protected final String name;


    // ----- Constructors -----


    public ASTAbsRec(String name, String param, ASTExpr body) {
        super(param, body);
        this.name = name;
    }


    // ----- Getters -----


    public String getName() {
        return name;
    }


    // ----- Override methods -----


    @Override
    public void acceptEqGenerator(EquationGenerator gen, Node target, Map<String, Node> context) throws TypingException {
        gen.generate(this, target, context);
    }

    @Override
    public String toString() {
        return "rec " + name + "(" + param + "){ " + body.toString() + " }";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ASTAbsRec astAbsRec = (ASTAbsRec) o;
        return Objects.equals(name, astAbsRec.name) && Objects.equals(param, astAbsRec.param) && Objects.equals(body, astAbsRec.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, param, body);
    }

}
