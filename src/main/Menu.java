package main;

/* This is the menu Class
 * It is currently not in use in the java project
 * due to runtime issues. If you would like to 
 * view the menu change public STATE gameState = STATE.Game;
 * to public STATE gameState = STATE.Menu;
 * on line 34 in Class Game. Thank you.
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import main.Game.STATE;



public class Menu extends MouseAdapter {
	
	private Game game;
	private Handler handler;
	private Hud hud;
	
	public Menu(Game game, Handler handler){
		this.game = game;
		this.handler = handler;
		
	}
	
	public void mousePressed(MouseEvent e){
		int mx = e.getX();
		int my = e.getY();
		
		if(mouseOver(mx,my, 32, 150, 200, 64)){
			game.gameState = STATE.Game;
		}
		if(mouseOver(mx,my,32, 250, 200, 64)){
			System.exit(1);
		}
	}//key press
	public void mouseReleased(MouseEvent e){
		
	}// key released
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height){
		if(mx > x && mx < x + width){
			if(my > y && my < y + height){
				return true;
			}else return false;
		}else return false;
	}

	
	public void render(Graphics g){
		
		Font fn = new Font("arial",1,50);
		Font fn2 = new Font("arial",1, 60);
		Font fn3 = new Font("arial", 1, 30);
		
		g.setFont(fn2);
		g.setColor(Color.red);
		g.drawString("Space Explorer", 170, 64);
		g.setColor(Color.GREEN);
		g.setFont(fn);
		g.drawString("Menu", 50, 128);
		g.drawString("Play", 64, 200);
		g.drawRect(32, 150, 200, 64);
		g.drawString("Quit", 64, 300);
		g.drawRect(32, 250, 200, 64);
		
		
		g.setColor(Color.yellow);
		g.drawString("How to play",350 , 128);
		g.drawRect(256, 150, 500, 128);
		
		g.setFont(fn3);
		g.drawString("You died! :(", 200, 200);
		g.drawString("Score: " + hud.getScore(), 200, 250);
	}
	public void tick(){
		
	}

}
