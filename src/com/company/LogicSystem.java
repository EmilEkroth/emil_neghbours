package com.company;

import java.util.ArrayList;
import java.util.Random;

public class LogicSystem{
    public void updateWorld (int worldSize, float satisfaction, Plot[][] world) // updates world;
    {
        int unsatisfied = 0;
        for(int i = 0; i < worldSize; i++)
        {
            for (int j = 0; j < worldSize; j++)
            {
                if (world[i][j].type != 0)
                {
                    if (!world[i][j].isSatisfied(satisfaction, world)) { //if the plot is unsatisfied we find a random empty plot and change position of the plots
                        Plot emptyPlot = FindRandomEmpty(worldSize, world);

                        world[i][j].SwitchPosition(emptyPlot);
                        unsatisfied ++;
                    }
                }
            }
        }

        System.out.println("number of unsatisfied citizens: " + unsatisfied);
        if (unsatisfied == 0) {
            System.out.println("Everyone is satisfied!");
        }
    }

    public Plot FindRandomEmpty (int worldSize, Plot[][] world) // this method can be replaced by for example a method that searches for the closest empty plot.
    {
        Random rng = new Random ();
        ArrayList<Plot> emptyPlots = new ArrayList<Plot>();
        for(int i = 0; i < worldSize; i++)
        {
            for (int j = 0; j < worldSize; j++)
            {
                if(world[i][j].type == 0)
                {
                    emptyPlots.add(world[i][j]);
                }
            }
        }
        return emptyPlots.get(rng.nextInt(emptyPlots.size()));
    }
}
