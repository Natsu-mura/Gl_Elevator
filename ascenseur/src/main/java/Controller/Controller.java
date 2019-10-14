package Controller;

import com.jfxtest.ControllerInterface;
import Operative.Control;
import Exterior.Exterior;
import Interior.Interior;
import Motor.MotorDisplay;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

public class Controller implements ControllerInterface{

    private boolean stopped;
    private int state;

    public static final int UP = 1;
    public static final int DOWN = -1;


    private final boolean requestsUp[];
    private final boolean requestsDown[];
    private final int maxFloor = 8;
    private final int nbFloors = 9;
    private int lastFloor;
    private int nextStop;
    private boolean emergency;
    
    private static Interior interior;
    private static Exterior ext;
    private static Control op;
    private static MotorDisplay motor;
    private boolean libre;
  
    private Controller(){
        this.lastFloor = 0;
        this.nextStop=0;
        this.stopped = true;
        this.state = 0;
        this.libre=true;
        this.requestsUp = new boolean[9];
        this.requestsDown = new boolean[9];
        this.emergency = false;
    }
     
    /** Holder */
    private static class SingletonHolder{       
            private static final Controller INSTANCE = new Controller();
    }
    public static Controller getInstance() {
        return SingletonHolder.INSTANCE;
    }
    
    public Interior getInt(){
        return Controller.interior;
    }
 
    /** Point d'accès pour l'instance unique du singleton
    * @param interior
    * @param ext
    * @param op
    * @param motor */
    public static void setInstance(Interior interior, Exterior ext, Control op, MotorDisplay motor){
        Controller.interior=interior;
        Controller.ext=ext;
        Controller.op=op;
        Controller.motor=motor;
    }
    
    public int getTarget(){
        return this.nextStop;
    }
    public int getCurrent(){
        return this.lastFloor;
    }
    public boolean getEmergency(){
        return this.emergency;
    }
    public int getState(){
        return this.state;
    }
    public boolean getLibre(){
        return this.libre;
    }
    public void changeLibre(boolean libre){
        this.libre=libre;
    }

    
    // callback des boutons à l'intérieur de l'ascenseur
    @Override
    public void intButtonCallback(int target){

        if (emergency || !this.libre || target==-1) 
            return;

        Controller.interior.getInteriorKeyboard().setIntLight(target, true);
        if(target==this.lastFloor && this.stopped){
            interior.getInteriorKeyboard().setIntLight(target, false);

            return;
        }
        switch (this.state) {
            case 0:
                if (target > this.lastFloor){
                    requestsUp[target] = true;
                    this.state = UP;
                }
                else{
                    requestsDown[target] = true;
                    this.state = DOWN;
                }   break;
            case UP:
                if (target > this.lastFloor+1 || this.stopped)
                    requestsUp[target] = true; // +1 par securité (eviter arrêts brusques)
                else
                    requestsDown[target] = true;
                break;
            case DOWN:
                if (target < this.lastFloor-1 || this.stopped)
                    requestsDown[target] = true; // +1 par sécurité (éviter arrêts brusques)
                else
                    requestsUp[target] = true;
                break;
            default:
                break;
        }
        this.updateTarget();
    }

    // callback des boutons aux étages (exterieurs à l'ascensseur)
    // ajoute requête dans la bonne liste de requêtes
    @Override
    public void extButtonCallback(int floorRequest, int direction){
        
        if (emergency || !this.libre) 
            return;
        ext.setExtLight(floorRequest, direction);
        if(floorRequest==this.lastFloor && this.stopped){
            ext.setExtLight(floorRequest, 0);

            return;
        }
        System.out.println(this.state);
        switch (this.state) {
            case 0:
                if (floorRequest > this.lastFloor){
                    requestsUp[floorRequest] = true;
                    this.state = UP;
                    ext.setExtLight(floorRequest, direction);
                }
                else{
                    requestsDown[floorRequest] = true;
                    this.state = DOWN;
                    ext.setExtLight(floorRequest, direction);
                }   break;
            case UP:
                if (floorRequest > this.lastFloor+1 || this.stopped)
                    requestsUp[floorRequest] = true; // +1 par securité (eviter arrêts brusques)
                else
                    requestsDown[floorRequest] = true;
                break;
            case DOWN:
                if (floorRequest < this.lastFloor-1 || this.stopped)
                    requestsDown[floorRequest] = true; // +1 par sécurité (éviter arrêts brusques)
                else
                    requestsUp[floorRequest] = true;
                break;
            default:
                break;
        }
        
        
        this.updateTarget();
    }

