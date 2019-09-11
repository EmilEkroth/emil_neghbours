package com.company;

import javafx.scene.paint.Color;

class Plot
{
    public Position position; // position of plot in world;
    public int type;    // the type of plot, 0 is empty plot, everything else is different types

    public Color color;

    public Plot (Position newPosition, int newType){
        position = newPosition;
        type = newType;
        ColorCorrect();
    }

    public void ColorCorrect ()
    {
        Color[] colors = {Color.WHITE, Color.BLUE, Color.RED, Color.GREEN, Color.GOLD, Color.PINK, Color.VIOLET, Color.GRAY, Color.BROWN};

        color = colors[type];
    }

    public boolean isSatisfied (float satisfaction, int worldSide, Plot[] world, int plotIndex) // check if plot is satisfied;
    {
        float avg = 0;
        int n = 0;
        for (int i = position.x - 1; i < position.x+2; i++)
        {
            for (int j = position.y -1; j < position.y+2; j++)
            {
                if (i > -1 && j > -1 //check that i or j isn't outside of world or at object pos
                        && i < worldSide && j < worldSide
                        && (i != this.position.x || j != this.position.y)) {
                    // System.out.println("at ("+position.x + ", "+ position.y +") "+ " x: " + i + " y: " + j);

                    int m = plotIndex + worldSide * (i -  position.x) + (j-position.y);

                    if (world[m].type == this.type)
                    {
                        avg += 1;
                    }
                    if (world[m].type != 0) //they don't care about empty plots
                    {
                        n++;
                    }
                }
            }
        }
        if (n == 0) //In some cases one plot is encircled with empty plots witch causes the n value to be set at 0. This if-statement prevents divide by zero
            return true;
        else {
            avg /= n;
            //System.out.println(avg);
            return avg >= satisfaction;
        }
    }

    public void SwitchPosition (Plot otherPlot, Plot[] emptyPlots) //Easy way to switch plots
    {
        int t = type;
        type=otherPlot.type;
        otherPlot.type = t;

        for(int i = 0; i < emptyPlots.length; i++)
        {
            if (emptyPlots[i] == otherPlot) emptyPlots[i] = this;
        }

        ColorCorrect();
        otherPlot.ColorCorrect();
    }
}