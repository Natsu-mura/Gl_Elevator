package com.jfxtest;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author thomsb
 */
public class App extends Application{
    @Override
    public void start(Stage stage) {
        stage.setTitle("Projet ascenseur - GUI");
        stage.setResizable(false);
        final BorderPane root = new BorderPane();
        final Scene scene = new Scene(root, 1100, 600);
        root.setPadding(new Insets(0, 0, 0, 0));
        
        Interior interior = new Interior();
        root.setRight(interior);
        
        
        scene.getStylesheets().add("styleSheet.css");
        root.getStyleClass().add("mainScene");
        
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
