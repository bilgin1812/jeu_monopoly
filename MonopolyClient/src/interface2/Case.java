package interface2;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.JPanel;



public class Case extends JPanel {
	

	
	//implémentation de la case, dessin du la case et chargement de la couleur

		String color;
		
		public int width;
		public int height;
		public int pos_y;
		public int pos_x;
		Color co;
		int id;
		int nbre_joueur;

		public Case (String color, int rayon, int num, int nbre_joueur){
			this.color= color;
			id = num;
			this.height= 30; //par défaut taille des case =30
			this.width=30;
			this.nbre_joueur = nbre_joueur;
			
		}
		
		public void setPosition_X(Graphics g,int x){
			this.pos_x= x;
			g.fillRect(pos_x, pos_y, this.width, this.width);
			
		}
		
		public void setPosition_Y(Graphics g, int y){
			this.pos_y=y;
			g.setColor(this.co);
			g.fillRect(pos_x, pos_y, this.width, this.width);
		}
		
		public void setPosition(Graphics g,int x, int y){
			this.pos_y=y;
			this.pos_x=x;
			
			g.setColor(this.co);
			g.fillRect(pos_x, pos_y, this.width, this.width);
		}
		
		
		public int getPosition_x(){
			return this.pos_x;
			
		}
		
		public int getPosition_y(){
			return this.pos_y;
			
		}
		
		public void setWidth(int w){
			this.width= w;
		}
		
		public void setHeight(int h){
			this.height= h;
		}
		
		public void setSize(int h, int w){
			this.width= w;
			this.height= h;
		}
		
		public void CaseColor(){
			
			switch (color){
			 case "red":
				 co= Color.red;
				 System.out.println("La couleur est rouge");
				 
				 break;
			 case "blue":
				 co=Color.blue;
				 System.out.println("La couleur est bleue");
				 
				 
				 break;		 
			 
			}
				
		}
		
		public void drawCase(Graphics g){
			g.setColor(this.co);
			
			//System.out.println(" hauteur= "+ this.width + " et pos_x"+ this.pos_x+" et pos_y "+ this.pos_y);
			g.fillRect(pos_x, pos_y, this.width, this.width);
			//this.setPosition(g, this.pos_x, this.pos_y);
			
		}
		
		public void repaintCase(Graphics g){
			g.fillRect(pos_x, pos_y, this.width, this.width);
			
		}
		
		public void goToPos(){
			
			
		}
		
		
		

	}
