/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jfxtest;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;

/**
 *
 * @author justi
 */
public class InteriorKey extends Parent implements Observer{
    
    Button key;
    Circle border;

    private final String display;
    private final int floor;
    private final int posX;
    private final int posY;
    private CommandControl control = CommandControl.getInstance();

    public InteriorKey(String display, int floor, int posX, int posY) {
        this.display=display;
        this.floor = floor;
        this.posX = posX;
        this.posY = posY;
        
        border = new Circle(26);
        border.setFill(null);
        border.setStroke(Color.web("#000000"));
        border.setStrokeType(StrokeType.OUTSIDE);
        border.setStrokeWidth(3);
       
        border.setTranslateX(25);
        border.setTranslateY(25);
        this.getChildren().add(border);
        
        key = new Button(this.display);
        
        key.setShape(new Circle(25));
        key.setMinSize(50,50);
        key.setMaxSize(50,50);

        key.setStyle(
                "-fx-font: 16 centurygothic;" +
                "-fx-font-weight : bold;" +
                "-fx-base: #d1d1d1;"+
                "-fx-focus-color: transparent;"+
                "-fx-faint-focus-color: transparent;"
        );

        this.getChildren().add(key);
        
        
        this.setTranslateX(posX);
        this.setTranslateY(posY);
        
        
        key.setOnMouseClicked((MouseEvent me) -> {
            if(this.floor==-1){
                emergency();
            }
            else
                appuyer();
        });
        observe(CommandControl.getInstance());
    }
    
    public void appuyer(){
        if (!control.getFloors().contains(this.floor)){
            control.addFloor(this.floor);
            control.updateDir();
            return;
        }
        control.removeFloor(this.floor);
        control.updateDir();
    }
    
    public void emergency(){
        control.emergency(-1);
        control.updateDir();
    }
    
        
    public void observe(Observable o) {
        o.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        ArrayList<Integer> someVariable = ((CommandControl) o).getFloors();
        if(someVariable.contains(floor) && floor!=-1) 
            border.setStroke(Color.web("#f1bc31"));
        else if(someVariable.contains(floor) && floor==-1) 
            border.setStroke(Color.web("#ef370e"));
        else
            border.setStroke(Color.web("#000000"));
    }
    
}
