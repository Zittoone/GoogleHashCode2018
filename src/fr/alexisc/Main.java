package fr.alexisc;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static int B;
    public static long T;

    public static void generateFile (String fname) throws Exception
    {
        Parser parser = new Parser(fname+".in");

        // Writer
        PrintWriter writer = new PrintWriter(fname+".out");

        // Core
        String[] headers = parser.extractLine(" ");

        int R, C, F, N;

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
        ArrayList<Task> listTask2 = new ArrayList<Task>(listTask);

        while(!listTask.isEmpty())
        {
            // Task t= Main.getEarliestTask(listTask);
            // int i = closestVehiculeIndex(vehicules, t.x_start, t.y_start);
            // Task t = Main.getLongestTask(listTask);
            // int i = closestVehiculeIndex(vehicules, t.x_start, t.y_start);
            Task t = null;
            int i = -1;
            int score = Integer.MAX_VALUE;
            for(Task task : listTask){
                for(int j = 0; j < vehicules.length; j++){
                    int scoreTemp = scoreTemps(task.x_start, task.y_start, vehicules[j].x, vehicules[j].y, vehicules[j].date);
                    if((scoreTemp < score) || ((scoreTemp == score) && (score == task.start_date))){
                        score = scoreTemp;
                        i = j;
                        t = task;
                    }

                }
            }
            int date = scoreTemps(vehicules[i].x, vehicules[i].y, t.x_start, t.y_start, vehicules[i].date);
            if (date <= t.final_date) {
                vehicules[i].tasks.add(t);
                vehicules[i].x = t.x_dest;
                vehicules[i].y = t.y_dest;
                vehicules[i].date = date + t.timeTaken();
            }
            listTask.remove(t);
        }
        /*for (Task t : listTask) {
            int i = closestVehiculeIndex(vehicules, t.x_start, t.y_start);
            int date = scoreTemps(vehicules[i].x, vehicules[i].y, t.x_start, t.y_start, vehicules[i].date);
            if (date <= t.final_date) {
                vehicules[i].tasks.add(t);
                vehicules[i].x = t.x_dest;
                vehicules[i].y = t.y_dest;
                vehicules[i].date = date + t.timeTaken();
            }

        }*/

        boolean[] taskUnused = new boolean[N];
        for(int i = 0; i < N; i++){
            taskUnused[i] = true;
        }

        for(Vehicule vehicule : vehicules){
            for(Task task : vehicule.tasks){
                taskUnused[task.numTache] = false;
            }
        }

        for(int i = 0; i < N; i++){
            if(taskUnused[i]){
                loopnulle: for(Vehicule vehicule : vehicules){
                    int indexTaskAdded = vehicule.isValidAddingTask(listTask2.get(i));
                    if(indexTaskAdded == -1){
                        
                    } else {
                        vehicule.tasks.add(indexTaskAdded, listTask2.get(i));
                        break loopnulle;
                    }
                }
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
    public static void main(String[] args) throws FileNotFoundException {

        String file1= "res/a_example";
        String file2= "res/b_should_be_easy";
        String file3= "res/c_no_hurry";
        String file4= "res/d_metropolis";
        String file5= "res/e_high_bonus";

        try {
            System.out.println("File 1 processing");
            generateFile(file1);
            System.out.println("File 2 processing");
        generateFile(file2);
            System.out.println("File 3 processing");
        generateFile(file3);
            System.out.println("File 4 processing");
        generateFile(file4);
            System.out.println("File 5 processing");
        generateFile(file5);
        } catch (Exception e) {
            e.printStackTrace();
        }

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
        return date + distance(x1, x2, y1, y2);
    }

    public static Task getEarliestTask(Collection<Task> lTask)
    {
        Task ret= new Task(0, 0, 0, 0, 1111111111, 0, 0);
        for(Task t: lTask)
        {
            if (t.start_date+t.timeTaken()< ret.start_date +ret.timeTaken())
                ret = t;
        }
        return ret;
    }
    public static Task getLongestTask(Collection<Task> lTask)
    {
        int time = -1;
        Task ret = null;
        for(Task t : lTask)
        {
            if(t.timeTaken() > time)
                ret=t;
        }
        return ret;
    }
    public static Task getShortestTask(Collection<Task> lTask)
    {
        int time = Integer.MAX_VALUE;
        Task ret = null;
        for(Task t : lTask)
        {
            if(t.timeTaken() < time)
                ret=t;
        }
        return ret;
    }

    public static int scoreListTask(List<Task> tasks){
        int x, y, score;
        x = y = score = 0;

        for(Task task : tasks){
            int distance = distance(x, task.x_start, y, task.y_start);
            if(task.start_date >= distance){
                x = task.x_dest;
                y = task.y_dest;
                score += B + task.timeTaken();
            } else {
                x = task.x_dest;
                y = task.y_dest;
                score += task.timeTaken();
            }
        }
        return score;
    }
}
