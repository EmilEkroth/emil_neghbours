package com.company;

import java.util.ArrayList;
import java.util.Random;

public class LogicSystem{

    int worldSide;
    int worldSize;
    float satisfaction;
    Plot[] world;
    Plot[] emptyPlots;

    LogicSystem(int newWorldSide, int newWorldSize, float newSatisfaction, Plot[] newWorld, Plot[] newEmptyPlots)
    {
        worldSide = newWorldSide;
        worldSize = newWorldSize;
        satisfaction = newSatisfaction;
        world = newWorld;
        emptyPlots = newEmptyPlots;
    }

    public void updateWorld () // updates world;
    {
        int unsatisfied = 0;
        for(int i = 0; i < worldSize; i++)
        {
            if (world[i].type != 0)
            {
                if (!world[i].isSatisfied(satisfaction, worldSide, world, i)) { //if the plot is unsatisfied we find a random empty plot and change position of the plots
                    Plot emptyPlot = FindRandomEmpty();

                    world[i].SwitchPosition(emptyPlot, emptyPlots);
                    unsatisfied ++;
                }
            }
        }

        System.out.println("number of unsatisfied citizens: " + unsatisfied);
        if (unsatisfied == 0) {
            System.out.println("Everyone is satisfied!");
            System.exit(0);
        }
    }

    public Plot FindRandomEmpty () // this method can be replaced by for example a method that searches for the closest empty plot.
    {
        Random rng = new Random();
        return emptyPlots[rng.nextInt(emptyPlots.length)];
}
}
