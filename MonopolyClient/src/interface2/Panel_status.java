package interface2;


import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

class Panel_status extends JPanel{ 
	final int MAX = 100 ;
	Graphics graph;	
	JButton stop_partie; //affecte les boutons de l'utilisateur
	JButton lance_de; // lance le dés 
	boolean wantToQuit= false;
	boolean fin_partie= false;
	boolean play = false;
	int caseOfPion=0;
	Graphics g;
	int height;
	int width;
	Random rand;
	public JLabel info;
	public JLabel info1;
	Player player;
	//public Jlabel somme;
	
	public Panel_status(Player pl){ 
			//this.setBackground(Color.green);
			this.player = pl;
			this.setBackground(Color.LIGHT_GRAY);
			this.setSize(800, 200);
			player.setPanelStatus(this);

			info1 = new JLabel();
			info1.setBounds(0, 60, 600, 10);
			info1.setText("info: !");
			this.add(info1);

			info = new JLabel();
			info.setBounds(10, 10, 600, 10);
			info.setText("Bonne chance !:");
			this.add(info);
			player.setPanelStatus(this);

				  
		}
	
	public void paintComponent (Graphics g){
		super.paintComponent(g);
		 graph = this.getGraphics();		
		Dimension dim = new Dimension();
		//System.out.println("taille du panneau status est " + this.getHeight()+ " " + this.getWidth());
	}
	
	

}