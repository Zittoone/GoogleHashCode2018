package fr.alexisc;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        // Parser
        Parser parser = new Parser("res/e_high_bonus.in");

        // Writer
        PrintWriter writer = new PrintWriter("res/e_high_bonus.out");

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
            listTask.add(new Task(Integer.parseInt(line[0]), Integer.parseInt(line [1]),
                    Integer.parseInt(line[2]),
                    Integer.parseInt(line[3]),
                    Integer.parseInt(line[4]),
                    Integer.parseInt(line[5]), index));
            index++;
        }

        for (Task t : listTask) {
            int i = closestVehiculeIndex(vehicules, t.x_start, t.y_start);
            int date = scoreTemps(vehicules[i].x, vehicules[i].y, t.x_start, t.y_start, vehicules[i].date);
            if (date <= t.final_date) {
                vehicules[i].tasks.add(t);
                vehicules[i].x = t.x_dest;
                vehicules[i].y = t.y_dest;
                vehicules[i].date = date + t.timeTaken();
            }

        }

        for(Vehicule vehicule : vehicules){
            StringBuilder sb = new StringBuilder("");
            sb.append(vehicule.tasks.size());
            for(Task task : vehicule.tasks){
                sb.append(" ").append(task.numTache);
            }
            writer.println(sb.toString());
        }

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
            score += vehicule.getDistanceTotal();
        }

        return score;
    }

    public static int closestVehiculeIndex(Vehicule[] vehicules, int x, int y){
        int index, score;
        index = -1;
        score = Integer.MAX_VALUE;
        for (int i = 0; i < vehicules.length; i++){
            int scoreTemp = scoreTemps(x, y, vehicules[i].x, vehicules[i].y, vehicules[i].date);
            if(scoreTemp < score){
                score = scoreTemp;
                index = i;
            }
        }
        return index;
    }

    public static int scoreTemps(int x1, int y1, int x2, int y2, int date){
        return date + distance(x1, y1, x2, y2);
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
