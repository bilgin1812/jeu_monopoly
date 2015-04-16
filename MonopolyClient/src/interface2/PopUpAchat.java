package interface2;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

//quelle sera la couleur de l'objet que l'utilisateur aura acheté
public class PopUpAchat {
	
	Player player;
	String message;
	String question;
	
	
	 public PopUpAchat (Player pl, String msg){
		 this.player= pl;
		 JFrame frame= new JFrame();
		 
		 //0) brun 1)bleu 2)rose 3)rouge 4) jaune 5) vert 
			Object[] options = {"brun", "bleu","rose", "rouge","jaune", "vert"};
			
			int n = JOptionPane.showOptionDialog(frame, msg,
		"Choix couleur achat objet", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,
		options[1]);
			if (n==0){
				System.out.println("Objet Brun ^^ ");
				question="brun";
				
				
			}
			else if (n==1){
				System.out.println("Objet Bleu? Un schtroumpf");
				question= "bleu";
				
			}
			
			else if (n==2){
				System.out.println("Objet rose");
				question= "rose";
				
			}
			
			else if (n==3){
				System.out.println("Objet rouge? ");
				question= "rouge";
				
			}
			
			else if (n==4){
				System.out.println("Objet jaune? Un schtroumpf");
				question= "jaune";
				
			}
			
			else if (n==5){
				System.out.println("Objet Vert");
				question= "Vert";
				
			}
				//player.cl.envoiMessage("false");	
		 
	 }
	 
	 public String getPopUpAnswer(){
		 return this.question;
	 }

}
