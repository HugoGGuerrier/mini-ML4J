package MML4J.main.typist.type;

import java.util.Objects;

public class SimpleType extends Type {

    // ----- Attributes -----


    private final int id;


    // ----- Constructors -----


    public SimpleType(int name) {
        this.id = name;
    }


    // ----- Getters -----


    public String getName() {
        return "T" + id;
    }


    // ----- Override type -----

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleType that = (SimpleType) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
