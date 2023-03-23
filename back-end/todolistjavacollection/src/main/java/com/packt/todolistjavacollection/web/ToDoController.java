package com.packt.todolistjavacollection.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.util.stream.Collectors;
import java.util.List;
import java.util.HashMap;

import com.packt.todolistjavacollection.domain.ToDo;
import com.packt.todolistjavacollection.exceptions.ToDoNotFoundException;
import com.packt.todolistjavacollection.domain.ToDoRepositoryImp;

@CrossOrigin()
@RestController
public class ToDoController {

    @Autowired
    private ToDoRepositoryImp repository;
    @Autowired
    private ToDoModelAssembler assembler;

    @DeleteMapping("/todos/{id}")
    public void deleteToDo(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @GetMapping("/todos")
    public CollectionModel<EntityModel<ToDo>> all(){
        List<EntityModel<ToDo>> todos = repository.findAll().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());
        return CollectionModel.of(todos, linkTo(methodOn(ToDoController.class).all()).withSelfRel());
    }   

    @GetMapping("/todos/{id}")
    public EntityModel<ToDo> one(@PathVariable Long id) {
        ToDo todo = repository.findById(id).orElseThrow(ToDoNotFoundException::new);
        return assembler.toModel(todo);
    }

    @GetMapping("/todos/metrics")
    public HashMap<String, String> metrics(){
        return repository.getMetrics();
    }

    @PostMapping("/todos")
    public ToDo newToDo(@RequestBody ToDo todo)
    {
        return repository.save(todo);
    }


    
    @PostMapping("/todos/{id}/done")
    public ToDo done(@RequestBody(required=false) ToDo todo, @PathVariable Long id) {
        return repository.findById(id)
        .map(done_todo -> {
            done_todo.setDoneAndDoneDate();
            return repository.save(done_todo);  
        })
        .orElseThrow(ToDoNotFoundException::new);
    }
    
    @PostMapping("/todos/{id}/undone")
    public ToDo undone(@PathVariable Long id) {
        return repository.findById(id)
        .map(todo -> {
            todo.setDone(false);
            return repository.save(todo);  
        })
        .orElseThrow(ToDoNotFoundException::new);
    }
    
    @PutMapping("/todos/{id}")
    public ToDo replaceToDo(@RequestBody ToDo newTodo, @PathVariable Long id){
        return repository.findById(id)
                        .map(todo -> {
                            todo.setText(newTodo.getText());
                            todo.setPriority(newTodo.getPriority());
                            todo.setDue_date(newTodo.getDue_date());
                            return repository.save(todo);
                        })
                        .orElseGet(() -> {
                                newTodo.setId(id);
                                return repository.save(newTodo);
                        });
    }
}
