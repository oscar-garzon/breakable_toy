package com.packt.todolistjavacollection.domain;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.packt.todolistjavacollection.repository.CrudRepositoryImp;
import com.packt.todolistjavacollection.repository.Specification;

@Component("todoRepositoryImp")
public class ToDoRepositoryImp extends CrudRepositoryImp implements ToDoRepository {

    @Autowired
    private Metrics metrics;
    
    public ToDoRepositoryImp(){
        super();
    }
    
    public Iterable<ToDo> filterBy(Specification spec){
        return null;
    }

    public Iterable<ToDo> sortBy(Specification spec){
        return null;
    }

    public HashMap<String, String> getMetrics()
    {
        return metrics.updateAndGet(findAll());
    }
}
