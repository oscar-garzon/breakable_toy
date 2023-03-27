package com.packt.todolistjavacollection.domain;

import java.util.HashMap;

import com.packt.todolistjavacollection.repository.CrudRepository;

public interface ToDoRepository extends CrudRepository<ToDo, Long> {
    
    public Iterable<ToDo> filterBy(Specification<ToDo> spec);

    public Iterable<ToDo> sortBy(Specification<ToDo> spec);

    public HashMap<String, String> getMetrics();
}
