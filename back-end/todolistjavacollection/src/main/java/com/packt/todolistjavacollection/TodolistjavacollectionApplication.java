package com.packt.todolistjavacollection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;

import com.packt.todolistjavacollection.domain.ToDo;
import com.packt.todolistjavacollection.domain.ToDoRepositoryImp;

@SpringBootApplication
public class TodolistjavacollectionApplication implements CommandLineRunner {
	private static final Logger logger =
             LoggerFactory.getLogger
                 (TodolistjavacollectionApplication.class);
	@Autowired
	private ToDoRepositoryImp repository;				 

	public static void main(String[] args) {
		SpringApplication.run(TodolistjavacollectionApplication.class, args);
		logger.info("Application started");
	}

	String high = "high";
	String medium = "medium";
	String low = "low";
	
	@Override
    public void run(String... args) throws Exception {
		repository.save(new ToDo("Implementa componente 1",high, "hoy"));	
		repository.save(new ToDo("Implementa componente 2", high, "manana"));	
		repository.save(new ToDo("Implementa componente 3", medium , "pasado manana"));	
		repository.save(new ToDo("Implementa componente 4", medium, "hoy"));	
		repository.save(new ToDo("Implementa componente 5", medium, "manana"));	
		for (ToDo todo : repository.findAll()) {
            logger.info(todo.getText() + " " + todo.getPriority());
        }
	}
}
