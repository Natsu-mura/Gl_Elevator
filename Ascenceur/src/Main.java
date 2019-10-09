import java.util.ArrayList;
import java.util.Collections;

public class Main {
	
	private static Controleur control = new Controleur(9);
 	private static Fenetre ascenseur = new Fenetre(control);
 	
	public static void main(String[] args){  
			
		while(true) {
			control.positionnement();
			control.descendre();
			try {
				Thread.sleep(1000);
			} catch(InterruptedException e) {e.printStackTrace();}
		}
	}
}
