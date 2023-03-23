package com.packt.todolistjavacollection.exceptions;

import java.lang.RuntimeException;

public class ToDoNotFoundException extends RuntimeException{

    public ToDoNotFoundException(){
        super("To Do not found by id");
    }
    
}
