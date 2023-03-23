package com.packt.todolistjavacollection.repository;

//import java.util.List;
import java.util.Optional;
import java.util.List;

public interface CrudRepository<T, ID> {
    // Returns the number of entities
    public long count();

    
    // Deletes all entities in the repository
    public void deleteAll();
    
    // Deletes an entity
    public void delete(T entity);
    
    //Deletes the entity with the given id
    public void deleteById(ID id);
    
    //Returns whether an entity with the given id exists
    public boolean existsById(ID id);
    
    // Returns all items of a given type
    public List<T> findAll();

        // Returns one item by ID
    public Optional<T> findById(ID Id);

    // Saves an entity
    public <S extends T> S save(S entity);

    // Saves multiple entites
    public boolean saveAll(Iterable<T> entities);


}
