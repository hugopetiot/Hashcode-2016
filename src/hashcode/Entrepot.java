package hashcode;

import java.util.ArrayList;
import java.util.Iterator;

public class Entrepot extends Emplacement {
	private ArrayList<Produit> produits_disponibles;
	private int id;
	public Entrepot(int x, int y,int i){
		produits_disponibles=new ArrayList<Produit>();
		location=new Coordonnees(x,y);
		id=i;
	}
	public ArrayList<Produit> getProduits_disponibles() {
		return produits_disponibles;
	}
	public void setProduits_disponibles(ArrayList<Produit> produits_disponibles) {
		this.produits_disponibles = produits_disponibles;
	}
	
	public boolean stockDisponible(Commande c){
		boolean trouve;

		
		ArrayList<Produit>tmp=(ArrayList<Produit>) produits_disponibles.clone();
		for(Produit demande : c.getProduits()){ 
			trouve=false;
			Iterator<Produit> it=tmp.iterator();
			while(it.hasNext()){
				Produit dispo=it.next();
				if(demande.getId()==dispo.getId()){
					tmp.remove(dispo);
					trouve=true;
					break;
				}				
			}
			if(trouve==false)
				return false;
			}
		
		return true;
	}
	
	public ArrayList<Commande> honorable(ArrayList<Commande> commandes){
		ArrayList<Commande> ret=new ArrayList<Commande>();
		for(Commande c : commandes){
			if(stockDisponible(c)){
				ret.add(c);				
			}
		}
		return minimum(ret);
		
		
	}
	
	
	public ArrayList<Commande> minimum(ArrayList<Commande> commande){
		@SuppressWarnings("unchecked")
		ArrayList<Commande> l=(ArrayList<Commande>) commande.clone();
		int min[]=new int[l.size()];
		int i=0;
		for(Commande c: l ){
			min[i]=location.distance(c.getLocation())*c.poidstotal();
			i++;

			
		}
		int max=l.size();
		int j;
		ArrayList<Commande> classement=new ArrayList<Commande>();
		int cpt[]=new int[max];
		for(int k = 0; k<max; k++)
			cpt[k]=0;
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
