package interface2;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;



public class GUI extends JPanel {
	JPanel pan;
	int width;
	int height;
		public GUI(int width, int height){
			super();
			this.width= width;
			this.height= height;
			this.layoutView();
			this.repaint();
		}
		
		public void layoutView(){
			JPanel properties_panel=this.buildpropriete(); 
			this.add(properties_panel);
			//JPanel layout= this.buildplayer()
			
		}
		
	
	public JPanel buildpropriete(){
		
		JPanel pan = new JPanel();
		this.pan = pan;
		
		//System.out.println("la largeur du panneau est "+ pan.getHeight() + " et la hauteur du panneau est " + pan.getHeight());
	    //ArrayList<Case> ca;
	    //ca = CreateCase(); //pour l'instant insertion de case, mais selon Sascha on a autre chose
	    
		return pan;
	}
	
	 public void paintComponent(Graphics g){
		 System.out.println("la largeur du panneau est "+ pan.getHeight() + " et la hauteur du panneau est " + pan.getHeight());
	 
	 }
	 
	
	/*public ArrayList<Case> CreateCase(int width, int height){
		  int x=0;
		  int y=0;
		  
		  ArrayList<Case> ca = new ArrayList<Case>();
		  //initialisation des cases
		  for (int i=0; i<40; i++){
			 // System.out.println("la taille du panneau est "+ this.height+ " et "+ this.width);
			   ca.add(new Case("red", 40, i));	
			   ca.get(i).CaseColor(); //remplit la couleur d'une case au début
			   ca.get(i).setHeight(this.height/10);
			   ca.get(i).setWidth(this.width/10);
			   if (ca.get(i)==null)
				  System.out.println("la case est null");
			  
			  //génération des positions de la case
			  if (i<10){
				 ca.get(i).pos_x=x;
			     ca.get(i).pos_y=y;
			     x= x+(this.width/10);
			     y = 0;
			     
			     //ca.get(i).setPosition(g, x, y);
			  }
			
			 if (i>=10 && i<19){
				 
				 ca.get(i).pos_x=this.width-(this.width/10);/*-this.width/10;
				 ca.get(i).pos_y=ca.get(i-1).pos_y+this.width/10;*/
						  
			  /*}
			  if (i>=19 && i<29){
				  //attention ici on doit décaler de d
				  ca.get(i).pos_x=ca.get(i-1).pos_x-(this.width/10);
				  ca.get(i).pos_y=this.height-this.height/10;
				 
			  }
			  
			 if (i>=29 && i<38){
				  ca.get(i).pos_x=0;
				  ca.get(i).pos_y=ca.get(i-1).pos_y-this.height/10;
			  }
			  
			  
		  }
		
	}*/
	
	/*public JPanel buildplayer(){
		
		
	}*/
		
		
		
	
	

}
