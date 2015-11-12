package main;

import java.awt.Graphics;
import java.awt.Rectangle;

/* This will handle all game objects,
 *  methods that all 
 * objects in the game have will be inherited 
 * from this abstract class in the Handler class.
 */

public abstract class GameObject {

	protected int x, y;								//These variables can only be accessed by class that extends GameObject
	protected Identity id;
	protected int velX, velY;
	
	public GameObject(int x, int y, Identity id){
		this.x = x;
		this.y = y;
		this.id = id;
		
	}//game object constructor
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	
	/////// GET & SET \\\\\\\\\
	// These will allow to easily get or set our parameters
	
	// GET AND SET X
	public void setX(int x){
		this.x = x;
	}
	public int getX(){
		return x;
	}
	
	//GET AND SET Y
	public void setY(int y){
		this.y = y;
	}
	public int getY(){
		return y;
	}
	
	//GET AND SET IDENTITY
	public void setId(Identity id){
		this.id = id;
	}
	public Identity getId(){
		return id;
	}
	
	//GET AND SET VELOCITY X
	public void setVelX(int velX){
		this.velX = velX;
	}
	public int getVelX(){
		return velX;
	}
	
	//GET AND SET VELOCITY Y
	public void setVelY(int velY){
		this.velY = velY;
	}
	public int getVelY(){
		return velY;
	}
}