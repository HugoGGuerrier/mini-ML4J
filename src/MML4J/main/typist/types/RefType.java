package MML4J.main.typist.types;

import java.util.Objects;

public class RefType extends Type {

    // ----- Attributes -----


    protected final Type contentType;


    // ----- Constructors -----


    public RefType(Type contentType) {
        this.contentType = contentType;
    }


    // ----- Getters -----


    public Type getContentType() {
        return contentType;
    }


    // ----- Override methods -----


    @Override
    public String toString() {
        return "Ref(" + contentType + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RefType refType = (RefType) o;
        return Objects.equals(contentType, refType.contentType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contentType);
    }

}