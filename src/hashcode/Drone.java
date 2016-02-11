package hashcode;

public class Drone {
	private int capaciteMax, charge;
	private Coordonnees position, destination;
	private int tempsRestant;
	
	public Drone(int capa, Coordonnees posInit){
		capaciteMax = capa;
		position = posInit;
		destination = new Coordonnees(0, 0);
		charge = 0;
		tempsRestant = 0;
	}
	
	
	public int getCapaciteMax() {
		return capaciteMax;
	}
	public void setCapaciteMax(int tailleMax) {
		this.capaciteMax = tailleMax;
	}
	public Coordonnees getPosition() {
		return position;
	}
	public void setPosition(Coordonnees position) {
		this.position = position;
	}




	public int getCharge() {
		return charge;
	}




	public void setCharge(int charge) {
		this.charge = charge;
	}




	public Coordonnees getDestination() {
		return destination;
	}




	public void setDestination(Coordonnees destination) {
		this.destination = destination;
	}




	public int getTempsRestant() {
		return tempsRestant;
	}




	public void setTempsRestant(int tempsRestant) {
		this.tempsRestant = tempsRestant;
	}
	
}
