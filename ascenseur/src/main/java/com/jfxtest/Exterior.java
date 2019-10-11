/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jfxtest;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author justi
 */
class Exterior extends Parent{
    
    private CommandControl control = CommandControl.getInstance();
    private final Scene scene;
    private final ExteriorDisplay keys[];
    
    public Exterior(Rectangle2D primaryScreenBounds){
        
        /*** Interface de base ***/
        final VBox root = new VBox();
        this.scene = new Scene(root, 350, 500);
        root.setPadding(new Insets(0, 0, 0, 0));
        root.setStyle(" -fx-background-color: linear-gradient(to bottom right, #5c5358, #757eab);");
        root.setSpacing(25);
        root.setAlignment(Pos.CENTER);
        
        /*** Creation du choix de l'etage depuis lequel l'appel est fait ***/
        String st[] = { "Etage 0", "Etage 1", "Etage 2", "Etage 3", "Etage 4", "Etage 5", "Etage 6", "Etage 7", "Etage 8" }; 
        ChoiceBox floorSelect = new ChoiceBox(FXCollections.observableArrayList(st));
        floorSelect.setPrefSize(125, 35);
        floorSelect.setStyle(
            "-fx-font: 16 centurygothic;" +
            "-fx-text-fill: #736d87;"+
            "-fx-base: #d1d1d1;"+
            "-fx-focus-color: #986767;"+
            "-fx-faint-focus-color: #986767;"
        );
        floorSelect.getSelectionModel().selectFirst();
        floorSelect.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() { 
  
            // if the item of the list is changed 
            @Override
            public void changed(ObservableValue ov, Number value, Number new_value) 
            { 
                root.getChildren().remove(keys[(int)value]);
                root.getChildren().add(keys[(int)new_value]);
            } 
        }); 
        
        keys = new ExteriorDisplay[]{
            new ExteriorDisplay(0),
            new ExteriorDisplay(1),
            new ExteriorDisplay(2),
            new ExteriorDisplay(3),
            new ExteriorDisplay(4),
            new ExteriorDisplay(5),
            new ExteriorDisplay(6),
            new ExteriorDisplay(7),
            new ExteriorDisplay(8),
        };
        
        
        root.getChildren().addAll(floorSelect, keys[0]);
        Stage window = new Stage();
        
        /*** set Stage boundaries to the lower right corner of the visible bounds of the main screen ***/
        window.setX(primaryScreenBounds.getMinX() + 100);
        //window.setY(primaryScreenBounds.getMinY() + 100);
        
        window.setTitle("Ascenseur - vue extérieure");
        window.setResizable(false);
        
        window.setScene(this.scene);
        window.show();
    }
}
