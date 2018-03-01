package fr.alexisc;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        // Parser
        Parser parser = new Parser("res/small.in");

        // Writer
        PrintWriter writer = new PrintWriter("res/small.out");

        // Core
        String[] headers = parser.extractLine(" ");

        int R, C, F, N, B;
        long T;

        R = Integer.parseInt(headers[0]);
        C = Integer.parseInt(headers[1]);
        F = Integer.parseInt(headers[2]);
        N = Integer.parseInt(headers[3]);
        B = Integer.parseInt(headers[4]);
        T = Long.parseLong(headers[5]);

        Vehicule[] vehicules = new Vehicule[F];
        ArrayList<Task> listTask = new ArrayList<Task>();
        for(int i = 0; i < F; i++){
            vehicules[i] = new Vehicule(0, 0, 0);
        }

        int index = 0;
        String[] line;

        while((line = parser.extractLine(" ")) != null){
            //TODO : liste de taches :
        }

        for (Task t : listTask) {
            int i = closestVehiculeIndex(vehicules, t.x_start, t.y_start);
            int date = scoretemps(vehicules[i]);
            if (date < t.final_date) {
                vehicules[i].tasks.add(t);
                vehicules[i].x = t.x_dest;
                vehicules[i].y = t.y_dest;
                vehicules[i].date = date + t.timeTaken();
            }

        }


        // Print the solution
        writer.println("it works");

        // Close the writer
        writer.close();
    }
    public static int distance(int xs, int xd, int ys, int yd)
    {
        return Math.abs(xd-xs) + Math.abs(yd-ys);
    }

    public static int calculScore(Vehicule[] vehicules){

        int score = 0;

        for (Vehicule vehicule : vehicules){

        }

        return score;
    }

    public static int closestVehiculeIndex(Vehicule[] vehicules, int x, int y){

        return 0;
    }
    @Deprecated
    public static Collection <Task> sortTasksFromStartDate(Collection<Task> lTask)
    {
        ArrayList<Task> lt = new ArrayList<Task>(lTask);

        lt.sort(new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2)
            {

               // return  t1.start_date<t2.start_date;
            }
        });

        return lt;
    }
    public static Task getEarliestTask(Collection<Task> lTask)
    {
        Task ret= new Task(0, 0, 0, 0, 1111111111, 0, 0);
        for(Task t: lTask)
        {
            if (t.start_date< ret.start_date)
                ret = t;
        }
        return ret;
    }
}
