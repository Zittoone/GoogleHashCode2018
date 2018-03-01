package fr.alexisc;

import java.util.Collection;

public class ProblemSolver {




    public String generateLine(int nbRides, Collection<Integer> lRides)
    {
        String ret=Integer.toString(nbRides);
        if(nbRides != lRides.size())
        {
            System.err.println("nbRides is not the same as the number of rides given");
        }
        for(Integer i:lRides)
        {
            ret+= " "+ i.toString();
        }
        return ret;
    }
}
