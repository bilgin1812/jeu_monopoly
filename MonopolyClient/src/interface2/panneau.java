package interface2;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
	
class panneau extends JPanel { 
	JFrame frame;
	final int MAX = 100 ;
	Graphics graph;	
	boolean start_partie;
	InitGamePanel IGpanel;
	Panel_status panel_status;
	Panel_Jeu pan_jeu;
	Panel_Utilisateur utilisateur;
	int compteur =0;
	//Client cl;
	Player player;
	pion[] pi;
	Event th;
	public int nbre_joueur_partie = 2;
	
	
	

	public panneau(JFrame fr, Player pl){
		player = pl;
		
		//player = new Player();
		//player.id= player_id;
		//initialisation du player
		
		//création des pions
		
			
		
		this.frame= fr;
		start_partie= false;
		this.LayoutView();
		th = new Event(this.player);
		//lance le thread	
		th.start();
		}
	
	
	
	public void paintComponent (Graphics g){
		super.paintComponent(g) ;
		graph = this.getGraphics();		
		Dimension dim = new Dimension();
		//System.out.println("taille du panneau est " + this.getHeight()+ " " + this.getWidth());
		this.setSize(800, 700);
		this.panel_status.setBounds(0,0, this.getWidth(), 50);
		//pan_jeu.setLayout(null);
		this.pan_jeu.setBounds(0,50,630, 630);
		this.pan_jeu.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.utilisateur.setBounds(630,50, 200, this.getHeight()-50);
			      
	}
	
	
	 private void LayoutView()
	   {
		
	     
	      //this.add(playerPanel);
		 IGpanel = new InitGamePanel();
		 //this.add(IGpanel);
		 //initialisation des pannel
		 utilisateur = new Panel_Utilisateur(this.player);
		 utilisateur.setLayout(null);
		 panel_status= new Panel_status(this.player);
		 pan_jeu = new Panel_Jeu(this.player);
		 
		 this.add(panel_status);
		 this.add(pan_jeu);
		 this.add(utilisateur);
		  
	   }
	
	 public void PanelToPrint(){
		 if (IGpanel.start==true && IGpanel.compteur==1){
			this.remove(0);
			this.add(panel_status);
			this.add(pan_jeu);
			this.add(utilisateur);
		 }
		 else if(IGpanel.start==false && IGpanel.compteur==0)
			 this.add(IGpanel);
	}
	 
	 
	 
	
}
