package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/* This is the main class that contains the main method
 * It will contain the game constructor, run, start, 
 * & stop methods, The Game loop.
 */
public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = 6691247796639148462L;
	
	static final int WIDTH = 800;
	static final int HEIGHT = 600;	
	private Thread thread;
	private boolean running = false;
	private Hud hud;
	private Spawn spawn;
	
	private Handler handler;
	private Random r;
	private Menu menu;

	public enum STATE{
		Menu,
		Game;
	};
	public STATE gameState = STATE.Menu;
	
	
	public Game(){																			//Game Method
		handler = new Handler();															//Create a new Handler
		
		new Window(WIDTH, HEIGHT, "Daniel Verdejo - First Java Game", this);				//Create a new window in which the game will be displayed.	
		hud = new Hud();																	//create a new HUD object
		spawn = new Spawn(handler, hud);
		r = new Random();																	//Generate a random number 
		menu = new Menu();
		long wait = 200, wait2 = 500;														//Instantiate some variable
		int noOfEnemies = 0;
		
		if(gameState == STATE.Game){
			
			handler.addObject(new Player(50,250,Identity.Player,handler));						//This will add an object of type Player.
			
			this.addKeyListener(new Controller(handler));										// This will listen for keyEvents from the Controller 
			
			for(int i = 0;	i <= 1000;	i++){													//Begin a loop 
				switch(hud.getWave()){															//using a switch we will dictate how many enemies, and the wait
				case 1:
					noOfEnemies = 3;
					break;
				case 2:
					noOfEnemies = 6;
					break;
				case 3:
					noOfEnemies = 9;
					break;
				case 4:
					noOfEnemies = 12;
					break;
				case 5:
					noOfEnemies = 15;
					wait = 150;
					wait2 = 475;
					break;
				case 6:
					noOfEnemies = 18;
					break;
				case 7:
					noOfEnemies = 21;
					break;
				case 8:
					noOfEnemies = 24;
					break;
				case 9:
					noOfEnemies = 27;
					break;
				case 10:
					noOfEnemies = 30;
					wait = 100;
					wait2 = 425;
					break;
				case 11:
					noOfEnemies = 40;
					wait = 75;
					wait2 = 400;
					break;
				case 12:
					noOfEnemies = 50;
					wait = 65;
					wait2 = 375;
					break;
				case 13:
					noOfEnemies = 60;
					wait2 = 325;
					break;
				case 14:
					noOfEnemies = 100;
					wait2 = 250;
					break;
				}//switch get the wave dictate the number of enemies
					
				for(int j = 0;	j < noOfEnemies;	j++){
					
					for(int l = 0;	l < handler.object.size();	l++){
						GameObject obj1  = handler.object.get(l);
						if(obj1.getId() != Identity.Player){
							obj1.setVelX(obj1.getVelX() - 1);			
						}
					}
					
					if(hud.getWave() > 10){
						handler.addObject(new Enemy2(800, r.nextInt(HEIGHT), Identity.Enemy, handler));
					}
					
					int y = r.nextInt(HEIGHT);												// y will give the enemy objects a random value for height
					
					
					handler.addObject(new Enemy(800, y, Identity.Enemy, handler));
	
					try {
						Thread.sleep(wait);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}//try / catch
					/* In the following code what we will do line be line is:
					 * 1 - loop setting k to the value of the object list -1, it must be >= 0, decrement.
					 * 2 - create a temporary object of type GameObject it will equal the index k in the object list.
					 * 3 - if the temporary object does not have the identity PLAYER.
					 * 4 - Set x to the temporary objects x value, using a get method.
					 * 5 - If x is < 10 
					 * 6 - Remove the temporary object using the removeObject() method from class Handler.
					 */
		            for (int k = handler.object.size()-1; k>= 0; k--){
		                GameObject tmpObj = handler.object.get(k);
		                if (tmpObj.getId() != Identity.Player){
		                	int x = tmpObj.getX();
		                	if (x < 10){
		                       this.handler.removeObject(tmpObj);
		                	}//if x < 10
		                }// if id != player
		            }//for
				}//inner for
	
				try {
					Thread.sleep(wait2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}//try / catch
				
				// We will check to see if our players health is 0
				// If it is we will remove them from the game
				if(Hud.HEALTH == 0){
	            	for(int j = 0; j < handler.object.size(); j++){
	        			GameObject tmpObj = handler.object.get(j);
	        			if(tmpObj.getId() == Identity.Player){		
	        				this.handler.removeObject(tmpObj);
	        			}//if
	            	}//for
	            }//if
			}//GameState game
		}//Outer For loop
	}//Game 
	
	public synchronized void start(){														//Start Method
		thread = new Thread(this); 															// initialise the thread and this will refer 
		thread.start();																		// to our game instance; start the thread.
		running = true;																					
	}//Start
	
	public synchronized void stop(){														//Stop Method
		try{
			thread.join();																	// This will kill the thread if not running
			running = false;
		}catch(Exception e){
			e.printStackTrace(); 															// this will display an error if here is any issues
		}
	}//stop
	
	/* Game loop logic Information 
	 * The game loop will run, render, update any changes it will
	 * also set a constant controlled speed which the game operates.
	 * The amountOfTicks defines how many ticks per second, with
	 * ticks set to 60 this means tick() will be called 60 times / second
	 * delta is the time difference in nano seconds between
	 * system.nanoTime(now) and the last tick().
	 * If this goes above one, tick() is called and delta is deremented by 1
	 */
	public void run(){																		// Run method Instantiate all variables required
		this.requestFocus();																// For the game loop - a mathematical equation 
		long lastTime = System.nanoTime();													// also request the focus of windows to allow instant input
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		
		while(running){ 																	// Game loop while running remains true
			long now = System.nanoTime();													// Create a time called now		
			delta += (now - lastTime) / ns;													// To Delta add (now minus lastTime) divided by ns
			lastTime = now;																	// lastTime will be set to now
																							//the above lines controls the speed which the game runs
			while(delta >= 1){																// Inner loop while delta is greater than or equals 1
				tick();																		// Call the tick method
				delta--;																	// decrement delta
			}//inner while 
																							// Above will call a tick if delta is >=1 and then decrement delta by 1
			if(running){																	
				render();																	
			}//if running call render
			frames++;																		// increment frames by 1
			
			if(System.currentTimeMillis() - timer > 1000){									// If the currentTime minus the timer is greater than 1000
				timer +=1000;																// add 1000 onto the timer
				//System.out.println("FPS: " + frames);
				// display the Frames per second
				frames = 0;																	// set the frames to 0
			}//Game loop while running
			
		}
		stop();																				// call the stop method 
	}//Run

	
	private void tick(){																	//tick Method tick updates information to handler and hud
		handler.tick();
	
		if(gameState == STATE.Game){
			hud.tick();
			spawn.tick();
		}else if(gameState == STATE.Menu){
			menu.tick();
		}

		
	//	if(gameState)
	}// tick 
	
	private void render(){																	//Render Method
		BufferStrategy buff = this.getBufferStrategy();										//create a bufferStrategy object
		if(buff == null){																	// if its = null
			this.createBufferStrategy(3);													
			return;
		}
		
		Graphics g = buff.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		handler.render(g);
		if(gameState == STATE.Game){
			hud.render(g);
		}else if(gameState == STATE.Menu){
			menu.render(g);
		}
		

		
		g.dispose();
		buff.show();
		
	}// render
	
	public static int clamp(int var, int min, int max){										//Clamp Method
		if (var >= max){
			return var = max;
		}else if (var <= min){
			return var = min;
		}else {
			return var;
		}//This will stop our player object from leaving the window
	}
	
	public static void main (String [] args){												//Main Method

		new Game();																			// This will call the constructor 
	
		
	}//main
}//class
