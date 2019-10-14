/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interior;

import com.jfxtest.ButtonLightInterface;
import java.util.Arrays;
import javafx.scene.Parent;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author justi
 */
public class InteriorKeyboard extends Parent implements ButtonLightInterface{
    
    private final InteriorKey keys[];
    
    public InteriorKeyboard(){
        Rectangle fond_int = new Rectangle();
        fond_int.setWidth(150);
        fond_int.setHeight(350);
        fond_int.setArcWidth(10);
        fond_int.setArcHeight(10);
        fond_int.setFill( //on remplit notre rectangle avec un dégradé
            new LinearGradient(0f, 0f, 0f, 1f, true, CycleMethod.NO_CYCLE,
                new Stop[] {
                    new Stop(1, Color.web("#7e7e7e")),
                    new Stop(0, Color.web("#aeaeae"))
                }
            )
        );
        
        keys = new InteriorKey[]{
            new InteriorKey("0",0,15,290),
            new InteriorKey("1",1,85,220),
            new InteriorKey("2",2,15,220),
            new InteriorKey("3",3,85,150),
            new InteriorKey("4",4,15,150),
            new InteriorKey("5",5,85,80),
            new InteriorKey("6",6,15,80),
            new InteriorKey("7",7,85,10),
            new InteriorKey("8",8,15,10),
            new InteriorKey("/!\\",-1,85,290),
        };
        
        
        DropShadow s = new DropShadow();
        s.setOffsetX(6.0);
        s.setOffsetY(4.0);
        fond_int.setEffect(s);
        this.getChildren().add(fond_int);
        
        this.getChildren().addAll(Arrays.asList(keys));
        
    }

    @Override
    public void setIntLight(int i, boolean status) {
        this.keys[i].light(status);
    }

    @Override
    public void setExtLight(int i, int direction) {
        throw new UnsupportedOperationException("Not supported"); //To change body of generated methods, choose Tools | Templates.
    }
}
