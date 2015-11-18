package main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Controller extends KeyAdapter {
	
	private Handler handler;
	private boolean [] keyDown = new boolean[4];
	
	public Controller(Handler handler){
		this.handler = handler;
		
		for(int j = 0; j < 4; j++){
			keyDown[j] = false;
		}//for
	}//controller
	
	public void keyPressed(KeyEvent e){
		
		int key = e.getKeyCode();
		
		System.out.println(key);
		for(int i = 0; i < handler.object.size(); i++){ // This will loop through objects and select the id of player
			
				GameObject tempObject = handler.object.get(i);
				
				if(tempObject.getId() == Identity.Player){
					//Key event for player 1
					
					if(key == KeyEvent.VK_W) { tempObject.setVelY(-7);	keyDown[0] =true; }
					if(key == KeyEvent.VK_S) { tempObject.setVelY(7);	keyDown[1] =true; }
					if(key == KeyEvent.VK_A) { tempObject.setVelX(-7);	keyDown[2] =true; }
					if(key == KeyEvent.VK_D) { tempObject.setVelX(7);	keyDown[3] =true; }
				}
		}
		
		if(key == KeyEvent.VK_ESCAPE){
			System.exit(1);
		}

	}//key pressed
	
	/* The below method will handle release events
	The event is passed to every KeyListener or KeyAdapter 
	object which registered to receive such events using 
	the component's addKeyListener method. 
	(KeyAdapter objects implement the KeyListener interface.) 
	Each such listener object gets this KeyEvent when the event occurs. 
	 */
	public void keyReleased(KeyEvent e){
		
		int key = e.getKeyCode();
		
		System.out.println(key);
		for(int i = 0; i < handler.object.size(); i++){ // This will loop through objects and select the id of player
			
				GameObject tempObject = handler.object.get(i);
				
				if(tempObject.getId() == Identity.Player){
					//Key event for player 1
					
					if(key == KeyEvent.VK_W) keyDown[0] = false; 
					if(key == KeyEvent.VK_S) keyDown[1] = false; 
					if(key == KeyEvent.VK_A) keyDown[2] = false; 
					if(key == KeyEvent.VK_D) keyDown[3] = false; 
					
					if(!keyDown[0] && !keyDown[3]){
						tempObject.setVelY(0);
					}
					if(!keyDown[1] && !keyDown[2]){
						tempObject.setVelX(0);
					}				}
		}
	}//Key Released 

}
