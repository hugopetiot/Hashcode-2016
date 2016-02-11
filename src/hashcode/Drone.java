package hashcode;

import java.util.ArrayList;

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

	public ArrayList<Commande> minimum(ArrayList<Commande> commande){
		ArrayList<Commande> l=(ArrayList<Commande>) commande.clone();
		int min[]=new int[l.size()];
		int i=0;
		for(Commande c: l ){
			min[i]=position.distance(c.getLocation())*c.poidstotal();
			

			
		}
		int max=l.size();
		int j;
		ArrayList<Commande> classement=new ArrayList<Commande>();
		int cpt[]=new int[max];
		for (i=1; i<max; i++){
		      for (j=0; j<i; j++){
		    	  if(min[i]>=min[j])
		    		  cpt[i]++;
		    	  else
		    		  cpt[j]++;
		      }
		    }
		    for (i=0; i<max; i++) classement.add(l.get(i));
		return classement;
	}
		
	
	
}
