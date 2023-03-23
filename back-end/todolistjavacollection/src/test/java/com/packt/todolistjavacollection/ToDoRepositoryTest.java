package com.packt.todolistjavacollection;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.packt.todolistjavacollection.domain.ToDo;
import com.packt.todolistjavacollection.domain.ToDoRepositoryImp;

@SpringBootTest
public class ToDoRepositoryTest {
   
    @Autowired
    private ToDoRepositoryImp repository;
   
    @Test
    void testGetMetrics(){
        repository.deleteAll();

        String high = "high";
        String medium = "medium";
        String low = "low";

        ToDo td_high = repository.save(new ToDo("hola", high, "manana"));
        ToDo td_high2 = repository.save(new ToDo("hola", high, "manana"));
        ToDo td_med = repository.save(new ToDo("hola", medium, "manana"));
        ToDo td_low = repository.save(new ToDo("hola", low, "manana"));
        ToDo td_low2 = repository.save(new ToDo("hola", low, "manana"));

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

}