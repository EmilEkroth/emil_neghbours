package com.company;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import static java.lang.System.nanoTime;

public class Rendering {
    public void renderWorld (GraphicsContext g, int worldSide,  int worldSize, int dotSize, Plot[] world) // render world
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
