package hashcode;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;


public class Main {

	static Scanner in;

	public static void main(String args[]){
		long cdeb = System.currentTimeMillis();
		ArrayList<Produit> lprods;
		ArrayList<Entrepot> lwarehouses;
		ArrayList<Drone> ldrones;
		ArrayList<Commande> lcommandes;
		try {
			String rendu="";
			//FileWriter fw= new FileWriter(new File("mother_of_all_warehouses.txt"));
			FileWriter fw= new FileWriter(new File("redundancy.txt"));
			//FileWriter fw= new FileWriter(new File("busy_day.txt"));
			Locale.setDefault(Locale.ENGLISH);
			//in = new Scanner(new File("mother_of_all_warehouses.in"));
			in = new Scanner(new File("redundancy.in"));
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
				Entrepot tmp = new Entrepot(in.nextInt(),in.nextInt());
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
				Commande ordertmp = new Commande(in.nextInt(),in.nextInt());
				int nbitems = in.nextInt();
				for(int j = 0; j<nbitems; j++){
					ordertmp.getProduits().add(lprods.get(in.nextInt()));
				}
				lcommandes.add(ordertmp);
			}
			
			for(Entrepot e : lwarehouses){
				ArrayList<Commande> l = e.honorable(lcommandes);
				for(Commande c : l){
					System.out.println(c.getLocation().x +"   "+c.getLocation().y+"    "+c.poidstotal()*e.getLocation().distance(c.getLocation())+"");
					fw.write(c.getLocation().x +"   "+c.getLocation().y+"    "+c.poidstotal()*e.getLocation().distance(c.getLocation())+"\n");
				
				}
				fw.write("\n\n");
			}
			
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
}

