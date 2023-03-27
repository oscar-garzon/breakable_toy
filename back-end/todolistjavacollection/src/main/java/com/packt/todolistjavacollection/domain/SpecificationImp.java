package com.packt.todolistjavacollection.domain;


// If a filter value is "" then the predicate will noot
// verify that spec. 
public class SpecificationImp implements Specification<ToDo> {

    // if filterText equals "" it means all texts
    private String filterText;

    // filterPriority only have four possible values: "", high, medium, low
    private String filterPriority;
    
    // filterDone only have three possible values: "", true, false
    private String filterDone;

    
    public SpecificationImp(String filterText, String filterPriority, String filterDone){
        this.filterText = filterText;
        this.filterPriority = filterPriority;
        this.filterDone = filterDone;
    }
    
    public boolean toPredicate(ToDo todo){
        return todo.getText().contains(filterText) &&
               (todo.getPriority().equals(filterPriority) || filterPriority.equals("")) &&
               (filterDone.equals("") || (Boolean.parseBoolean(filterDone) == todo.isDone()));
    }

}

