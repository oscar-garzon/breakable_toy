package com.packt.todolistjavacollection.domain;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;

//import jakarta.validation.constraints.FutureOrPresent;

public class ToDo {

    private Long id;

    @NotBlank(message = "This field is required")
    private String text, priority;
    
    // @DateTimeFormat(pattern="MM/dd/yyyy")
    //@FutureOrPresent
    private String due_date;
   
    private LocalDateTime creation_date, done_date;
    private boolean done;

    public ToDo(String text, String priority, String due_date) {
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
    public String getDue_date() {
        return due_date;
    }
    public void setDue_date(String due_date) {
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

    // Function that updates text, due_date and priority
    public void update(ToDo todo){
        this.text = todo.getText();
        this.priority = todo.getPriority();
        this.due_date = todo.getDue_date();
        return;
    }

    public void setDoneAndDoneDate() {
        done = true;
        done_date = LocalDateTime.now();
    }

}
