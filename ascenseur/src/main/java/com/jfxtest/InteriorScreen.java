/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jfxtest;

import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author justi
 */
public class InteriorScreen extends Parent{
    
    Rectangle base;
    Text display;
    Canvas arrowLeft;
    Canvas arrowRight;
    int currentFloor;
    
    public InteriorScreen(int currentFloor){
        
        this.currentFloor=currentFloor;
        
        /*** Ecran ***/
        base = new Rectangle(250,100,Color.BLACK);
        base.setFill( //on remplie notre rectangle avec un dégradé
            new LinearGradient(0f, 0f, 0f, 1f, true, CycleMethod.NO_CYCLE,
                new Stop[] {
                    new Stop(1, Color.web("#000000")),
                    new Stop(0, Color.web("#555555"))
                }
            )
        );
        base.setArcHeight(45);
        base.setArcWidth(45);
        
        
        /*** Affichage ***/
        
        display = new Text("FLOOR\n" + this.currentFloor);
        display.setTextAlignment(TextAlignment.CENTER);
        display.setStyle(
                "-fx-font: 25 centurygothic;"+
                "-fx-font-weight: bold;"
        );
        display.setFill(Color.web("#ff4d00"));
        
        /*** Fleches ***/
        
        arrowLeft = new Canvas(50,100);
        arrowRight = new Canvas(50,100);
        new DrawArrow(true, true, arrowLeft);
        new DrawArrow(false, true, arrowRight);
        
        /*** TilePane ***/
        
        TilePane screen = new TilePane();
        screen.setPrefSize(250, 100);
        screen.getChildren().addAll(
                arrowLeft,
                display,
                arrowRight
        );
        
        
        /*** StackPane ***/
        
        StackPane layout = new StackPane();
        layout.getChildren().addAll(
                base,
                screen
        );
        layout.setPadding(new Insets(15,15,15,15));
        
        this.getChildren().add(layout);
        
       
    }
    
    public void up(){
        new DrawArrow(false, false, arrowRight);
    }
    
    public void down(){
        new DrawArrow(true, false, arrowLeft);
    }
    
    public void stop(int newFloor){
        new DrawArrow(true, true, arrowLeft);
        new DrawArrow(false, true, arrowRight);
        display.setText("FLOOR\n" + newFloor);
    }
    
}
