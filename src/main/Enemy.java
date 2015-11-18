package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Enemy extends GameObject {
	
	private Handler handler;

	public Enemy(int x, int y, Identity id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		velX = -10;
		velY = 0;
		
	}
public Rectangle getBounds(){
	return new Rectangle(x,y,16,16);
}

public void tick() {

	x += velX;
	y += velY;
	
	x = Game.clamp(x, -5, Game.WIDTH - 20);
	y = Game.clamp(y, -5, Game.HEIGHT - 48);
	
	//handler.addObject(new Trail(x,y, Identity.Trail, Color.red,16,16, 0.08f, handler));

}//tick




public void render(Graphics g) {
	
	Graphics2D g2 = (Graphics2D) g;
	
	g2.setColor(Color.RED);
	g2.draw(getBounds());
	
	g.fillRect(x, y, 16, 16);
}// render



}
