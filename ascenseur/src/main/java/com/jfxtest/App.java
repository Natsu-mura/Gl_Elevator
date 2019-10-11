package com.jfxtest;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author thomsb
 */
public class App extends Application{
    @Override
    public void start(Stage controlWindow) {
        
        
        /*** obtient les limites de l'écran pour le positionnement des fenêtres ***/
        
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        
        /*** Interface de contrôle ***/
        
        Control control = new Control();
        
        
        /*** Intérieur de l'ascenseur ***/
        
        Interior interior = new Interior(primaryScreenBounds);
        
        /*** Extérieur de l'ascenseur ***/
  
        Exterior ext = new Exterior(primaryScreenBounds);
        
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
