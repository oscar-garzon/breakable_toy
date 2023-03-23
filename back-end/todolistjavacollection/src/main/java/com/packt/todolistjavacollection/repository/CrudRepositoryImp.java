package com.packt.todolistjavacollection.repository;

import com.packt.todolistjavacollection.domain.ToDo;
import java.util.ArrayList;
import java.util.Optional;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class CrudRepositoryImp implements CrudRepository<ToDo, Long> {

        private ArrayList<ToDo> repository;
        private AtomicLong id_generator;

        public CrudRepositoryImp(){
            repository = new ArrayList<ToDo>();    
            id_generator = new AtomicLong();        
        }

        // Returns the number of entities
        public long count(){
            return repository.size();
        }
        
        // Deletes an entity
        public void delete(ToDo entity){
            deleteById(entity.getId());
        }
    
        // Deletes all entities in the repository
        public void deleteAll(){
            repository.clear();
        }

        //Deletes the entity with the given id
        public void deleteById(Long id){
            repository.removeIf( todo -> (todo.getId() == id));
        }

        //Returns whether an entity with the given id exists.
        public boolean existsById(Long id){
            for(ToDo td : repository){
                if (td.getId() == (id)){
                    return true;
                }
            }
            return false;
        }

        // Returns all items of a given type
        public List<ToDo> findAll(){
            return new ArrayList<>(repository);
        }
    
            // Returns one item by ID
        public Optional<ToDo> findById(Long id){
            for(ToDo td: repository){
                if(td.getId() == id){
                    return Optional.of(new ToDo((td)));
                    //return Optional.of(td).clone();
                }
            }
            return Optional.ofNullable(null);
            
        }
        
        // Saves an entity
        public ToDo save(ToDo entity){
            Long entity_id = entity.getId();
                                     
            if (entity_id == null){ //ToDo is not present
                entity.setId(generateId());
                repository.add(entity);
                return new ToDo(entity);
            }
            else{
                for (ToDo todo : repository) {
                    if(todo.getId() == entity_id){
                        todo.setText(entity.getText());
                        todo.setPriority(entity.getPriority());
                        todo.setDue_date(entity.getDue_date());
                        todo.setDone(entity.isDone());
                        todo.setDone_date(entity.getDone_date());
                        return new ToDo(todo);
                    }
                }
            }
            return null;
        }
    
        // Saves multiple entites
        public boolean saveAll(Iterable<ToDo> entities){
            for(ToDo todo : entities) {
                if(save(todo) == null){
                    return false;
                }
            }
            return true;
        }

        // Auxiliar function that generates an Id
        private long generateId(){
            return id_generator.getAndIncrement();
        }

}
