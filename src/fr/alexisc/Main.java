package fr.alexisc;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

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

        for(int i = 0; i < F; i++){
            vehicules[i] = new Vehicule(0, 0, 0);
        }

        int index = 0;
        String[] line;

        while((line = parser.extractLine(" ")) != null){

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
}
