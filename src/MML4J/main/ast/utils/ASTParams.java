package MML4J.main.ast.utils;

import MML4J.main.ast.abstracts.AST;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class ASTParams extends AST {

    // ----- Attributes -----


    protected final List<String> params;


    // ----- Constructors -----


    public ASTParams(String single) {
        params = new LinkedList<>();
        params.add(single);
    }


    // ----- Getters -----


    public List<String> getParams() {
        return params;
    }

    public String get(int index) {
        return params.get(index);
    }

    public int size() {
        return params.size();
    }


    // ----- Setters -----


    public void addParam(String param) {
        params.add(param);
    }


    // ----- Override methods -----


    @Override
    public String toString() {
        return params.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ASTParams astParams = (ASTParams) o;
        return Objects.equals(params, astParams.params);
    }

    @Override
    public int hashCode() {
        return Objects.hash(params);
    }

}
