package com.company;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.math.*;

import java.awt.*;

import static java.lang.System.nanoTime;


public class Main extends Application {

    //setup for standard var
    public static final int worldSide = 300;
    public static final int worldSize = (int)Math.pow(worldSide,2);
    public static final int ammountOfTypes = 2;

    public static final int dotSize = 2; // display var

    public static final float satisfaction = 0.7f; // needed satisfaction
    public static final float emptyProcent = 0.1f; // the percentage of plots which is set as empty

    long previousTime = nanoTime(); //timer var
    final long interval = 450000000;

    public static void main(String[] args) {
	launch(args);
    }

    Plot [] InitializeWorld ()
    {
        Plot[] world = new Plot[worldSize]; // world var (3)
        System.out.println("side: " + worldSide + " size: " + worldSize + " world.length: " + world.length);
        Random rng = new Random();
        int n = 0;
        int t = 1;
        for(int i = 0; i < worldSide; i++)  //initializing world
        {
            for (int j = 0; j < worldSide; j++)
            {
                int e = (int)(worldSize*emptyProcent);
                if(n < e)  world[n] = new Plot(new Position(i,j), 0);
                else {
                    if(n>=((float)((worldSize-e)/ammountOfTypes)*t) + e) t++;

                    world[n]= new Plot(new Position(i,j), t);
                }
                n++;
            }
        }
        return world;
    }

    Plot [] ShuffleWorld (Plot[] world, Plot[] emptyPlots)
    {
        Random rng = new Random();
        for (int i = 0; i < world.length; i++)
        {
            int n = rng.nextInt(worldSize);
            world[i].SwitchPosition(world[n], emptyPlots);

        }
        return world;
    }

    Plot[] FindEmpty (Plot[] world)
    {
        ArrayList<Plot> empty = new ArrayList<Plot>();
        for(int i = 0; i < worldSize; i++)
        {
            if (world[i].type == 0) empty.add(world[i]);
        }

        return empty.toArray(new Plot[empty.size()]);
    }

    @Override
    public void start (Stage primaryStage) throws Exception{

        Plot[] world = InitializeWorld();
        Plot[] emptyPlots = FindEmpty(world);
        world = ShuffleWorld(world, emptyPlots);

        LogicSystem logS = new LogicSystem(worldSide, worldSize, satisfaction, world, emptyPlots);
        Rendering renderer = new Rendering(worldSide, worldSize ,dotSize, world);
        primaryStage.setTitle("neighbours");

        Group root = new Group();
        javafx.scene.canvas.Canvas canvas = new Canvas(worldSide * (dotSize), worldSide * (dotSize));
        GraphicsContext gc =  canvas.getGraphicsContext2D();
        root.getChildren().addAll(canvas);

        AnimationTimer timer =  new AnimationTimer() { //timer
            public void handle(long currentNanoTime) {
                long elapsedNanos = currentNanoTime - previousTime;
                if(elapsedNanos > interval) {
                    logS.updateWorld();
                    renderer.renderWorld(gc);
                    previousTime = currentNanoTime;
                }
            }
        };

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

        timer.start();
    }
}