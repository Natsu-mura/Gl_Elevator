/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jfxtest;


import java.util.Observable;
import java.util.Observer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author justi
 */
public class Interior extends Parent implements Observer{
    
    private CommandControl control = CommandControl.getInstance();
    private InteriorScreen screen;
    private InteriorKeyboard interiorKeyboard;
    private final Scene scene;
    
    public Interior(Rectangle2D primaryScreenBounds){
        
        /*** Interface de base ***/
        final VBox root = new VBox();
        this.scene = new Scene(root, 350, 600);
        this.scene.getStylesheets().add("C:\\Users\\justi\\Documents\\ascenseur\\styleSheet.css");
        root.setPadding(new Insets(0, 0, 0, 0));
        root.setStyle(" -fx-background-color: linear-gradient(to bottom right, #8b4513, #ffa54f);");
        root.setSpacing(25);
        root.setAlignment(Pos.CENTER);
        
        /*** Création du clavier et de l'écran d'affichage ***/
        screen = new InteriorScreen(0);
        interiorKeyboard = new InteriorKeyboard();
        root.getChildren().add(screen);
        root.getChildren().add(interiorKeyboard);
        
        Stage window = new Stage();
        
        /*** set Stage boundaries to the lower right corner of the visible bounds of the main screen ***/
        window.setX(primaryScreenBounds.getMinX() + primaryScreenBounds.getWidth() - 400);
       // window.setY(primaryScreenBounds.getMinY() + primaryScreenBounds.getHeight() - 700);
        
        window.setTitle("Ascenseur - vue intérieure");
        window.setResizable(false);
        
        window.setScene(this.scene);
        window.show();
        
        observe(control);
    }
    
    public void observe(Observable o) {
        o.addObserver(this);
    }


    @Override
    public void update(Observable o, Object arg) {
      int someVariable = ((CommandControl) o).getDirection();
      switch(someVariable) {
        case -1:
           screen.down();
          break;
        case 1:
           screen.up();
          break;
        default:
           screen.stop(CommandControl.getInstance().getCurrent());
      }
    }
    
}
