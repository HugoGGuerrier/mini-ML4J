package MML4J.main.typist.type;

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

}
