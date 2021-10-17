package MML4J.main.ast;

import java.util.Objects;

public class ASTVar extends ASTExpr {

    // ----- Attributes -----

    private final String name;

    // ----- Constructors -----

    public ASTVar(String name) {
        this.name = name;
    }

    // ----- Getters -----

    public String getName() {
        return name;
    }

    // ----- Override methods -----

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ASTVar var = (ASTVar) o;
        return Objects.equals(name, var.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
