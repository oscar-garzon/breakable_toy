package com.packt.todolistjavacollection.domain;

import java.time.LocalDateTime;
import java.util.Comparator;

import jakarta.validation.constraints.NotBlank;

//import jakarta.validation.constraints.FutureOrPresent;

public class ToDo{

    private Long id;

    @NotBlank(message = "This field is required")
    private String text, priority;
    
    // @DateTimeFormat(pattern="MM/dd/yyyy")
    // @FutureOrPresent
    private LocalDateTime creation_date, done_date, due_date;
    private boolean done;

    public ToDo(String text, String priority, LocalDateTime due_date) {
        this.text = text;
        this.id = null;
        this.due_date = due_date;
        this.done_date = null;
        this.creation_date = LocalDateTime.now();
        this.priority = priority;
        this.done = false;
    }

    public ToDo(ToDo entity){
        this.text = entity.getText();
        this.id = entity.getId();
        this.due_date = entity.getDue_date();
        this.done_date = entity.getDone_date();
        this.creation_date = entity.getCreation_date();
        this.priority =entity.getPriority();
        this.done = entity.isDone();
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public LocalDateTime getDue_date() {
        return due_date;
    }
    public void setDue_date(LocalDateTime due_date) {
        this.due_date = due_date;
    }
    public LocalDateTime getDone_date() {
        return done_date;
    }
    public void setDone_date(LocalDateTime done_date) {
        this.done_date = done_date;
    }
    public String getPriority() {
        return priority;
    }
    public void setPriority(String priority) {
        this.priority = priority;
    }
    public LocalDateTime getCreation_date() {
        return creation_date;
    }
    public void setCreation_date(LocalDateTime creation_date) {
        this.creation_date = creation_date;
    }
    public boolean isDone() {
        return done;
    }
    public void setDone(boolean done) {
        this.done = done;
    }

    public void setDoneAndDoneDate() {
        done = true;
        done_date = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "[text=" + text + ", priority: " + priority + ", due date: " + due_date + "]";
    }

    // Function that updates text, due_date and priority
    public void update(ToDo todo){
        this.text = todo.getText();
        this.priority = todo.getPriority();
        this.due_date = todo.getDue_date();
        return;
    }

    // Comparator to sort todos by priority
    public static Comparator<ToDo> PriorityComparatorASC = new Comparator<ToDo>() {
        
        @Override
        public int compare(ToDo td1, ToDo td2) {
            // if(!(td1.getClass().equals(td2.getClass()))){
            //     throw ClassCastException;
            // }

            if (td1.getPriority().equals("low")){
                if (td2.getPriority().equals("low")){
                    return 0;
                }
                else{
                    return -1;
                }
            }
            else if(td1.getPriority().equals("medium")){
                if(td2.getPriority().equals("low")) {
                    return 1;
                }
                else if(td2.getPriority().equals("medium")){
                    return 0;
                }
                else{
                    return -1;
                }
            }
            else {
                if(td2.getPriority().equals("high")) {
                    return 0;
                }
                else {
                    return -1;
                }
            }
            
        }
    };

    public static Comparator<ToDo> PriorityComparatorDESC = new Comparator<ToDo>() {
        @Override
        public int compare(ToDo td1, ToDo td2){
            if (td1.getPriority().equals("high")){
                if (td2.getPriority().equals("high")){
                    return 0;
                }
                else{
                    return -1;
                }
            }
            else if(td1.getPriority().equals("medium")){
                if(td2.getPriority().equals("low")) {
                    return -1;
                }
                else if(td2.getPriority().equals("medium")){
                    return 0;
                }
                else{
                    return 1;
                }
            }
            else {
                if(td2.getPriority().equals("low")) {
                    return 0;
                }
                else {
                    return 1;
                }
            }
        }
    };

    public static Comparator<ToDo> DueDateComparator = new Comparator<ToDo>() {
        @Override
        public int compare(ToDo td1, ToDo td2){
            return td1.getDue_date().compareTo(td2.getDue_date());
        }
    };

    public static Comparator<ToDo> PriorityAscAndDueDateAscComparator = new Comparator<ToDo>() {
        @Override
        public int compare(ToDo td1, ToDo td2) {
            if (td1.getPriority().equals("low")){
                if (td2.getPriority().equals("low")){ // Both todos has a low priority
                    return td1.getDue_date().compareTo(td2.getDue_date());
                }
                else {
                    return -1;
                }
            }
            else if(td1.getPriority().equals("medium")){
                if(td2.getPriority().equals("low")) {
                    return 1;
                }
                else if(td2.getPriority().equals("medium")){
                    return td1.getDue_date().compareTo(td2.getDue_date());
                }
                else{
                    return -1;
                }
            }
            else {
                if(td2.getPriority().equals("high")) {
                    return td1.getDue_date().compareTo(td2.getDue_date());
                }
                else {
                    return -1;
                }
            }
        }
    };

    public static Comparator<ToDo> PriorityAscAndDueDateDescComparator = new Comparator<ToDo>() {
        @Override
        public int compare(ToDo td1, ToDo td2) {
            if (td1.getPriority().equals("low")){
                if (td2.getPriority().equals("low")){ // Both todos has a low priority
                    return -1 * td1.getDue_date().compareTo(td2.getDue_date());
                }
                else {
                    return -1;
                }
            }
            else if(td1.getPriority().equals("medium")){
                if(td2.getPriority().equals("low")) {
                    return 1;
                }
                else if(td2.getPriority().equals("medium")){
                    return -1 * td1.getDue_date().compareTo(td2.getDue_date());
                }
                else{
                    return -1;
                }
            }
            else {
                if(td2.getPriority().equals("high")) {
                    return -1 * td1.getDue_date().compareTo(td2.getDue_date());
                }
                else {
                    return -1;
                }
            }
        }
    };

    public static Comparator<ToDo> PriorityDescAndDueDateAscComparator = new Comparator<ToDo>() {
        @Override
        public int compare(ToDo td1, ToDo td2) {
            if (td1.getPriority().equals("low")){
                if (td2.getPriority().equals("low")){ // Both todos has a low priority
                    return td1.getDue_date().compareTo(td2.getDue_date());
                }
                else {
                    return 1;
                }
            }
            else if(td1.getPriority().equals("medium")){
                if(td2.getPriority().equals("low")) {
                    return -1;
                }
                else if(td2.getPriority().equals("medium")){
                    return td1.getDue_date().compareTo(td2.getDue_date());
                }
                else{
                    return 1;
                }
            }
            else {
                if(td2.getPriority().equals("high")) {
                    return td1.getDue_date().compareTo(td2.getDue_date());
                }
                else {
                    return -1;
                }
            }
        }
    };

    public static Comparator<ToDo> PriorityDescAndDueDateDescComparator = new Comparator<ToDo>() {
        @Override
        public int compare(ToDo td1, ToDo td2) {
            if (td1.getPriority().equals("low")){
                if (td2.getPriority().equals("low")){ // Both todos has a low priority
                    return -1 * td1.getDue_date().compareTo(td2.getDue_date());
                }
                else {
                    return 1;
                }
            }
            else if(td1.getPriority().equals("medium")){
                if(td2.getPriority().equals("low")) {
                    return -1;
                }
                else if(td2.getPriority().equals("medium")){
                    return -1 * td1.getDue_date().compareTo(td2.getDue_date());
                }
                else{
                    return 1;
                }
            }
            else {
                if(td2.getPriority().equals("high")) {
                    return -1 * td1.getDue_date().compareTo(td2.getDue_date());
                }
                else {
                    return -1;
                }
            }
        }
    };

    public static Comparator<ToDo> DueDateAscAndPriorityAscComparator = new Comparator<ToDo>() {
        @Override
        public int compare(ToDo td1, ToDo td2){
            int due_date_comparation = td1.getDue_date().compareTo(td2.getDue_date());
            
            if(due_date_comparation == 0){
                return PriorityComparatorASC.compare(td1, td2);
            }
            else {
                return due_date_comparation;
            }
        }
    };

    public static Comparator<ToDo> DueDateAscAndPriorityDescComparator = new Comparator<ToDo>() {
        @Override
        public int compare(ToDo td1, ToDo td2){
            int due_date_comparation = td1.getDue_date().compareTo(td2.getDue_date());
            
            if(due_date_comparation == 0){
                return PriorityComparatorDESC.compare(td1, td2);
            }
            else {
                return due_date_comparation;
            }
        }
    };

    public static Comparator<ToDo> DueDateDescAndPriorityAscComparator = new Comparator<ToDo>() {
        @Override
        public int compare(ToDo td1, ToDo td2){
            int due_date_comparation = -1 * td1.getDue_date().compareTo(td2.getDue_date());
            
            if(due_date_comparation == 0){
                return PriorityComparatorASC.compare(td1, td2);
            }
            else {
                return due_date_comparation;
            }
        }
    };

    public static Comparator<ToDo> DueDateDescAndPriorityDescComparator = new Comparator<ToDo>() {
        @Override
        public int compare(ToDo td1, ToDo td2){
            int due_date_comparation = -1 * td1.getDue_date().compareTo(td2.getDue_date());
            
            if(due_date_comparation == 0){
                return PriorityComparatorDESC.compare(td1, td2);
            }
            else {
                return due_date_comparation;
            }
        }
    };



    



}
