/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jfxtest;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

/**
 *
 * @author justi
 */
public class ControlKey extends Parent{
    
    Button button;

    private final String command;
    private final int comId;

    public ControlKey(String command, int comId) {
        this.command=command;
        this.comId=comId;
        
        button = new Button(this.command);
        button.setMinSize(225,70);
        button.setStyle(
              "-fx-font: 16 centurygothic;" +
              "-fx-font-weight : bold;"+
              "-fx-text-fill: #7a7a7a;"+
              "-fx-base: #d1d1d1;"+
              "-fx-focus-color: #f6ddbb;"+
              "-fx-faint-focus-color: #f6ddbb;"
        );
        
        this.getChildren().add(this.button);
        
        button.setOnMouseClicked((MouseEvent me) -> {
            switch (this.comId) {
            case 0:
                x.goUp();
                break;
            case 1:
                x.goDown();
                break;
            case 2:
                x.stopNextFloor();
                break;
            default:
                x.stopNow();
                break;
        }
        });
    }
}
