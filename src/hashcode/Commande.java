package hashcode;

import java.util.ArrayList;

public class Commande extends Emplacement {
	private ArrayList<Produit> produits;
	private Coordonnees location;
	public Commande(int x, int y){
		produits= new ArrayList<Produit>();
		location=new Coordonnees(x,y);
	}
	public ArrayList<Produit> getProduits() {
		return produits;
	}
	public void setProduits(ArrayList<Produit> produits) {
		this.produits = produits;
	}
	public Coordonnees getLocation() {
		return location;
	}
	public void setLocation(Coordonnees location) {
		this.location = location;
	}
	
}
