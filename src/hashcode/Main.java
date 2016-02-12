package hashcode;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;
import java.util.TreeMap;


public class Main {

	static Scanner in;

	public static void main(String args[]){
		long cdeb = System.currentTimeMillis();
		ArrayList<Produit> lprods;
		ArrayList<Entrepot> lwarehouses;
		ArrayList<Drone> ldrones;
		ArrayList<Commande> lcommandes;
		HashMap<Coordonnees, Emplacement> mapEmplacements = new HashMap<Coordonnees, Emplacement>();
		try {
			String rendu="";
			FileWriter fw= new FileWriter(new File("mother_of_all_warehouses.txt"));
			//FileWriter fw= new FileWriter(new File("redundancy.txt"));
			//FileWriter fw= new FileWriter(new File("busy_day.txt"));
			Locale.setDefault(Locale.ENGLISH);
			in = new Scanner(new File("mother_of_all_warehouses.in"));
			//in = new Scanner(new File("redundancy.in"));
			//in = new Scanner(new File("mbusy_day.in"));

			int nbcom=0;
			int rows=in.nextInt(),columns=in.nextInt(),drones=in.nextInt(),turns=in.nextInt(),maxPayload=in.nextInt();
			
			int nbProduits = in.nextInt();
			lprods = new ArrayList<Produit>(nbProduits);
			for(int i = 0; i<nbProduits; i++){
				lprods.add(new Produit(i,in.nextInt()));
			}
			
			int nbWarehouses = in.nextInt();
			lwarehouses = new ArrayList<Entrepot> (nbWarehouses);
			
			for(int i = 0; i<nbWarehouses; i++){
				int x, y;
				x = in.nextInt();
				y = in.nextInt();
				Entrepot tmp = new Entrepot(x,y,i);
				mapEmplacements.put(new Coordonnees(x,y), tmp);
				ArrayList<Produit> l = tmp.getProduits_disponibles();
				for(int j = 0; j<nbProduits; j++){
					int t = in.nextInt();
					for(int k = 0; k <t; k++){
						l.add(lprods.get(j));
					}
				}
				tmp.setProduits_disponibles(l);
				lwarehouses.add(tmp);
			}
			
			
			ldrones = new ArrayList<Drone>(drones);
			for(int i=0; i<drones; i++){
				ldrones.add(new Drone(maxPayload, lwarehouses.get(0).getLocation(), i));
			}

			int nborders = in.nextInt();
			lcommandes = new ArrayList<Commande>(nborders);
			for(int i = 0; i <nborders; i++){
				Commande ordertmp = new Commande(in.nextInt(),in.nextInt(),i);
				int nbitems = in.nextInt();
				for(int j = 0; j<nbitems; j++){
					ordertmp.getProduits().add(lprods.get(in.nextInt()));
				}
				lcommandes.add(ordertmp);
			}
			
			for(Entrepot e : lwarehouses){
				ArrayList<Commande> l = e.honorable(lcommandes);
				for(Commande c : l){
					//fw.write(c.getLocation().x +"   "+c.getLocation().y+"    "+c.poidstotal()*e.getLocation().distance(c.getLocation())+"\n");
				
				}
			}
			
			rendu = algoDeLaMuerte(ldrones, lwarehouses, lcommandes, turns, mapEmplacements, fw);
			
			//System.out.println("carre "+sq.getScore());
			//System.out.println("# "+dessin.getHashtag());
			//System.out.println(dessin.toString());
			//fw.write(nbcom+"\n");
			//fw.write(rendu);
			//	fw.write(dessin.toString());
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		long c = System.currentTimeMillis() - cdeb;
		System.out.println(c);
	}	
	
	
	public static String algoDeLaMuerte(ArrayList<Drone> ldrones, ArrayList<Entrepot> lwarehouses, ArrayList<Commande> lcommandes, int turns, HashMap<Coordonnees, Emplacement> mapEmplacements, FileWriter fw) throws IOException{
		String rendu = "";
		int nbCom = 0;
		for(int i=0;i<turns-1;i++){
			System.out.println(i);
			rendu = "";
			int numDrone = 0;
			for(Drone d:ldrones){
				if(d.getTempsRestant() == 0){
					if(true){ //A modifier
						//System.out.println("test");
						String renduTemp = "";
						//System.out.println(mapEmplacements.get(d.getPosition()));
						ArrayList<Commande> com = lwarehouses.get(0).honorable(lcommandes);
							int y = 0;
							while(y < com.get(0).getProduits().size()){
								if(d.getCapaciteMax() >= d.getCharge()+com.get(0).getProduits().get(y).getWeight()){
									d.setCharge(d.getCharge()+com.get(0).getProduits().get(y).getWeight());
									ArrayList<Produit> modifStock =  lwarehouses.get(0).getProduits_disponibles();
									int z = 0;
									while(z < modifStock.size() && modifStock.get(z).getId() != com.get(0).getProduits().get(y).getId()){
										z++;
									}
									modifStock.remove(z);
									lwarehouses.get(0).setProduits_disponibles(modifStock);
									
									ArrayList<Produit> prod = com.get(0).getProduits();
						
									//print des load
									d.setTempsRestant(d.getTempsRestant()+1);
									rendu += d.getId()+" L "+lwarehouses.get(0).getId()+" "+com.get(0).getProduits().get(y).getId()+ " 1\n";
									renduTemp += d.getId()+" D "+com.get(0).getId()+" "+com.get(0).getProduits().get(y).getId()+ " 1\n";
									nbCom += 2;
									prod.remove(y);
									com.get(0).setProduits(prod);
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
								//System.out.println(rendu);
								fw.write(rendu);
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
						nbCom++;
						//print load rien vers l'entrepot
						fw.write(rendu);
					}
				}
				else{
					d.setTempsRestant(d.getTempsRestant()-1);
				}
			}
		}
		return nbCom+"\n"+rendu;
	}
}

