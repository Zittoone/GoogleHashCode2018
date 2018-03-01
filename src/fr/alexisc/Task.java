package fr.alexisc;

public class Task {
    public int x_start, y_start, x_dest, y_dest, start_date,final_date;
    public int numTache;

    public int vehiculeIndex;

    Task(int xs, int ys,int  xd, int yd, int sd, int infofd, int nt )
    {
        x_start=xs;
        x_dest=xd;
        y_start=ys;
        y_dest=yd;
        start_date=sd;

        final_date= Math.min(0, infofd - Math.abs(xd-xs) - Math.abs(yd-ys));
        numTache=nt;
    }


    public int timeTaken()
    {
        return Main.distance(x_start, x_dest, y_start, y_dest);
    }

}
