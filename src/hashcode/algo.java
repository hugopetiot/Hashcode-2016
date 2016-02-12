package hashcode;

import java.util.*;

public class algo {
	
	ArrayList<Entrepot> lwarehouses;
	TreeMap<Coordonnees, Emplacement> mapEmplacements = new TreeMap<Coordonnees, Emplacement>();
	
	public void algoDeLaMuerte(){
		for(int i=0;i<turns-1;i++){
			int numDrone = 0;
			for(Drone d:ldrones){
				if(d.getTempsRestant() == 0){
					if(mapEmplacements.get(d.getPosition()) instanceof Entrepot){ //A modifier
						String renduTemp = "";
						ArrayList<Commande> com = mapEmplacements.get(d.getPosition()).honorable(commandes);
							int y = 0;
							while(y < com.get(0).getProduits().size()){
								if(d.getCapaciteMax() >= d.getCharge()+com.get(0).getProduits().get(y).getWeight()){
									d.setCharge(d.getCharge()+com.get(0).getProduits().get(y).getWeight());
									ArrayList<Produit> modifStock =  ((Entrepot)mapEmplacements.get(d.getPosition())).getProduits_disponibles();
									int z = 0;
									while(z < modifStock.size() && modifStock.get(z).getId() != com.get(0).getProduits().get(y).getId()){
										z++;
									}
									modifStock.remove(z);
									((Entrepot)mapEmplacements.get(d.getPosition())).setProduits_disponibles(modifStock);
									
									ArrayList<Produit> prod = com.get(0).getProduits();
									prod.remove(y);
									com.get(0).setProduits(prod);
									//print des load
									d.setTempsRestant(d.getTempsRestant()+1);
									rendu += d.getId()+" L "+((Entrepot)mapEmplacements.get(d.getPosition())).getId()+" "+com.get(0).getProduits().get(y).getId()+ "1\n";
									renduTemp += d.getId()+" D "+com.get(0).getId()+" "+com.get(0).getProduits().get(y).getId()+ "1\n";
								}
								else{
									y++;
								}
								d.setTempsRestant(d.getPosition().distance(com.get(0).getLocation()));
							}
							if(com.get(0).getProduits().isEmpty()){
								lcommandes.remove(com.get(0));
							}
							
							if(d.getCharge() > 0){
								rendu += renduTemp;
							}
					}
					else{
						int min = 999999999;
						Entrepot entPlusProche = null;
						for(Entrepot e:lwarehouses){
							if(e.getLocation().distance(d.getPosition()) < min){
								min = e.getLocation().distance(d.getPosition());
								entPlusProche = e;
							}
						}
						rendu += d.getId()+" L "+entPlusProche.getId()+" 0 0\n";
						//print load rien vers l'entrepot
					}
				}
				else{
					d.setTempsRestant(d.getTempsRestant()-1);
				}
			}
		}
	}
	

}
