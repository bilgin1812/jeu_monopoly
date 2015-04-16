package interface2;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

class Panel_Jeu extends JPanel{ 
	Player player;
	final int MAX = 100 ;
	Graphics graph;	
	JLabel picLabel;
	BufferedImage myPicture = null;
	public pion pi1 ;
	public pion[] pion;
	Case2[] position = new Case2[41];
	public int pos = 0;
	int x,y;
	public String[] color={"red",
		"blue","green", "orange"};
	ImageIcon icon = null;
	public Image img;
	public int nbre_joueur_partie = 2;
	static final int maxJoueur=9;
	ArrayList<Terrain> terrain= new ArrayList<Terrain>();

	public Panel_Jeu(Player p ) {
		
		player= p;
		//pi1 = new pion("red", 12, this);
		pion = new pion[this.nbre_joueur_partie];
		this.piongetId();
		
		
		System.out.println("Panneau Jeu a player " + player.id);
		player.getTabPion(pion);
		//player.getPion(pi1);
		
		player.setPanelJeu(this);
		
	
	    //0) brun 1)bleu 2)rose 3)rouge 4) jaune 5) vert 
		
	    position[0] = new Case2(580,580, this.nbre_joueur_partie);
	    position[1] = new Case2(516,580, this.nbre_joueur_partie, "brun");
	    position[2] = new Case2(465,580, this.nbre_joueur_partie);
	    position[3] = new Case2(414,580, this.nbre_joueur_partie, "brun");
	    position[4] = new Case2(362,580, this.nbre_joueur_partie);
	    position[5] = new Case2(311,580, this.nbre_joueur_partie);
	    position[6] = new Case2(260,580, this.nbre_joueur_partie, "bleu");
	    position[7] = new Case2(209,580, this.nbre_joueur_partie);
	    position[8] = new Case2(159,580, this.nbre_joueur_partie,"bleu");
	    position[9] = new Case2(108,580, this.nbre_joueur_partie, "bleu");
	    position[10] = new Case2(15,607, this.nbre_joueur_partie, "prison");
	    
	    position[11] = new Case2(30,515, this.nbre_joueur_partie, "rose");
	    position[12] = new Case2(30,464, this.nbre_joueur_partie);
	    position[13] = new Case2(30,413, this.nbre_joueur_partie,"rose");
	    position[14] = new Case2(30,363, this.nbre_joueur_partie, "rose");
	    position[15] = new Case2(30,312, this.nbre_joueur_partie);
	    position[16] = new Case2(30,261, this.nbre_joueur_partie, "orange");
	    position[17] = new Case2(30,209, this.nbre_joueur_partie);
	    position[18] = new Case2(30,158, this.nbre_joueur_partie, "orange");
	    position[19] = new Case2(30,108, this.nbre_joueur_partie, "orange");
	    position[20] = new Case2(40,40, this.nbre_joueur_partie, "parc");
	    
	    position[21] = new Case2(108,29, this.nbre_joueur_partie, "rouge");
	    position[22] = new Case2(159,29, this.nbre_joueur_partie);
	    position[23] = new Case2(209,29, this.nbre_joueur_partie, "rouge");
	    position[24] = new Case2(260,29, this.nbre_joueur_partie, "rouge");
	    position[25] = new Case2(311,29, this.nbre_joueur_partie);
	    position[26] = new Case2(362,29, this.nbre_joueur_partie, "jaune");
	    position[27] = new Case2(414,29, this.nbre_joueur_partie, "jaune");
	    position[28] = new Case2(465,29, this.nbre_joueur_partie);
	    position[29] = new Case2(516,29, this.nbre_joueur_partie, "jaune");   
	    position[30] = new Case2(580,35, this.nbre_joueur_partie);
	    position[31] = new Case2(595,108, this.nbre_joueur_partie, "vert");
	    position[32] = new Case2(595,158, this.nbre_joueur_partie, "vert");
	    position[33] = new Case2(595,209, this.nbre_joueur_partie);
	    position[34] = new Case2(595,261, this.nbre_joueur_partie,"vert");
	    position[35] = new Case2(595,312, this.nbre_joueur_partie);
	    position[36] = new Case2(595,363, this.nbre_joueur_partie);
	    position[37] = new Case2(595,413, this.nbre_joueur_partie, "bleu");
	    position[38] = new Case2(595,464, this.nbre_joueur_partie);
	    position[39] = new Case2(595,515, this.nbre_joueur_partie, "bleu");
	    position[40] = new Case2(55,570, this.nbre_joueur_partie); //prison
	    
	    try
	    {icon = new ImageIcon("resources/carte_jeu.jpg");}
	    catch (Exception e)
	    {
	    	System.out.println("EEEEEEEEEEEE "+e.getMessage());
	    	icon = new ImageIcon("carte_jeu.jpg");
	    
	    }
	    this.img = icon.getImage();
		   
	    this.repaint();
	}
	
	
	public void piongetId(){
		for(int i =0;  i<this.nbre_joueur_partie; i++){
			pion[i] = new pion(this.color[i], 12, this);
			
			if (i==0) //le pion du joueur � l'�cran est toujours rouge
				pion[i].getId(Integer.valueOf(this.player.id));
			//chargement d'id au pion
			else				
				pion[i].getId(i);
				
		}	
				
			
	}

	
	public void paintComponent (Graphics g1){
		pos = pos % 40; 
	    super.paintComponent(g1);
	    g1.drawImage(img, 0, 0,img.getWidth(null), img.getHeight(null), null);
	   // pi1.setPosition(g1,position[pi1.pos].posx,position[pi1.pos].posy);
	  
	   
	    //on dessine les pions
	    for (int i = 0; i<this.nbre_joueur_partie; i++){
	    	if (pion[i]!=null){
	    		//System.out.println("le pion avec l'id " + pion[i].id+ " a la position "+  position[pion[i].pos].pos_grille(pion[i].id)[0]+" "+  position[pion[i].pos].pos_grille(pion[i].id)[1]);
	    		pion[i].setPosition(g1, position[pion[i].pos].pos_grille(pion[i].id)[0],  position[pion[i].pos].pos_grille(pion[i].id)[1]);
	    		
	    	}
	    }
	    
	    //dessin des cases de terrains
	    for (int i=0; i<this.terrain.size(); i++)
	    	this.terrain.get(i).drawTerrain(g1);		
	}
	
	
	public class Case2 {
		  int width;
		  int height;
		  int nbre_joueur;
		  int posx;
		  int posy;
		  String color="";
		  int[][] grille;
		  static final int maxJoueur=9;
		 public int colonne;
		 public int ligne;
		 public int goToX;
		 public int goToY;
		 public int begin_x;
		 public int begin_y;
		 public ArrayList<ArrayList <Integer[]>> goTo;
		 
		 
		  public Case2(int x, int y, int nbre_joueur) {
			  posx = x;
			  posy = y;
			  this.nbre_joueur= nbre_joueur;		  
			  grille = new int[(int) Math.sqrt(maxJoueur)][(int) Math.sqrt(maxJoueur)];
			  colonne = (int) Math.sqrt(maxJoueur);
			  ligne = (int) Math.sqrt(maxJoueur);
			  //initialisation du tableau
			  width= 65;
			  height= 50;
			  this.begin_x= posx-width/2;
			  this.begin_y = posy-height/2;
			  this.goTo= new ArrayList<ArrayList<Integer[]>>();
			  this.charge_grille();
			 
			  
		  }
		  
