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
			



			//System.out.println("carre "+sq.getScore());
			//System.out.println("# "+dessin.getHashtag());
			//System.out.println(dessin.toString());
			System.out.println(nbcom);
			fw.write(nbcom+"\n");
			fw.write(rendu);
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

