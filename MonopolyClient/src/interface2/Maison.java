package interface2;

import java.awt.Graphics;

public class Maison {
	
	int x;
	int y;
	int Price;
	String owner;
	String color;

	public Maison(int posX, int posY, String col){
		x= posX;
		y= posY;
		this.color = col;
		
	}
	
	public void setOwner(String name){
		this.owner =name;
	}
	
	public String getOwner(){
		return owner;
	}
	
	public void drawHouse(Graphics g){
		g.fillRect(x, y, 20, 25);
	}
	

}
