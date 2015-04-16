package interface2;



import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class pion extends JPanel{
	String color;
	int rayon;
	public int pos_y;
	public int pos_x;
	public int pos=0;
	Color co;
	Panel_Jeu pan;
	Graphics g;
	private Graphics2D g2d;
	public int id=0;
	 
	//Case2[] position = new Case2[41];

	public pion(String color, int rayon, Panel_Jeu pj){
		
		this.color= color;
		this.rayon = 8;
		
		this.drawPion();
		
	}
	
	public void setPosition_X(Graphics g,int x){
		this.pos_x= x;
		g.fillOval(pos_x, pos_y, rayon, rayon);
		
	}
	
	public void setPosition_Y(Graphics g, int y){
		this.pos_y=y;
		g.setColor(this.co);
		g.fillOval(pos_x, pos_y, rayon, rayon);
		
	}
	
	public void setPosition(Graphics g,int x, int y){
		this.pos_y=y;
		this.pos_x=x;
		g.setColor(this.co);
		g.fillOval(pos_x, pos_y, rayon, rayon);
	}
	
	//Déplacement du pion selon le nombre de case
	public void movePion(int nbre){
		this.pos=(this.pos+nbre)%41;
		
	}
	
	
	public int getPosition_x(){
		return this.pos_x;
		
	}
	
	public int getPosition_y(){
		return this.pos_y;
		
	}
	
	public void drawPion(){
		
		switch (color){
		 case "red":
			 co= Color.red;
			// System.out.println("La couleur est rouge");
			 
			 break;
		 case "blue":
			 co=Color.blue;
			 //System.out.println("La couleur est bleue");
			break;
		 case "green":
			 //System.out.println("La couleur est Verte");
			 co = Color.green;
				break;
			 
		 
		}
			
	}

	public void getId(int i) {
		// TODO Auto-generated method stub
		this.id= i;
		
	}
	
	

}
