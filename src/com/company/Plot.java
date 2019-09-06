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
        if (type == 1) color = Color.BLUE; // needs to bee redone if more than 2 types (except empty) exists
        else if (type == 2) color = Color.RED;
        else color = Color.WHITE;
    }

    public boolean isSatisfied (float satisfaction, Plot[][] world) // check if plot is satisfied;
    {
        float avg = 0;
        int n = 0;
        for (int i = position.x - 1; i < position.x+2; i++)
        {
            for (int j = position.y -1; j < position.y+2; j++)
            {
                if (i > -1 && j > -1 //check that i or j isn't outside of world or at object pos
                        && i < world.length && j < world.length
                        && (i != this.position.x || j != this.position.y)) {
                    // System.out.println("at ("+position.x + ", "+ position.y +") "+ " x: " + i + " y: " + j);

                    if (world[i][j].type == this.type)
                    {
                        avg += 1;
                    }
                    if (world[i][j].type != 0) //they don't care about empty plots
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

    public void SwitchPosition (Plot otherPlot) //Easy way to switch plots
    {
        int t = type;
        type=otherPlot.type;
        otherPlot.type = t;

        ColorCorrect();
        otherPlot.ColorCorrect();
    }
}