package com.packt.todolistjavacollection.domain;

public interface Specification<T> {

    // Returns true if the ToDo todo satisfies the specification
    // defined in this method.
    public boolean toPredicate(T entity);
}
