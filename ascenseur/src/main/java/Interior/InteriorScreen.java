/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interior;

import Design.DrawArrow;
import com.jfxtest.DisplayInterface;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author justi
 */
public class InteriorScreen extends Parent implements DisplayInterface{
    
    private Rectangle base;
    private Text display;
    private Canvas arrowLeft;
    private Canvas arrowRight;
    private int currentFloor;
    private TilePane screen;
    
    public InteriorScreen(int currentFloor){
        
        this.currentFloor=currentFloor;
        
        /*** Ecran ***/
        base = new Rectangle(250,100,Color.BLACK);
        base.setFill( //on remplie notre rectangle avec un dégradé
            new LinearGradient(0f, 0f, 0f, 1f, true, CycleMethod.NO_CYCLE,
                new Stop[] {
                    new Stop(1, Color.web("#000000")),
                    new Stop(0, Color.web("#555555"))
                }
            )
        );
        base.setArcHeight(45);
        base.setArcWidth(45);
        
        
        /*** Affichage ***/
        
        display = new Text("FLOOR\n" + this.currentFloor);
        display.setTextAlignment(TextAlignment.CENTER);
        display.setStyle(
                "-fx-font: 25 centurygothic;"+
                "-fx-font-weight: bold;"
        );
        display.setFill(Color.web("#ff4d00"));
        
        /*** Fleches ***/
        
        arrowLeft = new Canvas(50,100);
        arrowRight = new Canvas(50,100);
        new DrawArrow(true, true, arrowLeft);
        new DrawArrow(false, true, arrowRight);
        
        /*** TilePane ***/
        
        screen = new TilePane();
        screen.setPrefSize(250, 100);
        screen.getChildren().addAll(
                arrowLeft,
                display,
                arrowRight
        );
        
        
        /*** StackPane ***/
        
        StackPane layout = new StackPane();
        layout.getChildren().addAll(
                base,
                screen
        );
        layout.setPadding(new Insets(15,15,15,15));
        
        this.getChildren().add(layout);
        
       
    }

    @Override
    public void setFloor(int i) {
        this.currentFloor=i;
        this.display.setText("FLOOR\n" + this.currentFloor);
    }

    @Override
    public void setDirection(int i) {
       switch(i) {
        case -1:
            new DrawArrow(true, false, arrowLeft);
            break;
        case 1:
            new DrawArrow(false, false, arrowRight);
            break;
        default:
           new DrawArrow(false, true, arrowRight);
           new DrawArrow(true, true, arrowLeft);
        }
    
    }
}
