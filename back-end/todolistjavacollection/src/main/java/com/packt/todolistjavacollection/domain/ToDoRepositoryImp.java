package com.packt.todolistjavacollection.domain;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.packt.todolistjavacollection.exceptions.SortByNotFoundException;
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

    // Return all the elements with the sorts specified in sort
    public ArrayList<ToDo> findAll(Sort sort) {
        if(sort.hasPrincipalSort()){
            if(sort.hasSecondarySort()){
                return sortSecondary(sortPrincipal(sort.getPrincipalSortBy(), sort.getPrincipalSortOrder()),
                                     sort.getSecondarySortBy(),
                                     sort.getSecondarySortOrder());
            }
            else {
                return sortPrincipal(sort.getPrincipalSortBy(), sort.getPrincipalSortOrder());
            }
        }
        else{
            return (ArrayList<ToDo>) findAll();
        }
    }

    // Return a list with all elements sorted as indicated by principalSortBy and  
    // ordered as indicated by principalSortOrder
    public ArrayList<ToDo> sortPrincipal(String principalSortBy, String principalSortOrder) {
        ArrayList<ToDo> elements = (ArrayList<ToDo>) findAll();
        if (principalSortBy.equals("priority")){
            if(principalSortOrder.equals("ASC")){
                Collections.sort(elements, ToDo.PriorityComparatorASC);
            }
            if(principalSortOrder.equals("DESC")){
                Collections.sort(elements, ToDo.PriorityComparatorDESC);
            }
        }
            
        if (principalSortBy.equals("due_date")){
            if(principalSortOrder.equals("ASC")){
                Collections.sort(elements, ToDo.DueDateComparator);
            }
            if(principalSortOrder.equals("DESC")){
                Collections.sort(elements, Collections.reverseOrder(ToDo.DueDateComparator));
            }
        }
        return elements;
    }

    // elements is a list that has already been order by sortPrincipal.
    // Return elements list respecting the principal sorting and applies the secondary sorting
    // as indicated by secondarySortBy and secondarySortOrder. 
    public ArrayList<ToDo> sortSecondary(ArrayList<ToDo> elements, String secondarySortBy, String secondarySortOrder){
        return null;
    }
}
