/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exterior;

import Controller.Controller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

/**
 *
 * @author justi
 */
public class ExteriorDisplay extends Parent{
    
    private Button upKey;
    private Button downKey;
    private final Controller controlCommand = Controller.getInstance();

    private final int id;

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
            controlCommand.extButtonCallback(this.id, 1);
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
            controlCommand.extButtonCallback(this.id, -1);
            
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
    
    public void light(int direction){
        switch (direction) {
            case -1:
                downKey.setStyle("-fx-font: 16 centurygothic;" +
                "-fx-font-weight : bold;" +
                "-fx-text-fill:#d46d6d;"+
                "-fx-base: #d1d1d1;"+
                "-fx-focus-color: transparent;"+
                "-fx-faint-focus-color: transparent;"
                );
                break;
            case 1:
                upKey.setStyle("-fx-font: 16 centurygothic;" +
                "-fx-font-weight : bold;" +
                "-fx-text-fill:#d46d6d;"+
                "-fx-base: #d1d1d1;"+
                "-fx-focus-color: transparent;"+
                "-fx-faint-focus-color: transparent;"
                );
                break;
            default:
                downKey.setStyle(
                "-fx-font: 16 centurygothic;" +
                "-fx-font-weight : bold;" +
                "-fx-base: #d1d1d1;"+
                "-fx-focus-color: transparent;"+
                "-fx-faint-focus-color: transparent;"
                );
                upKey.setStyle(
                "-fx-font: 16 centurygothic;" +
                "-fx-font-weight : bold;" +
                "-fx-base: #d1d1d1;"+
                "-fx-focus-color: transparent;"+
                "-fx-faint-focus-color: transparent;"
                );
                break;
        }
    }
}
