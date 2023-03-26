package com.packt.todolistjavacollection;

import java.time.LocalDateTime;

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

	
	@Override
    public void run(String... args) throws Exception {
		String high = "high";
		String medium = "medium";
		String low = "low";
		LocalDateTime date1 = LocalDateTime.of(2023, 03, 24, 18, 23);
		LocalDateTime date2 = LocalDateTime.of(2023, 03, 25, 10, 23);
		LocalDateTime date3 = LocalDateTime.of(2023, 03, 24, 19, 23);


		repository.save(new ToDo("Implementa componente 1",high, date1));	
		repository.save(new ToDo("Implementa componente 2", high, date2));	
		repository.save(new ToDo("Implementa componente 3", medium , date2));	
		repository.save(new ToDo("Implementa componente 4", medium, date3));	
		repository.save(new ToDo("Implementa componente 5", low, date1));	
		for (ToDo todo : repository.findAll()) {
            logger.info(todo.getText() + " " + todo.getPriority());
        }
	}
}
