/**
 * 
 * Etages
 * 
 * @author Kevin
 */
public class Etage {

	private int numero;
	private boolean up;
	private boolean down;
	private boolean doorsOpen;
	
	/**
	 * 
	 * Constructeur
	 * 
	 * @param numero Numéro de l'étage
	 */
	public Etage(int numero) {
		this.numero = numero;
		this.up = false;
		this.down = false;
		this.doorsOpen = false;
	}
	
	/**
	 * 
	 * Getters & Setters
	 * 
	 */
	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
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
