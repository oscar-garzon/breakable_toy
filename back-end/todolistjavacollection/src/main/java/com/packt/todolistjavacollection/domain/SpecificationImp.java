package com.packt.todolistjavacollection.domain;


// If a filter value is "" then the predicate will noot
// verify that spec. 
public class SpecificationImp implements Specification<ToDo> {

    
    private String filterText;

    // filterPriority only have four possible values: all, high, medium, low
    private String filterPriority;
    
    // filterDone only have three possible values: all, true, false
    private String filterDone;

    
    public SpecificationImp(String filterText, String filterPriority, String filterDone){
        this.filterText = filterText;
        this.filterPriority = filterPriority;
        this.filterDone = filterDone;
    }
    
    public boolean toPredicate(ToDo todo){
        return (todo.getText().toLowerCase().contains(filterText) || filterText.toLowerCase().equals("all"))&&
               (todo.getPriority().equals(filterPriority) || filterPriority.equals("all")) &&
               (filterDone.equals("all") || (Boolean.parseBoolean(filterDone) == todo.isDone()));
    }

    // Returns true if there are none filters. False in other case.
    public boolean isEmpty(){
        return filterText.equals("") && 
                filterPriority.equals("") &&
                filterDone.equals("");
    }

}

