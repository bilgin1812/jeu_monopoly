package interface2;


import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JPanel;
public class InitGamePanel extends JPanel implements ActionListener{
	
		final int MAX = 100 ;
		Graphics graph;
		//JButton connexion; 
		JButton rejoindre_partie;
		JButton creer_partie;
		JButton start_partie;
		/*Bouton rejoindre_partie;
		Bouton creer_partie;
		Bouton start_partie;*/
		boolean start;
		boolean play = false;		
		Graphics g;
		int height;
		int width;
		int compteur =0;
		
		public InitGamePanel(){ 
			    this.start= false;
			    this.setLayout(new GridBagLayout());
			   
			    
			    
				rejoindre_partie = new JButton("rejoindre partie");
				creer_partie= new JButton("creer partie");
				start_partie = new JButton("Start partie"); 
			    
				
				 this.add(rejoindre_partie);
				 this.add(creer_partie);
				 this.add(start_partie);
				/* pan.add(rejoindre_partie);
				 pan.add(creer_partie);
				 pan.add(start_partie);*/
				
				 start_partie.setBounds(50, 50, 150, 150);
				 start_partie.addActionListener(this);
				 creer_partie.addActionListener(this);
				 rejoindre_partie.addActionListener(this);
				 
				/* Bouton ecoute_start = new Bouton (1);
				 Bouton ecoute_rejoindre = new Bouton(2);
				 Bouton ecoute_creer= new Bouton(3);
				 this.start_partie.addActionListener(ecoute_start);
				 this.creer_partie.addActionListener(ecoute_creer);
				 this.rejoindre_partie.addActionListener(ecoute_rejoindre);*/
				
				 
				 
				  //affectation gestion de l'évenement aux boutons
				/*  rejoindre_partie.addActionListener(new ActionListener(){
						public void actionPerformed (ActionEvent e){
							JButton b = (JButton)e.getSource();
							if (b.getText().equals("rejoindre partie")){
								System.out.println("Le joueur veut rejoindre une partie");
							
								
								
							    
							}
						  }});
				  rejoindre_partie.setBounds(55, 150,
				             40, 50);
				  
				  creer_partie.addActionListener(new ActionListener(){
						public void actionPerformed (ActionEvent e){
							JButton b = (JButton)e.getSource();
							if (b.getText().equals("creer partie")){
							    
								System.out.println("Le joueur veut créer une partie");							    
							}
						  }});
				  start_partie.addActionListener(new ActionListener(){
						public void actionPerformed (ActionEvent e){
							JButton b = (JButton)e.getSource();
							if (b.getText().equals("Start partie")){
							    
								System.out.println("Le joueur souhaite lancer le jeu");
							    start=true;
							}
						  }});
				  
				  start_partie.setBounds(50, 50, 150, 50);
				  
				  */
				  
			}
		
		/*
		 * return state of start variable
		 */
		public boolean Start_Partie(){
			return start;
			
		}
		
		public void paintComponent (Graphics g){
			super.paintComponent(g);
			 graph = this.getGraphics();		
			Dimension dim = new Dimension();
			System.out.println("taille du panneau InitGame est " + this.getHeight()+ " " + this.getWidth());
		}
		
		public boolean isOk(boolean val){
			
			return val;
		}
		
		public void actionPerformed (ActionEvent ev)
		{ 
			if (ev.getSource() == this.creer_partie)
				System.out.println ("action sur creer_partie") ;
			if (ev.getSource() == this.start_partie){
				System.out.println ("action sur start partie") ;
				this.start=true;
				compteur++;
				
			}
		}
		
		

	}


