package interface2;

import java.awt.Color;
import java.awt.Graphics;

public class Terrain {
	int x;
	int y;
	Graphics g;
	
	public Terrain( int valX, int valY){
		
			
		x= valX-(65/2);
		y = valY-(50/2);
				
	}
		
	public void drawTerrain(Graphics g){
		g.drawRect(x, y, 65, 50);
		//g.fillRect(x, y, 65, 50);
		
	}
		
	
}
