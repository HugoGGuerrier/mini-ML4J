package MML4J.main.typist.type;

import java.util.Objects;

public class SimpleType extends Type {

    // ----- Attributes -----

    private final String name;

    // ----- Constructors -----

    public SimpleType(String name) {
        this.name = name;
    }

    // ----- Getters -----

    public String getName() {
        return name;
    }

    // ----- Override type -----

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleType that = (SimpleType) o;
        return Objects.equals(name, that.name);
    }

}
