package mml4j.main.ast.utils;

import mml4j.main.Pair;
import mml4j.main.ast.abstracts.AST;
import mml4j.main.ast.abstracts.ASTExpr;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class ASTAffect extends AST {

    // ----- Attributes -----


    protected final List<Pair<String, ASTExpr>> affects;


    // ----- Constructors -----


    public ASTAffect(Pair<String, ASTExpr> single) {
        this.affects = new LinkedList<>();
        this.affects.add(single);
    }


    // ----- Getters -----


    public List<Pair<String, ASTExpr>> getAffects() {
        return affects;
    }

    public Pair<String, ASTExpr> get(int index) {
        return affects.get(index);
    }

    public int size() {
        return affects.size();
    }


    // ----- Setters -----


    public void addAffect(Pair<String, ASTExpr> affect) {
        affects.add(affect);
    }


    // ----- Override methods -----


    @Override
    public String toString() {
        return affects.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ASTAffect astAffect = (ASTAffect) o;
        return Objects.equals(affects, astAffect.affects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(affects);
    }

}
