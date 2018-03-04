package fr.alexisc;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Vehicule {


    public int x, y, date;
    public List<Task> tasks;

    public Vehicule(int x, int y, int date){
        this.x = x;
        this.y = y;
        this.date = date;
        tasks = new ArrayList<>();
    }


    public int getDistanceTotal() {
        int distanceTotal = 0;
        for(Task task : tasks){
            distanceTotal += task.timeTaken();
        }
        return distanceTotal;
    }

    public int isValidAddingTask(Task t){

        LinkedList<Task> taskTemp = new LinkedList<>(tasks);

        for(int i = 0; i < taskTemp.size(); i++){

            taskTemp.add(i, t);

            if(isValidTaskList(tasks)){
                return i;
            } else {
                taskTemp.remove(i);
            }
        }

        return -1;
    }


    public boolean isValidTaskList(List<Task> tasks){
        // override variables
        int x, y, date;
        x = y = date = 0;

        for(Task task : tasks){

            int distance = Main.distance(x, y, task.x_start, task.y_start);

            if(! (date + distance < task.final_date))
                return false;

            x = task.x_dest;
            y = task.y_dest;
            date += distance + task.timeTaken();
        }

        return true;
    }
}
