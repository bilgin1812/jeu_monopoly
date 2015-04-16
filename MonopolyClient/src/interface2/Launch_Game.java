package interface2;

import java.util.Random;



public class Launch_Game {
	public static String id;
	public static Player player; 
	
	public static void main (String args[])
	 { 
		
		//affectation d'un identifiant au joueur par random
		
		Random r = new Random();
		int Low = 0;
		int High = 9;
		int R = r.nextInt(High-Low) + Low;
		
		id = Integer.toString(R);
		System.out.println("l'id de votre joueur est " + id + " ou en entier " + Integer.valueOf(id));
		//cr�ation du joueur
		player = new Player();
		player.getId(id);
	
		FenetreMonopoly fen= new FenetreMonopoly(player);
		fen.setVisible(true);
		
		
	 }

}
