package fr.alexisc;

import java.util.ArrayList;
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
}
