package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/* The player class will extend game object
 * this means that player will inherit all
 * of the methods and variables that are within 
 * the abstract game object class
 */

public class Player extends GameObject {
	Handler handler;
	

	public Player(int x, int y, Identity id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
	}//player

	public Rectangle getBounds(){
		return new Rectangle(x,y,32,32);
	}//getBounds
	
	public void tick() {

		x += velX;
		y += velY;
		
		y = Game.clamp(y, 0, Game.HEIGHT - 64);
		x = Game.clamp(x, 0, Game.WIDTH - 38);
		
		handler.addObject(new Trail(x,y, Identity.Trail, Color.orange,16,16, 0.08f, handler));


		collision();
	}//tick
	
	/*In the following lines of code we will detect collision
	 * We will run a for loop that will run through all the objects
	 * We will then create a temporary object that will get each instance of the for loop
	 */
	private void collision(){
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObj = handler.object.get(i);
			
			if(tempObj.getId() == Identity.Enemy){								// Here We use only the temporary Object that is an enemy
																				//Collision code
				if(getBounds().intersects(tempObj.getBounds())){				//if player bounds intersects the Enemy bounds
																				//Trigger a Collision
					Hud.HEALTH -= 5;	

                    this.handler.removeObject(tempObj);
                    
                    

				}//nested if
			}//if
		}//for
	}//collision

	
	public void render(Graphics g) {
		
		Graphics2D g2 = (Graphics2D) g;											//This will cast our 
		
		g.setColor(Color.pink);
		g2.draw(getBounds());
		
		g.setColor(Color.orange);
		g.fillRect(x, y, 32, 32);
	}// render
	
	

}//class
