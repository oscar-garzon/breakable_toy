package com.packt.todolistjavacollection;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import com.packt.todolistjavacollection.domain.Sort;
import com.packt.todolistjavacollection.domain.SpecificationImp;
import com.packt.todolistjavacollection.domain.ToDo;
import com.packt.todolistjavacollection.domain.ToDoRepositoryImp;

@SpringBootTest
public class ToDoRepositoryTest {
   
    @Autowired
    private ToDoRepositoryImp repository;

    LocalDateTime date = LocalDateTime.now();
    LocalDateTime date2 = date.plusHours(1L);
    LocalDateTime date1 = date.plusMinutes(23);
    LocalDateTime date3 = date.plusDays(1L);

    String high = "high";
    String medium = "medium";
    String low = "low";
    
    @Test
    void testGetMetrics(){
        repository.deleteAll();

        ToDo td_high = repository.save(new ToDo("hola", high, date1));
        ToDo td_high2 = repository.save(new ToDo("hola", high, date2));
        ToDo td_med = repository.save(new ToDo("hola", medium, date1));
        ToDo td_low = repository.save(new ToDo("hola", low, date3));
        ToDo td_low2 = repository.save(new ToDo("hola", low, date2));

        ToDo[] todos = new ToDo[] {td_high, td_high2, td_low, td_low2, td_med};    
        for(ToDo todo : todos){
            todo.setDone_date(LocalDateTime.of(2023, 3, 20, 14, 10));
            repository.save(todo);
        }

         
        Long avgTimePriorityLow = (Duration.between(td_low.getCreation_date(), td_low.getDone_date()).toMinutes() +
                                  Duration.between(td_low2.getCreation_date(), td_low2.getDone_date()).toMinutes()) / 2;
        Long avgTimePriorityMedium = Duration.between(td_med.getCreation_date(), td_med.getDone_date()).toMinutes();
        Long avgTimePriorityHigh = (Duration.between(td_high.getCreation_date(), td_high.getDone_date()).toMinutes() +
                                    Duration.between(td_high2.getCreation_date(), td_high2.getDone_date()).toMinutes()) / 2;
        Long avgTimePriorityGeneral = (Duration.between(td_low.getCreation_date(), td_low.getDone_date()).toMinutes() +
                                  Duration.between(td_low2.getCreation_date(), td_low2.getDone_date()).toMinutes() +
                                  Duration.between(td_med.getCreation_date(), td_med.getDone_date()).toMinutes() +
                                  Duration.between(td_high.getCreation_date(), td_high.getDone_date()).toMinutes() +
                                  Duration.between(td_high2.getCreation_date(), td_high2.getDone_date()).toMinutes()) / 5;

        HashMap<String, String> metrics = repository.getMetrics();

        assertThat(repository.count()).isEqualTo(5);
        assertThat(metrics.get("general")).isEqualTo(Long.toString(avgTimePriorityGeneral));
        assertThat(metrics.get("low")).isEqualTo(Long.toString(avgTimePriorityLow));
        assertThat(metrics.get("medium")).isEqualTo(Long.toString(avgTimePriorityMedium));
        assertThat(metrics.get("high")).isEqualTo(Long.toString(avgTimePriorityHigh));
    }

