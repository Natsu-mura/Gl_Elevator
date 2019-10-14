/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interior;


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
public class Interior extends Parent{
    
    private final InteriorScreen screen;
    private final InteriorKeyboard interiorKeyboard;
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
        window.setY(primaryScreenBounds.getMinY() + 50);
        
        window.setTitle("Ascenseur - vue intérieure");
        window.setResizable(false);
        
        window.setScene(this.scene);
        window.show();
        
    }

    public InteriorScreen getScreen() {
        return screen;
    }

    public InteriorKeyboard getInteriorKeyboard() {
        return interiorKeyboard;
    }

    
    
    
}