    // call back lorsque l'ascenseur croise un niveau
    @Override
    public void floorCrossedCallback(int floorDetection) throws InterruptedException{
        if (this.emergency)
            return;
        this.lastFloor = floorDetection;
        
        // --> envoyer a l'écran le numero de l'étage et la direction (up/down)
        interior.getScreen().setFloor(this.lastFloor);

        // dans le cas où l'ascenseur croise l'étage cible
        if (floorDetection == this.nextStop){
            this.changeLibre(false);
            // on retire l'étage des requêtes et on s'arrête
            // --> on éteint la lumiere des boutons correspondants à l'étage
            interior.getInteriorKeyboard().setIntLight(floorDetection, false);
            ext.setExtLight(floorDetection, 0);
            interior.getScreen().setDirection(0);

            if (this.state == UP) 
                requestsUp[floorDetection] = false;
            
            else if ( this.state == DOWN ) 
                requestsDown[floorDetection] = false;
            
            stopped = true;
            TranslateTransition tt = new TranslateTransition(Duration.millis(2000), Controller.motor.getElevator());
            tt.setByX(25);
            tt.setCycleCount(1);
            TranslateTransition tt2 = new TranslateTransition(Duration.millis(2000), Controller.motor.getElevator());
            tt2.setByX(-25);
            tt2.setCycleCount(1);
            tt.play();
            
            tt.setOnFinished(e -> {
                tt2.play();
            });
            tt2.setOnFinished(e -> {
                this.updateTarget();
                this.changeLibre(true);
            });
        }
        
        
        if(this.state == UP){
            if(this.nextStop < floorDetection){
                this.state = DOWN;
                interior.getScreen().setDirection(DOWN);
                this.stopped=false;
                motor.goDown();
            }

            else if(this.nextStop > floorDetection){
                interior.getScreen().setDirection(UP);
                this.stopped=false;
                motor.goUp();
            }
        }

        else if (this.state == DOWN){
            if (this.nextStop > floorDetection){
                this.state = UP;
                interior.getScreen().setDirection(UP);

                this.stopped = false;
                motor.goUp();
            }
          
            else if(this.nextStop < floorDetection){
                interior.getScreen().setDirection(DOWN);
                this.stopped = false;
                motor.goDown();
            }
        }
    }

    // etat d'urgence, l'ascenseur est arrêté, on réinitialise les données
    @Override
    public void emergency(){
        this.emergency = true;
        this.stopped = true;
        this.changeLibre(false);
        motor.stopNow();
        this.state = 0;
        interior.getInteriorKeyboard().setIntLight(9, true);
        this.emptyRequests();
        interior.getScreen().setDirection(0);
    }

    // fin d'état d'urgence, on demande à l'ascenseur de descendre au premier étage qu'il trouve
    // ce qui va lancer la floorCrossedCallBack et updateTarget pour reinitialiser les dernieres
    // valeurs
    @Override
    public void emergencyResolved(){
        /*double h = Controller.motor.getHeight();
        interior.getInteriorKeyboard().setIntLight(9, false);
        TranslateTransition tt = new TranslateTransition(Duration.millis(3000), Controller.motor.getElevator());
        tt.setToY( ( h - 51 ) - ( this.lastFloor * ( h / 9) ) );
        tt.setCycleCount(1);
        tt.play();
        tt.setOnFinished(e -> {
                this.emergency=false;
                this.changeLibre(true);
            try {
                this.floorCrossedCallback(this.lastFloor);
            } catch (InterruptedException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        });*/
    }

    @Override
    public void updateTarget(){
        boolean found = false;
        int floorBrowse = this.lastFloor;
        
        if (state == UP){

            while(floorBrowse < this.nbFloors && !found){
                if(requestsUp[floorBrowse]){
                    found = true;
                    this.nextStop = floorBrowse;
                }
                floorBrowse++;
            }
            // sinon si il existe des requestes de descente AU DESSUS de l'ascensseur
            // on choisi la plus haute pour ne rater personne
            if(!found){
                floorBrowse = this.lastFloor;
                while (floorBrowse < this.nbFloors){
                    if(requestsDown[floorBrowse] == true){
                        found = true;
                        this.nextStop = floorBrowse;
                    }
                    floorBrowse++;
                }
            }
            
            if (found && this.stopped){
                this.stopped = false;
                motor.goUp();
                return;
            }
            // sinon on verifie si les listes ne sont pas vides
            if (!found){
                boolean empty = true;
                for(int index = 0 ; index < maxFloor ; index++){
                    if( requestsUp[index] || requestsDown[index] ){
                        empty = false;
                        break;
                    }
                }
              //si elles ne sont pas vides, c'est qu'on passe en phase descente
                if(empty == false){
                    state = DOWN;
                    this.updateTarget();
                }
                else
                    this.state=0;
                
            }
        }
        // si on est en phase de descente
        else if (state == DOWN){
            // si il reste des requêtes EN DESSOUS de l'ascensseur,
            // la premiere sera notre prochain arrêt
            while(floorBrowse >= 0 && !found){
                if( requestsDown[floorBrowse] ){
                    found = true;
                    this.nextStop = floorBrowse;
                }
            floorBrowse--;
            }
            // sinon si il y a des requêtes dans la liste de montee EN DESSOUS de l'ascensseur
            // on choisi la plus basse pour ne rater personne
            if( found != true ){
                floorBrowse = this.lastFloor;
                while (floorBrowse >= 0){
                    if( requestsUp[floorBrowse] ){
                        found = true;
                        this.nextStop = floorBrowse;
                    }
                floorBrowse--;
            }
          }
            
            if (found && this.stopped){
                this.stopped = false;
                motor.goDown();
                return;
            }
            // sinon on verifie si les listes ne sont pas vides
            if (!found){
                boolean empty = true;
                for(int index = 0; index < maxFloor; index++){
                    if( requestsUp[index] || requestsDown[index] ){
                        empty = false;
                        break;
                    }
                }
            //si elles ne sont pas vides, c'est qu'on passe en phase montée
                if(!empty){
                    this.state = UP;
                    this.updateTarget();
                }
                else
                    this.state = 0;
                
            }
        }
    }

    
    public void emptyRequests(){
        for(int i=0; i<this.nbFloors; i++){
            this.requestsDown[i]=false;
            this.requestsUp[i]=false;
            interior.getInteriorKeyboard().setIntLight(i, false);
            ext.setExtLight(i, 0);
        }
    }
}
