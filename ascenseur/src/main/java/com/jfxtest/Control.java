/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jfxtest;

import java.util.Arrays;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author justi
 */
public class Control extends Parent{
        
    private final Scene scene;
    private final ControlKey[] commands;
    
    public Control(){
        
        /*** Interface de base ***/
        final VBox root = new VBox();
        this.scene = new Scene(root, 350, 350);
        root.setPadding(new Insets(10, 0, 10, 0));
        root.setStyle(" -fx-background-color: linear-gradient(to bottom right, #9b9192, #b7a49f);");
        root.setSpacing(10);
        root.setAlignment(Pos.CENTER);
  
        commands = new ControlKey[]{
            new ControlKey("Monter",0),
            new ControlKey("Descendre",1),
            new ControlKey("Arreter au prochain etage",2),
            new ControlKey("Arreter d'urgence",3),
        };
        
        root.getChildren().addAll(Arrays.asList(commands));
        
        Stage window = new Stage();
        
        window.setTitle("Ascenseur - Controle");
        window.setResizable(false);
        
        window.setScene(this.scene);
        window.show();
        window.setOnCloseRequest(e -> Platform.exit());
    }
}
