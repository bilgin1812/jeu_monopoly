package interface2;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class PopUp extends JFrame {        
	Player player;
	String message;
	int question;
	
	
	 public PopUp (Player pl, String msg){
		 this.player= pl;
		 JFrame frame= new JFrame();
			
			Object[] options = {"Oui",
		            "Non"};
			
			int n = JOptionPane.showOptionDialog(frame, msg,
		"PopUp a message", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,
		options[1]);
			if (n==0){
				System.out.println("réponse positive");
				question=1;
				
				
			}
			else if (n==1){
				System.out.println("réponse négative");
				question= 2;
				
			}
				//player.cl.envoiMessage("false");	
		 
	 }
	 
	 public int getPopUpAnswer(){
		 return this.question;
	 }
}  
        
