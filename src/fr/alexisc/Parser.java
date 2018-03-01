package fr.alexisc;

import java.io.*;
import java.util.Collection;
import java.util.function.Function;

/**
 * Created by Alexis Couvreur on 25/02/2018.
 */
public class Parser {

    private FileInputStream fileInputStream;
    private BufferedReader bufferedReader;

    public Parser(String fileName) throws FileNotFoundException {
        fileInputStream = new FileInputStream(fileName);
        bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
    }

    public String[] extractLine(String delimiter) {

        String line = null;
        try {
            line = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(line == null) return null;
        return line.split(delimiter);

    }

    /**
     * Useful if you want to apply a method on each lines
     * @param function that will process on each lines
     * @param collector where the result ends up
     * @param <T>
     */
    public <T> void applyToEachLines(Function<String, T> function, Collection<T> collector){
        String line;

        try {
            while ((line = bufferedReader.readLine()) != null){
                collector.add(function.apply(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void rewind(){
        try {
            fileInputStream.getChannel().position(0);
        } catch (IOException e) {
            System.out.println("The FileInputStream could not set the position 0 to the channel");
        }
        bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
    }

}
