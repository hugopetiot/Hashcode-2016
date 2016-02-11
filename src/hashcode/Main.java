package hashcode;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;


public class Main {

	static Scanner in = new Scanner(System.in);

	public static void main(String args[]){
		long cdeb = System.currentTimeMillis();

		try {
			String rendu="";
			FileWriter fw= new FileWriter(new File("logo.txt"));
			Locale.setDefault(Locale.ENGLISH);
			int nbcom=0;
			Dessin dessin = new Dessin(new File("logo.in"));
			//Dessin dessin = new Dessin(new File("learn_and_teach.in"));
			//Dessin dessin = new Dessin(new File("right_angle.in"));

			int maxl, maxc, maxsq,max;

			while(dessin.getHashtag()!=0){
				nbcom++;
				scLig=dessin.ScoreLigne();
				scCol=dessin.ScoreColonne();
				sq=dessin.ScoreCarre();
				maxl = scLig.getScore();
				maxc= scCol.getScore();
				maxsq= sq.getScore();

				if(maxl < maxc){
					max = maxc;
				}else{
					max = maxl;
				}

				if(max < maxsq){
					dessin.changeSquarre(sq);
					rendu+=sq.toString();
				}else{
					if(maxl<maxc){

						dessin.changeLine(scCol);
						rendu+=scCol.toString();
					}else{
						dessin.changeLine(scLig);	
						rendu+=scLig.toString();
					}
				}

				//System.out.println("carre "+sq.getScore());
				//System.out.println("# "+dessin.getHashtag());
			}
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

