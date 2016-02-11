package hashcode;

import java.util.ArrayList;

public class Entrepot extends Emplacement {
	private ArrayList<Produit> produits_disponibles;
	public Entrepot(int x, int y){
		produits_disponibles=new ArrayList<Produit>();
		location=new Coordonnees(x,y);
	}
	public ArrayList<Produit> getProduits_disponibles() {
		return produits_disponibles;
	}
	public void setProduits_disponibles(ArrayList<Produit> produits_disponibles) {
		this.produits_disponibles = produits_disponibles;
	}

	
}
