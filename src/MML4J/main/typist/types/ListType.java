package MML4J.main.typist.types;

import java.util.Objects;

public class ListType extends Type {

    // ----- Attributes -----


    private final Type type;


    // ----- Constructors -----


    public ListType(Type type) {
        this.type = type;
    }

    // ----- Getters -----


    public Type getType() {
        return type;
    }


    // ----- Override methods -----


    @Override
    public String toString() {
        return "[" + type + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListType listType = (ListType) o;
        return Objects.equals(type, listType.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }

}
