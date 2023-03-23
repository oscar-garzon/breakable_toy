

package com.packt.todolistjavacollection.domain;

import java.util.Collection;
import java.util.HashMap;
import java.time.Duration;

import org.springframework.stereotype.Component;

@Component("metrics")
public class Metrics {
 
    //  Return the average time in minutes to finish tasks in general
    //  and also by priority (low, medium, high)
    public HashMap<String, String> updateAndGet(Collection<ToDo> todos){
        HashMap<String, String> metrics = new HashMap<>();
        
        Long avgTimeGeneral = 0L;
        Long avgTimeHigh = 0L;
        Long avgTimeMedium = 0L;
        Long avgTimeLow = 0L;

        int countLow = 0;         
        int countMedium = 0; 
        int countHigh = 0; 
        

        for (ToDo todo : todos){
            if(todo.getDone_date() == null){
                continue;
            }
            Long todo_duration = Duration.between(todo.getCreation_date(), todo.getDone_date()).toMinutes();
            String priority = todo.getPriority();
            avgTimeGeneral += todo_duration;

            if(priority == "high") {
                countHigh += 1;
                avgTimeHigh += todo_duration;
            }
            else if(priority == "medium") {
                countMedium += 1;
                avgTimeMedium += todo_duration;
            }
            else {
                countLow += 1;
                avgTimeLow += todo_duration;
            }
        }
        
        metrics.put("general", Long.toString(avgTimeGeneral / ((todos.size() == 0) ? 1 : todos.size())));
        metrics.put("high", Long.toString(avgTimeHigh / ((countHigh == 0) ? 1 : countHigh) ));
        metrics.put("medium", Long.toString(avgTimeMedium / ((countMedium == 0) ? 1 : countMedium)));
        metrics.put("low", Long.toString(avgTimeLow / ((countLow == 0) ? 1 : countLow)));

        return metrics;
    }
    
}
