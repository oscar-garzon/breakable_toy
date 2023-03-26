package com.packt.todolistjavacollection.web;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.packt.todolistjavacollection.domain.ToDo;

@Component()
public class ToDoModelAssembler implements RepresentationModelAssembler<ToDo, EntityModel<ToDo>> {
    
    @Override
     public EntityModel<ToDo> toModel(ToDo todo) {
        System.out.println("CARNAL ENTRAMOS AL ASSEMBLER");
        return EntityModel.of(todo, //
            linkTo(methodOn(ToDoController.class).one(todo.getId())).withSelfRel(),
            linkTo(methodOn(ToDoController.class).all("", "", "", ""))
                                                            .withRel("todos"));
  }
}
