/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jfxtest;


import java.util.Observable;
import java.util.Observer;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

/**
 *
 * @author justi
 */
public class Interior extends Parent implements Observer{
    
    private Control control = Control.getInstance();
    private InteriorScreen screen;
    private InteriorKeyboard interiorKeyboard;
    
    public Interior(){
        
        VBox B = new VBox();
        B.setPrefSize(280,600);
        B.setStyle(" -fx-background-color: linear-gradient(to bottom right, #8b4513, #ffa54f);");
        B.setSpacing(25);
        B.setAlignment(Pos.CENTER);
        screen = new InteriorScreen(0);
        interiorKeyboard = new InteriorKeyboard();
        B.getChildren().add(screen);
        B.getChildren().add(interiorKeyboard);
        observe(control);
        
        
        
        this.getChildren().add(B);
    }
    
    public void observe(Observable o) {
        o.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
      int someVariable = ((Control) o).getDirection();
      switch(someVariable) {
        case -1:
           screen.down();
          break;
        case 1:
           screen.up();
          break;
        default:
           screen.stop(Control.getInstance().getCurrent());
      }
    }
    
}
