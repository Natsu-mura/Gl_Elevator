/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jfxtest;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation.Status;
import static javafx.animation.Animation.Status.STOPPED;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author justi
 */
public class MotorDisplay extends Parent implements MotorInterface{
    
    private final Scene scene;
    private final Pane root;
    private Rectangle elevator;
    private final double width;
    private final double height;
    private final int nbFloors;
    private int current;
    private int next;
    private final InteriorScreen screen;
    private final Controller controlCommand=Controller.getInstance();
    
    public MotorDisplay(Rectangle2D primaryScreenBounds, Interior interior){
        
        /*** Interface de base ***/
        this.root = new Pane();
        this.scene = new Scene(root, 350, 700);
        this.width =scene.getWidth();
        this.height=scene.getHeight();
        this.nbFloors=9;
        this.current=controlCommand.getCurrent();
        this.next=0;
        this.screen=interior.getScreen();
        
        Stage window = new Stage();
        
        /*** set Stage boundaries to the lower right corner of the visible bounds of the main screen ***/
        window.setX(primaryScreenBounds.getMinX() + primaryScreenBounds.getWidth() - 1525);
        window.setY(primaryScreenBounds.getMinY() + 50);
        
        window.setTitle("Ascenseur - Déplacement");
        window.setResizable(false);
        
        window.setScene(this.scene);
        window.show();
        
    }
    
    public void init(){
        double h = this.height;
        double w = this.width/2;
        this.elevator = new Rectangle(w-25, h-51, 50, 49);
        elevator.setFill(Color.DARKGRAY);
        elevator.setStrokeType(StrokeType.OUTSIDE);
        elevator.setStrokeWidth(2);
        elevator.setStroke(Color.BLACK);
        
        Line cable = new Line(w, 0, w, h);
        cable.setStrokeWidth(2);
        cable.setStroke(Color.BLACK);
        
        root.getChildren().addAll(cable, elevator);
                
        for(int i=0; i<this.nbFloors;i++){
            Line one = new Line(0,h-(i*h/9)-1,w-27,h-(i*h/9)-1);
            Line two = new Line(w+27,h-(i*h/9)-1,this.width,h-(i*h/9)-1);
            one.setStroke(Color.BROWN);
            one.setStrokeWidth(2);
            two.setStroke(Color.BROWN);
            two.setStrokeWidth(2);
            root.getChildren().addAll(one, two);
        }
        
    }
    
    public Rectangle getElevator(){
        return this.elevator;
    }
    public double getWidth(){
        return this.width;
    }
    public double getHeight(){
        return this.height;
    }
    
    public void updateFloors(int current) throws InterruptedException{
        this.current=current;
        controlCommand.floorCrossedCallback(current);
    }

    @Override
    public void goUp() {
        
        this.next=controlCommand.getTarget();
        this.screen.setDirection(1);
        double h = this.height;
        if(this.current<this.next-1){
            TranslateTransition tt = new TranslateTransition(Duration.millis(2000), this.elevator);
            tt.setByY(-(h/9));
            tt.setCycleCount(1);
            tt.play();
            tt.setOnFinished(e -> {
                try {
                    updateFloors(this.current+1);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MotorDisplay.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        }
        else{
            this.stopNextFloor();
        }
    }

    @Override
    public void goDown() {
        
        this.next=controlCommand.getTarget();
        this.screen.setDirection(-1);
        double h = this.height;
        if(current>this.next+1){
            TranslateTransition tt = new TranslateTransition(Duration.millis(2000), this.elevator);
            tt.setByY(h/9);
            tt.setCycleCount(1);
            tt.play();
            tt.setOnFinished(e -> {
                try {
                    updateFloors(this.current-1);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MotorDisplay.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        }
        else{
            this.stopNextFloor();
        }
    }

    @Override
    public void stopNow() {
        
        double h = this.height;
        TranslateTransition tt = new TranslateTransition(Duration.millis(2000), this.elevator);
        tt.setByY(-(this.controlCommand.getState())*h/18);
        tt.setCycleCount(1);
        tt.play();
        tt.setOnFinished(e -> {
                this.controlCommand.changeLibre(true);
        });
    }

    @Override
    public void stopNextFloor() {
        
        double h = this.height;
        if(this.controlCommand.getState()==1){
            TranslateTransition tt = new TranslateTransition(Duration.millis(1000), this.elevator);
            tt.setByY(-h/18);
            tt.setCycleCount(1);
            TranslateTransition tt2 = new TranslateTransition(Duration.millis(2000), this.elevator);
            tt2.setByY(-h/18);
            tt2.setCycleCount(1);
            tt.play();
            tt.setOnFinished(e -> {
                tt2.play();
            });
            tt2.setOnFinished(e -> {
                try {
                    updateFloors(this.current+1);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MotorDisplay.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        }
        else if(this.controlCommand.getState()==-1){
            TranslateTransition tt = new TranslateTransition(Duration.millis(1000), this.elevator);
            tt.setByY(h/18);
            tt.setCycleCount(1);
            TranslateTransition tt2 = new TranslateTransition(Duration.millis(2000), this.elevator);
            tt2.setByY(h/18);
            tt2.setCycleCount(1);
            tt.play();
            tt.setOnFinished(e -> {
                tt2.play();
            });
            tt2.setOnFinished((ActionEvent e) -> {
                try {
                    updateFloors(this.current-1);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MotorDisplay.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        }
    }
    
}