		  public Case2(int x, int y, int nbre_joueur, String col) {
			  posx = x;
			  posy = y;
			  this.color = col;
			  this.nbre_joueur= nbre_joueur;		  
			  grille = new int[(int) Math.sqrt(maxJoueur)][(int) Math.sqrt(maxJoueur)];
			  colonne = (int) Math.sqrt(maxJoueur);
			  ligne = (int) Math.sqrt(maxJoueur);
			  //initialisation du tableau
			  width= 65;
			  height= 50;
			  this.begin_x= posx-width/2;
			  this.begin_y = posy-height/2;
			  this.goTo= new ArrayList<ArrayList<Integer[]>>();
			  this.charge_grille();
			 
			  
		  }
		  
		  
		  
		
			
		  public void charge_grille(){
			  int largeur_bande= this.width/ligne;
			  int move = largeur_bande/2;
			  int init_x = this.begin_x+(largeur_bande-move);
			  int init_y = this.begin_y+(largeur_bande-move);
			
			  for(int i=0; i<= this.ligne; i++){
				  	this.goTo.add(i, new ArrayList<Integer[]>());
				  	goToX = init_x+largeur_bande*i;
				  	
				  	
				  	for (int j=0; j<=this.colonne; j++){
				  		this.goTo.get(i).add(new Integer[2]);
				  		
				  		goToY = init_y+ (largeur_bande)*j;
				  		this.goTo.get(i).get(j)[0]= goToX;
				  		this.goTo.get(i).get(j)[1]= goToY;
					  
				  }
				  
				  
			  }
			  
		  }
		  
		  public Integer[] pos_grille(int id){
			  int player_line = id/ligne;
			  int player_colonne = id%colonne;		  
			  
			  return this.goTo.get(player_line).get(player_colonne);
		  }
	}//fin class case2
	
	
	
	
	
	
}