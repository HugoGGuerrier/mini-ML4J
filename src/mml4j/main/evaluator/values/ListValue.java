package mml4j.main.evaluator.values;

import mml4j.main.evaluator.values.abstracts.Value;

public class ListValue extends Value {

    // ----- Attributes -----


    /** Head of the list */
    protected final Value head;

    /** Tail of the list */
    protected final ListValue tail;


    // ----- Constructors -----


    /**
     * Create a new list value with the head and the tail
     *
     * @param head The list head
     * @param tail The list tail
     */
    public ListValue(Value head, ListValue tail) {
        this.head = head;
        this.tail = tail;
    }


    // ----- Getters -----


    public Value getHead() {
        return head;
    }

    public ListValue getTail() {
        return tail;
    }


    // ----- Override methods -----


    @Override
    public String toString() {
        StringBuilder res = new StringBuilder("[");
        ListValue curList = this;
        while (curList != NilValue.getInstance()) {
            res.append(curList.head.toString());
            if(curList.tail != NilValue.getInstance()) res.append(", ");
            curList = curList.tail;
        }
        res.append("]");
        return res.toString();
    }

}