    @Test
    void testFindAllSort(){
        repository.deleteAll();

        String priority = "priority";
        String due_date = "due_date";
        String asc = "ASC";
        String desc = "DESC";

        ToDo td_high = repository.save(new ToDo("hola", high, date3));
        ToDo td_low1 = repository.save(new ToDo("hola 2 low", low, date2));
        ToDo td_med = repository.save(new ToDo("hola", medium, date1));
        ToDo td_low = repository.save(new ToDo("hola", low, date1));

        Sort sort = new Sort("", "", "", "");
        ArrayList<ToDo> sorted_elements = repository.findAll(sort);
        assertThat(sorted_elements).isNotEqualTo(null);
        assertThat(sorted_elements.get(0).getText()).isEqualTo("hola");
        assertThat(sorted_elements.get(1).getText()).isEqualTo("hola 2 low");
        assertThat(sorted_elements.get(2).getText()).isEqualTo("hola");
        assertThat(sorted_elements.get(3).getText()).isEqualTo("hola");

        sort = new Sort(priority, asc, due_date, asc);
        sorted_elements = repository.findAll(sort);
        assertThat(sorted_elements).isNotEqualTo(null);
        assertThat(sorted_elements.get(0).getDue_date()).isEqualTo(date1);
        assertThat(sorted_elements.get(1).getDue_date()).isEqualTo(date2);
        assertThat(sorted_elements.get(2).getPriority()).isEqualTo(medium);
        assertThat(sorted_elements.get(3).getPriority()).isEqualTo(high);

        sort = new Sort(priority, desc, due_date, asc);
        sorted_elements = repository.findAll(sort);
        assertThat(sorted_elements).isNotEqualTo(null);
        assertThat(sorted_elements.get(0).getPriority()).isEqualTo(high);
        assertThat(sorted_elements.get(1).getPriority()).isEqualTo(medium);
        assertThat(sorted_elements.get(2).getDue_date()).isEqualTo(date1);
        assertThat(sorted_elements.get(3).getDue_date()).isEqualTo(date2);

        sort = new Sort(priority, asc, due_date, desc);
        sorted_elements = repository.findAll(sort);
        assertThat(sorted_elements).isNotEqualTo(null);
        assertThat(sorted_elements.get(0).getDue_date()).isEqualTo(date2);
        assertThat(sorted_elements.get(1).getDue_date()).isEqualTo(date1);
        assertThat(sorted_elements.get(2).getPriority()).isEqualTo(medium);
        assertThat(sorted_elements.get(3).getPriority()).isEqualTo(high);

        sort = new Sort(priority, desc, due_date, desc);
        sorted_elements = repository.findAll(sort);
        assertThat(sorted_elements).isNotEqualTo(null);
        assertThat(sorted_elements.get(0).getPriority()).isEqualTo(high);
        assertThat(sorted_elements.get(1).getPriority()).isEqualTo(medium);
        assertThat(sorted_elements.get(2).getDue_date()).isEqualTo(date2);
        assertThat(sorted_elements.get(3).getDue_date()).isEqualTo(date1);

        sort = new Sort(due_date, asc, priority, asc);
        sorted_elements = repository.findAll(sort);
        assertThat(sorted_elements).isNotEqualTo(null);
        assertThat(sorted_elements.get(0).getPriority()).isEqualTo(low);
        assertThat(sorted_elements.get(1).getPriority()).isEqualTo(medium);
        assertThat(sorted_elements.get(2).getDue_date()).isEqualTo(date2);
        assertThat(sorted_elements.get(3).getDue_date()).isEqualTo(date3);

        sort = new Sort(due_date, desc, priority, asc);
        sorted_elements = repository.findAll(sort);
        assertThat(sorted_elements).isNotEqualTo(null);
        assertThat(sorted_elements.get(0).getDue_date()).isEqualTo(date3);
        assertThat(sorted_elements.get(1).getDue_date()).isEqualTo(date2);
        assertThat(sorted_elements.get(2).getPriority()).isEqualTo(low);
        assertThat(sorted_elements.get(3).getPriority()).isEqualTo(medium);

        sort = new Sort(due_date, asc, priority, desc);
        sorted_elements = repository.findAll(sort);
        assertThat(sorted_elements).isNotEqualTo(null);
        assertThat(sorted_elements.get(0).getPriority()).isEqualTo(medium);
        assertThat(sorted_elements.get(1).getPriority()).isEqualTo(low);
        assertThat(sorted_elements.get(2).getDue_date()).isEqualTo(date2);
        assertThat(sorted_elements.get(3).getDue_date()).isEqualTo(date3);

        sort = new Sort(due_date, desc, priority, desc);
        sorted_elements = repository.findAll(sort);
        assertThat(sorted_elements).isNotEqualTo(null);
        assertThat(sorted_elements.get(0).getDue_date()).isEqualTo(date3);
        assertThat(sorted_elements.get(1).getDue_date()).isEqualTo(date2);
        assertThat(sorted_elements.get(2).getPriority()).isEqualTo(medium);
        assertThat(sorted_elements.get(3).getPriority()).isEqualTo(low);
    }

    @Test
    public void testFindAllSpec(){
        repository.deleteAll();

        repository.save(new ToDo("hola", high, date3));
        repository.save(new ToDo("hola 2 low", low, date2));
        repository.save(new ToDo("hola", medium, date1));
        ToDo td_low = repository.save(new ToDo("hola", low, date1));

        td_low.setDone(true);
        repository.save(td_low);

        ArrayList<ToDo> filter_elements = (ArrayList<ToDo>) repository.filterBy(new SpecificationImp("2 l", "", ""));
        assertThat(filter_elements.get(0).getText()).as("Filter by text").isEqualTo("hola 2 low");

        filter_elements = (ArrayList<ToDo>) repository.filterBy(new SpecificationImp("", high, ""));
        assertThat(filter_elements.get(0).getPriority()).as("Filter by priority").isEqualTo(high);

        filter_elements = (ArrayList<ToDo>) repository.filterBy(new SpecificationImp("", "", "true"));
        assertThat(filter_elements.size()).as("Filter by done").isEqualTo(1);
        assertThat(filter_elements.get(0).getPriority()).isEqualTo(low);
        assertThat(filter_elements.get(0).getText()).isEqualTo("hola");

        filter_elements = (ArrayList<ToDo>) repository.filterBy(new SpecificationImp("hola", "low", "true"));
        assertThat(filter_elements.get(0).getText()).as("Filter by text, priiority and done").isEqualTo("hola");
        assertThat(filter_elements.get(0).isDone()).isTrue();
        assertThat(filter_elements.get(0).getPriority()).isEqualTo(low);

      
    }

}