package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Hud {
	
	public static int HEALTH = 100;
	
	private int score = 0;
	private int wave = 1;
	private int health = 255;
	
	public void tick(){
		
		HEALTH = Game.clamp(HEALTH, 0, 100); 									// set the min and max val - also when collision detected will make health bar smaller
		
		score++;
	
	}
	public void setScore(int score){
		this.score = score;
	}//set score
	public int getScore(){
		return score;
	}//get score
	public int getWave(){
		return wave;
	}
	public void setWave(int wave){
		this.wave = wave;
	}
	
	public void render(Graphics g){
		g.setColor(Color.DARK_GRAY);
		g.fillRect(8, 516, 200, 32);
		g.setColor(Color.green);
		g.fillRect(8, 516, HEALTH * 2, 32);
		g.setColor(Color.orange);
		g.drawRect(8, 516, HEALTH * 2,	32);
		g.setColor(Color.WHITE);
		g.drawRect(8, 516, 200, 32);
		g.drawString("Score: " + score, 16, 16);
		g.drawString("Wave: " + wave, 16, 32);	
		g.drawString("Use the W A S D keys to move & avoid incoming blocks",196,16);
		
		if (HEALTH == 0){
			Font fn = new Font("arial",1,50);
			Font fn2 = new Font("arial",1,30);
			g.setFont(fn);
			g.drawString("You died! :( ", 200, 200);
			g.setFont(fn2);
			g.drawString("Esc to exit", 150, 300);
		}
		
		
	}
}
