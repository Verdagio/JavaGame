package main;

import java.awt.Graphics;
import java.util.LinkedList;

/* This class will update and render all of our objects
 * this will loop through all objects 
 * individually update and render them to the screen
 */

public class Handler { 
	
	LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	
	public void tick(){
		for(int i = 0; i < object.size(); i++){
			GameObject tempObject = object.get(i);
			
			tempObject.tick();
		}
	}//Tick
	
	public void render(Graphics g){
		for (int i = 0; i < object.size(); i++){
			GameObject tempObject = object.get(i);
			
			tempObject.render(g);
		}
	}//Render
	
	public void addObject(GameObject obj){
		this.object.add(obj);
	}//add object
	public void removeObject(GameObject obj){
		this.object.remove(obj);
	}//remove object
	
	

}
