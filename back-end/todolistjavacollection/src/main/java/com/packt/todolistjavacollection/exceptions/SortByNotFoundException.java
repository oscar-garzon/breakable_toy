package com.packt.todolistjavacollection.exceptions;

import java.lang.RuntimeException;

public class SortByNotFoundException extends RuntimeException {
    public SortByNotFoundException(){
        super("Sorting is only by priority or due_date");
    }
}
