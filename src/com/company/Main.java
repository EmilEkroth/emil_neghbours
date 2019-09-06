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

import java.awt.*;

import static java.lang.System.nanoTime;


public class Main extends Application {

    //setup for standard var
    public static final int worldSize = 50;
    public static Plot[][] world = new Plot[worldSize][worldSize]; // world var

    public static final int dotSize = 10; // display var

    public static final float satisfaction = 0.6f; // needed satisfaction
    public static final int emptyProcent = 5; // the percentage of plots which is set as empty

    long previousTime = nanoTime(); //timer var
    final long interval = 450000000;

    public static void main(String[] args) {

        Random rng = new Random();
        for(int i = 0; i < worldSize; i++)  //initializing world
        {
            for (int j = 0; j < worldSize; j++)
            {
                int r = rng.nextInt(100);
                if (r <= emptyProcent) world[i][j] = new Plot(new Position(i, j), 0);
                else {
                    int t = rng.nextInt(2) +1;
                    world[i][j] = new Plot(new Position(i, j), t);
                }
            }
        }

	launch(args);
    }


    @Override
    public void start (Stage primaryStage) throws Exception{
        LogicSystem logS = new LogicSystem();
        Rendering renderer = new Rendering();
        primaryStage.setTitle("neighbours");

        Group root = new Group();
        javafx.scene.canvas.Canvas canvas = new Canvas(worldSize * (dotSize), worldSize * (dotSize));
        GraphicsContext gc =  canvas.getGraphicsContext2D();
        root.getChildren().addAll(canvas);

        AnimationTimer timer =  new AnimationTimer() { //timer
            public void handle(long currentNanoTime) {
                long elapsedNanos = currentNanoTime - previousTime;
                if(elapsedNanos > interval) {
                    logS.updateWorld(worldSize, satisfaction, world);
                    renderer.renderWorld(gc,worldSize,dotSize, world);
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