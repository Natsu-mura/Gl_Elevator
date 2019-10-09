/**
 * 
 * Cage d'ascenseur
 * 
 * @author Kevin
 */
public class Cage {
	
	private int position = 9;
	private boolean doorsOpen;
	
	/**
	 * 
	 * Constructor
	 * 
	 * @param position Position de la cage d'ascenseur
	 */
	public Cage(int position) {
		this.position = position;
		this.doorsOpen = false;
	}
	
	/**
	 * 
	 * Getters & setters
	 * 
	 */
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public boolean isDoorsOpen() {
		return doorsOpen;
	}
	public void setDoorsOpen(boolean doorsOpen) {
		this.doorsOpen = doorsOpen;
	}

	/**
	 * 
	 * Functions
	 * 
	 */
}
