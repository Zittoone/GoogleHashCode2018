package fr.alexisc;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

public class Main {

    //toto
    public static void main(String[] args) throws FileNotFoundException {

        // Parser
        Parser parser = new Parser("res/big.in");

        // Writer
        PrintWriter writer = new PrintWriter("res/big.out");

        // Core
        String[] headers = parser.extractLine(" ");

        Collection<Integer> collector = new ArrayList<>();
        parser.applyToEachLines(line -> {
            return line.length();
        }, collector);

        // Print the solution
        writer.println(collector.toString());

        // Close the writer
        writer.close();
    }
}
