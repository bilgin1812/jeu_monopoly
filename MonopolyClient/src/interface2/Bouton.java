package interface2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Bouton implements ActionListener
{ 
	private int num_bouton ;
	public Bouton (int n)
	{
		this.num_bouton = n ;
	}
	
public void actionPerformed (ActionEvent ev)
{ 
	if (this.num_bouton==1){
		System.out.println ("action sur bouton " + this.num_bouton) ;	
	}
	if (this.num_bouton==4)
		System.out.println("action sur lance de " );
	if (this.num_bouton==5)
	System.out.println("action sur stop jeu " );
}

}