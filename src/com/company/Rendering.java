package com.company;


import javafx.scene.canvas.GraphicsContext;

public class Rendering {
    int worldSide;
    int worldSize;
    int dotSize;
    Plot[] world;

    Rendering (int newWorldSide,  int newWorldSize, int newDotSize, Plot[] newWorld)
    {
        worldSide = newWorldSide;
        worldSize = newWorldSize;
        dotSize = newDotSize;
        world = newWorld;
    }


    public void renderWorld (GraphicsContext g) // render world
    {
        g.clearRect(0, 0, worldSide * (dotSize), worldSide * (dotSize));
        for (int i = 0; i < worldSize; i++) {
            int x = dotSize * world[i].position.x;
            int y = dotSize * world[i].position.y;

            g.setFill(world[i].color);
            g.fillOval(x, y, dotSize, dotSize);
        }
    }
}
