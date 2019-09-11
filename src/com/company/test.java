package com.company;

public class test {
    Plot [] testWorld = {
            new Plot(new Position(0,0), 0),
            new Plot(new Position(0,1), 0),
            new Plot(new Position(0,2), 0),
            new Plot(new Position(1,0), 1),
            new Plot(new Position(1,1), 1),
            new Plot(new Position(1,2), 1),
            new Plot(new Position(2,0), 2),
            new Plot(new Position(2,1), 2),
            new Plot(new Position(2,2), 2),
    };

    Plot [] emptyPlots = {
            testWorld[0],
            testWorld[1],
            testWorld[2]
    };

    public void main ()
    {
        System.out.println(testWorld[3].isSatisfied(0.7f, 3, testWorld, 3) == false);
        System.exit(1);
    }
}
