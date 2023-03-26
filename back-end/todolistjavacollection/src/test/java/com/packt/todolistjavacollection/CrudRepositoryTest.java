package com.packt.todolistjavacollection;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import com.packt.todolistjavacollection.domain.ToDo;
import com.packt.todolistjavacollection.repository.CrudRepositoryImp;

@SpringBootTest
public class CrudRepositoryTest {
   
    @Autowired
    private CrudRepositoryImp repository;

    LocalDateTime date1 = LocalDateTime.of(2023, 03, 24, 18, 23);
	LocalDateTime date2 = LocalDateTime.of(2023, 03, 25, 10, 23);
	LocalDateTime date3 = LocalDateTime.of(2023, 03, 24, 19, 23);
   
    @Test
    void testExistsById(){
        ToDo td = repository.save(new ToDo("hey hey", "medium", date1));
        Long id = td.getId();
        assertThat(repository.existsById(id)).isTrue();
        repository.delete(td);
        assertThat(repository.existsById(id)).isFalse();
    }

    @Test
    void testSave(){
        ToDo td = new ToDo("Have Fun", "high", date2);
        ToDo repo_td = repository.save(td);
        td.setId(repo_td.getId());
        assertThat(repository.existsById(td.getId())).isTrue();
    }

    @Test
    // Verify that findById returns the correct ToDo
    void testFindById(){
        ToDo td1 = repository.save(new ToDo("hey hey", 
                                       "medium",
                                       date3));
        Long id1 = td1.getId();
        ToDo td2 = repository.save(new ToDo("ay ay", 
                                        "high", 
                                        date1));
        ToDo finded_todo = repository.findById(id1).get();                                      
        assertThat(finded_todo.getId()).isEqualTo(id1);
        assertThat(finded_todo.getText()).isEqualTo(td1.getText());
        assertThat(finded_todo.getPriority()).isEqualTo(td1.getPriority());
        assertThat(finded_todo.getDue_date()).isEqualTo(td1.getDue_date());
        assertThat(finded_todo.getText()).isNotEqualTo(td2.getText());

        // Test that verifies that if an object returned by findById has
        // a property value changed then the corresponding object in the 
        // repository is not modified (Without doing save.repository)
        finded_todo.setText("NOT hey hey");
        String todo_text_after_modificatiion = repository.findById(id1).get().getText();
        assertThat(finded_todo.getText()).isNotEqualTo(todo_text_after_modificatiion);
        assertThat("hey hey").isEqualTo(todo_text_after_modificatiion);
    }
   
    @Test
    //Ver que generateId genera id's distinto cada vez que lo mando llamar
    void testGenerateId(){
        Long id1 = repository.save(new ToDo("hey hey", "medium", date1)).getId();
        Long id2 = repository.save(new ToDo("hu hu urra", "high", date2)).getId();
        assertThat(id1).isNotEqualTo(id2);
    }

    @Test
    void testDeleteById(){
        Long id1 = repository.save(new ToDo("hey hey", "medium", date3)).getId();
        assertThat(repository.findById(id1).isPresent()).isTrue();
        repository.deleteById(id1);
        assertThat(repository.findById(id1).isPresent()).isFalse();
    }


}
