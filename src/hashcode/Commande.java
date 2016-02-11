package hashcode;

import java.util.ArrayList;

public class Commande extends Emplacement {
	private ArrayList<Produit> produits;
	int id;
	public Commande(int x, int y,int i){
		produits= new ArrayList<Produit>();
		location=new Coordonnees(x,y);
		id=i;
	}
	public ArrayList<Produit> getProduits() {
		return produits;
	}
	public void setProduits(ArrayList<Produit> produits) {
		this.produits = produits;
	}
	public int poidstotal(){
		int ret=0;
		for(Produit p : produits){
			ret=ret+p.getWeight();
		}
		return ret;
	}

	
}
