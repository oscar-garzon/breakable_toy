package com.packt.todolistjavacollection.domain;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//import com.packt.todolistjavacollection.exceptions.SortByNotFoundException;
import com.packt.todolistjavacollection.repository.CrudRepositoryImp;

@Component("todoRepositoryImp")
public class ToDoRepositoryImp extends CrudRepositoryImp implements ToDoRepository {

    @Autowired
    private Metrics metrics;
    
    public ToDoRepositoryImp(){
        super();
    }
    
    public Iterable<ToDo> filterBy(Specification<ToDo> spec){
        ArrayList<ToDo> elements = (ArrayList<ToDo>)findAll();
        ArrayList<ToDo> filtered_elements = new ArrayList<>();
        for(ToDo todo : elements){
            if(spec.toPredicate(todo)){
                filtered_elements.add(todo);
            }
        }
        return filtered_elements;
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

        ArrayList<ToDo> elements = (ArrayList<ToDo>) findAll();
        if(!sort.hasPrincipalSort()){
            return elements;
        }

        String principalSortBy = sort.getPrincipalSortBy();
        String principalSortOrder = sort.getPrincipalSortOrder();

        if(sort.hasPrincipalSort() && sort.hasSecondarySort()){
            String secndarySortOrder = sort.getSecondarySortOrder();

            if(principalSortBy.equals("priority")){
                sortByPriorityAndDueDate(elements, principalSortOrder, secndarySortOrder);
            }
            else{
                sortByDueDateAndPriority(elements, principalSortOrder, secndarySortOrder);
            }
        }
        else {
            // Only has principal sort
            if(principalSortBy.equals("priority")){
                sortByPriority(elements, principalSortOrder);
            }
            else {
                sortByDueDate(elements, principalSortOrder);
            }
        }
        return elements;
    }

    private void sortByPriority(ArrayList<ToDo> elements, String prioritySortOrder){
        if(prioritySortOrder.equals("ASC")){
            Collections.sort(elements, ToDo.PriorityComparatorASC);
        }
        else{
            Collections.sort(elements, ToDo.PriorityComparatorDESC);
        }
    }

    private void sortByDueDate(ArrayList<ToDo> elements, String dueDateSortOrder){
        if(dueDateSortOrder.equals("ASC")){
            Collections.sort(elements, ToDo.DueDateComparator);
        }
        else{
            Collections.sort(elements, Collections.reverseOrder(ToDo.DueDateComparator));
        }
    }

    private void sortByPriorityAndDueDate(ArrayList<ToDo> elements, String prioritySortOrder, String dueDateSortOrder){
        if(prioritySortOrder.equals("ASC")){
            if(dueDateSortOrder.equals("ASC")){
                Collections.sort(elements, ToDo.PriorityAscAndDueDateAscComparator);
            }
            else{
                Collections.sort(elements, ToDo.PriorityAscAndDueDateDescComparator);
            }
        }
        else{
            if(dueDateSortOrder.equals("ASC")){
                Collections.sort(elements, ToDo.PriorityDescAndDueDateAscComparator);
            }
            else{
                Collections.sort(elements, ToDo.PriorityDescAndDueDateDescComparator);
            }
        }
    }

    private void sortByDueDateAndPriority(ArrayList<ToDo> elements, String dueDateSortOrder, String prioritySortOrder){
        if(dueDateSortOrder.equals("ASC")){
            if(prioritySortOrder.equals("ASC")){
                Collections.sort(elements, ToDo.DueDateAscAndPriorityAscComparator);
            }
            else{
                Collections.sort(elements, ToDo.DueDateAscAndPriorityDescComparator);
            }
        }
        else{
            if(prioritySortOrder.equals("ASC")){
                Collections.sort(elements, ToDo.DueDateDescAndPriorityAscComparator);
            }
            else{
                Collections.sort(elements, ToDo.DueDateDescAndPriorityDescComparator);
            }
        }
    }
}
