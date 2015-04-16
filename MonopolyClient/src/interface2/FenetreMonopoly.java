package interface2;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FenetreMonopoly extends JFrame {
	boolean initialisation= true;
	Client cl;
	Player player;
	
	public FenetreMonopoly(Player pl){
		//Scanner recup = new Scanner(System.in);
		//String id;
		//System.out.println("Quel est l'id de votre joueur ? ");
		//id = recup.next();	
		player = pl;
		
		this.setSize(800, 700);
		
		Container contenu = this.getContentPane();
	
		panneau pan= new panneau(this, player); //panneau principal
		//1) départ du jeu
	    
     
	   
		pan.setLayout(new BoxLayout(pan, BoxLayout.X_AXIS));
	    
		
		contenu.add(pan);
		
		
		
	    }
		
	  
		 
}
