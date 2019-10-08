/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jfxtest;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author justi
 */
public class DrawArrow {
    
    public DrawArrow(boolean down, boolean stop, Canvas origin){
        GraphicsContext gc = origin.getGraphicsContext2D();
        gc.setLineWidth(7);
        gc.setStroke(Color.web("#ff4d00"));
        if(stop)
            gc.setStroke(Color.BLACK);
        gc.strokeLine(25, 25, 25, 75);
        if(down){
            gc.strokeLine(25, 80, 10, 65);
            gc.strokeLine(25, 80, 40, 65);
        }
        else{
            gc.strokeLine(25, 20, 10, 35);
            gc.strokeLine(25, 20, 40, 35);
        }
    } 
    
}
