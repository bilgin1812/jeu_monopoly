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
import javax.swing.JProgressBar;

class Panel_Utilisateur extends JPanel implements ActionListener{ 
	final int MAX = 100 ;
	Graphics graph;	
	JButton stop_partie; //affecte les boutons de l'utilisateur
	JButton lance_de; // lance le d�s 
	JButton fin_tour;
	JButton achat;
	JButton vente;
	boolean wantToQuit= false;
	boolean fin_partie= false;
	boolean play = false;
	int caseOfPion=0;
	Graphics g;
	int height;
	int width;
	Random rand;
	int num_bouton;
	Client client;
	Player player;
	
	
	public Panel_Utilisateur(Player p){
		   
			player= p;
			
			this.setBackground(Color.LIGHT_GRAY);
			stop_partie = new JButton("stop partie");
			lance_de= new JButton("lance les d�");
			achat = new JButton("achat");
			vente = new JButton("vente");
			fin_tour = new JButton(" Fin du tour");
			this.add(achat);
			this.add(vente);
			this.add(stop_partie);
			this.add(fin_tour);
			this.add(lance_de);

			this.add(stop_partie);
			this.add(lance_de);
			lance_de.addActionListener(this);
			stop_partie.addActionListener(this);
			fin_tour.addActionListener(this);
			achat.addActionListener(this);
			vente.addActionListener(this);
			lance_de.setBounds(20, 5, 120, 30);
			fin_tour.setBounds(20, 45, 120, 30);
			
			// stop_partie.setPreferredSize(new Dimension(200, 40));
			achat.setBounds(20, 85, 120, 30);
			vente.setBounds(20, 125, 120, 30);
			stop_partie.setBounds(20, 125, 120, 30);
			
			JLabel textMe = new JLabel();
			textMe.setBounds(10, 205, 140, 15);
			textMe.setText("Moi :\n " );
			textMe.setToolTipText("Ce que vous poosedez!!");
			this.add(textMe);
			
			JProgressBar jMe= new JProgressBar();
			jMe.setBounds(10, 230 , 140, 10);
			//jMe.setValue(p.pi.argent);
			
			JLabel textPlayerargent = new JLabel();
			textPlayerargent.setBounds(10, 250, 140, 15);
			//textPlayerargent.setText("argent: "+p.pi.argent);
			
			JLabel textPlayerterrains = new JLabel();
			textPlayerterrains.setBounds(10,275, 140, 15);
			textPlayerterrains.setText("terrains: ");
			
			JLabel textPlayerbuilding = new JLabel();
			textPlayerbuilding.setBounds(10, 300, 140, 15);
			textPlayerbuilding.setText("buildings: ");

			
			this.add(textPlayerargent);
			this.add(textPlayerterrains);		
			this.add(textPlayerbuilding);		
			this.add(jMe);
			
			
	/*****joueur1*****/
			JLabel textPlayer1 = new JLabel();
			textPlayer1.setBounds(10, 315, 140, 15);
			textPlayer1.setText("Joueur 1:");// +p.pions.get("1").argent);
			JProgressBar j1= new JProgressBar();
			j1.setBounds(10, 340, 140, 10);
			//j1.setValue(p.pions.get("1").argent);
			
			JLabel textPlayer1argent = new JLabel();
			textPlayer1argent.setBounds(10, 360, 140, 15);
			//textPlayer1argent.setText("argent: "+p.pions.get("4").argent);
			
			JLabel textPlayer1terrains = new JLabel();
			textPlayer1terrains.setBounds(10, 385, 140, 15);
			textPlayer1terrains.setText("terrains: ");
			
			JLabel textPlayer1building = new JLabel();
			textPlayer1building.setBounds(10, 410, 140, 15);
			textPlayer1building.setText("buildings: ");

			
			this.add(textPlayer1argent);
			this.add(textPlayer1terrains);		
			this.add(textPlayer1building);
			//this.add(j1);
			this.add(textPlayer1);
			
			this.player.setPanelUtil(this);
			
			
			  
		}
	
	public void actionPerformed (ActionEvent ev)
	{ 
		
		
		if (ev.getSource()==this.lance_de){
			player.lance_de();	    
			
		}
		
		//achat
		if (ev.getSource()==this.achat){
			player.doAction=4;
		}
		
		//vente
		if (ev.getSource()==this.vente)
		
			player.doAction=2;
		
		
		//fin du tour
		if (ev.getSource()==this.fin_tour)
			player.doAction=5;
		
		if (ev.getSource()==this.stop_partie)
			player.doAction=6;
		
		if (ev.getSource()==this.stop_partie && this.player.myTurn==true){
			System.out.println("Stop partie");
			
		}
		
	
	}

	public void paintComponent (Graphics g){
		super.paintComponent(g);
		 graph = this.getGraphics();		
		Dimension dim = new Dimension();
		//System.out.println("taille du panneau utilisateur est " + this.getHeight()+ " " + this.getWidth());
	
	}
	
	

}