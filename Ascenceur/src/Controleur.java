import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JButton;

/**
 * 
 * Controleur de l'ascenseur
 * 
 * @author Kevin
 */
public class Controleur {
	
	public Etage[] etage = new Etage[9];
	public Cage cage = new Cage(9);
	public ArrayList<Integer> waitList = new ArrayList<Integer>();
	
 	/**
 	 * 
 	 * Constructeur
 	 * 
 	 * @param nbEtage Nombre d'�tage de notre controleur.
 	 */
	public Controleur(int nbEtage){
		
		for(int i = 0; i < nbEtage; i++)
			etage[i] = new Etage(i);
	}
	
	
	/**
	 * 
	 * Functions
	 * 
	 */
	
	/**
	 * 
	 * Affiche la waitList
	 * 
	 */
	public void positionnement() {
		 for(Integer elem: waitList)
	       {
	       	 System.out.println (elem);
	       }
	}
	
	
	/**
	 * 
	 * Tant qu'il y a des requetes au dessus de la position de la cage, elle monte.
	 * 
	 */
	public void monter() {
		if(!waitList.isEmpty()) {
			
			while(cage.getPosition() <= Collections.max(waitList)) {
				if(stopIsWanted(cage.getPosition())) {
					System.out.println("ARRET --- Vous etes arriv� � l'�tage: " + cage.getPosition());
					doorsOpenning(cage.getPosition());
					removeFromWaitList(cage.getPosition());
				}
				else{
					System.out.println("CONTINUE --- Vous venez de d�passer l'�tage : " + cage.getPosition());
					cage.setPosition(cage.getPosition() + 1);
				}
				
				try {
					Thread.sleep(2000);
				} catch(InterruptedException e) {e.printStackTrace();}
				
				if(waitList.isEmpty()) 
					break;
			}
		}
	}
	
	
	/**
	 * 
	 * Tant qu'il y a des requetes en dessous de la position de la cage, elle descend.
	 * 
	 */
	public void descendre() {
		if(!waitList.isEmpty()) {
			
			while(cage.getPosition() >= Collections.min(waitList)) {
				
				if(stopIsWanted(cage.getPosition())) {
					System.out.println("ARRET ------ Vous etes arriv� � l'�tage     : " + cage.getPosition());
					doorsOpenning(cage.getPosition());
					removeFromWaitList(cage.getPosition());
				}
				else{
					System.out.println("CONTINUE --- Vous venez de d�passer l'�tage : " + cage.getPosition());
					cage.setPosition(cage.getPosition() - 1);
				}
				
				try {
					Thread.sleep(2000);
				} catch(InterruptedException e) {e.printStackTrace();}
				
				if(waitList.isEmpty()) 
					break;
			}
		}
	}
	
	
	/**
	 * TODO 
	 * 
	 * Efface la waitList et met tout le system en �tat de pause (Jusqu'� action interne)
	 * 
	 */
	public void urgStop() {
		
	}
	
	/**
	 * 
	 * Ajoute une requete d'�tage � la waitList et la trie.
	 * 
	 * @param number Num�ro de l'�tage � ajouter
	 */
	public void addToWaitList(int number) {
		
		if(!waitList.contains(number)) 
			waitList.add(number);
		
		Collections.sort(waitList);
	}
	
	/**
	 * 
	 * Enleve une requete d'�tage � la waitList et la trie.
	 * 
	 * @param number Num�ro de l'�tage � retirer
	 */
	public void removeFromWaitList(int number) {
		
		if(waitList.contains(number)) 
			waitList.remove((Integer)number);
		
		Collections.sort(waitList);
	}
	
	
	/**
	 * 
	 * Requete d'un bouton de destination, ajoute ou retire le num�ro demand�
	 * 
	 * @param number Num�ro de l'�tage demand�
	 */
	public void request(int number) {
		if(!waitList.contains(number)) 
			addToWaitList(number);
		else
			removeFromWaitList(number);
	}
	
	/**
	 * 
	 * Renvoie true si l'�tage est demand� sinon false
	 * 
	 * @param floor Position de la cage
	 * @return boolean
	 */
	public boolean stopIsWanted(int floor) {
		if(waitList.contains(floor)) 
			return true;
		return false;
	}
	
	/**
	 * 
	 * Ouvre les portes de la cage d'ascenseur puis celle de l'�tage.
	 * 
	 * @param floor Num�ro de l'�tage o� les portes s'ouvrent
	 */
	public void doorsOpenning(int floor) {
		
		if(!cage.isDoorsOpen()) {
			System.out.println("Ouverture des portes de la cage d'ascenceur");
			try {
				Thread.sleep(1000);
			} catch(InterruptedException e) {e.printStackTrace();}
			cage.setDoorsOpen(true);
			System.out.println("Les portes de la cages sont ouvertes");
			if(!etage[floor].isDoorsOpen()) {
				System.out.println("Ouverture des portes de l'�tage " + floor);
				try {
					Thread.sleep(1000);
				} catch(InterruptedException e) {e.printStackTrace();}
				etage[floor].setDoorsOpen(true);
				System.out.println("Les portes de l'�tage " + floor + " sont ouvertes");
				
				System.out.println("Fermeture des portes de l'�tage " + floor);
				try {
					Thread.sleep(1000);
				} catch(InterruptedException e) {e.printStackTrace();}
				etage[floor].setDoorsOpen(false);
				System.out.println("Les portes de l'�tage " + floor + " sont ferm�es");
			}
			System.out.println("Fermeture des portes de la cage");
			try {
				Thread.sleep(1000);
			} catch(InterruptedException e) {e.printStackTrace();}
			cage.setDoorsOpen(false);
			System.out.println("Les portes de la cage sont ferm�es");
		}
	}
}
