package com.packt.todolistjavacollection.domain;

import java.util.HashMap;

import com.packt.todolistjavacollection.repository.CrudRepository;
import com.packt.todolistjavacollection.repository.Specification;

public interface ToDoRepository extends CrudRepository<ToDo, Long> {
    
    public Iterable<ToDo> filterBy(Specification spec);

    public Iterable<ToDo> sortBy(Specification spec);

    public HashMap<String, String> getMetrics();
}
