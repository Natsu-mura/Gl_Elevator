/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jfxtest;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;

/**
 *
 * @author justi
 */
public class ExteriorDisplay extends Parent{
    
    Button upKey;
    Button downKey;

    private final int id;
    private CommandControl control = CommandControl.getInstance();

    public ExteriorDisplay(int id) {
        this.id = id;
        
        VBox root = new VBox();
        root.setPrefSize(120, 215);
        root.setPadding(new Insets(0, 0, 0, 0));
        root.setStyle(" -fx-background-color: transparent;");
        root.setSpacing(25);
        root.setAlignment(Pos.CENTER);
        
        upKey = new Button("UP");
        
        upKey.setShape(new Circle(50));
        upKey.setMinSize(100,100);
        upKey.setMaxSize(100,100);

        upKey.setStyle(
                "-fx-font: 16 centurygothic;" +
                "-fx-font-weight : bold;" +
                "-fx-base: #d1d1d1;"+
                "-fx-focus-color: transparent;"+
                "-fx-faint-focus-color: transparent;"
        );
        
        upKey.setOnMouseClicked((MouseEvent me) -> {
            upKey.setStyle("-fx-font: 16 centurygothic;" +
                "-fx-font-weight : bold;" +
                "-fx-text-fill:#d46d6d;"+
                "-fx-base: #d1d1d1;"+
                "-fx-focus-color: transparent;"+
                "-fx-faint-focus-color: transparent;"
        );
        });
        
        downKey = new Button("DOWN");
        
        downKey.setShape(new Circle(50));
        downKey.setMinSize(100,100);
        downKey.setMaxSize(100,100);

        downKey.setStyle(
                "-fx-font: 16 centurygothic;" +
                "-fx-font-weight : bold;" +
                "-fx-base: #d1d1d1;"+
                "-fx-focus-color: transparent;"+
                "-fx-faint-focus-color: transparent;"
        );
        
        downKey.setOnMouseClicked((MouseEvent me) -> {
            downKey.setStyle("-fx-font: 16 centurygothic;" +
                "-fx-font-weight : bold;" +
                "-fx-text-fill:#d46d6d;"+
                "-fx-base: #d1d1d1;"+
                "-fx-focus-color: transparent;"+
                "-fx-faint-focus-color: transparent;"
        );
        });
        switch (this.id) {
            case 0:
                root.getChildren().addAll(upKey);
                break;
            case 8:
                root.getChildren().addAll(downKey);
                break;
            default:
                root.getChildren().addAll(upKey, downKey);
                break;
        }

        this.getChildren().add(root);
    }
}
