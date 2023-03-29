package com.packt.todolistjavacollection.domain;

import java.util.ArrayList;
import java.util.HashMap;

import com.packt.todolistjavacollection.repository.CrudRepository;

public interface ToDoRepository extends CrudRepository<ToDo, Long> {
    
    // Returns the tasks that satisfies the specification
    public ArrayList<ToDo> filterBy(Specification<ToDo> spec);

    // Returns a HashMaps with the metric name as the key and the metric 
    // score as the value.
    public HashMap<String, String> getMetrics();

    // Returns the tasks sorted as specified by the Sort object
    public ArrayList<ToDo> findAll(Sort sort);
}
