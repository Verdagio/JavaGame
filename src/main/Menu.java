package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu extends MouseAdapter {
	
	public void mousePressed(MouseEvent e){
		
	}
	public void mouseReleased(MouseEvent e){
		
	}
	public void tick(){
		
	}
	public void render(Graphics g){
		
		Font font = new Font("arial", 1, 50);
		g.setFont(font);
		g.setColor(Color.GREEN);
		g.drawString("Space Explorer", 210, 64);
		g.drawString("Menu", 328, 128);
		
		g.setColor(Color.green);
		g.drawRect(270, 156, 250, 64);
		g.drawString("Play", 342, 205);
		
		g.setColor(Color.green);
		g.drawRect(270, 248, 250, 64);
		g.drawString("Info", 348, 300);
		
		g.setColor(Color.green);
		g.drawRect(270, 340, 250, 64);
		g.drawString("Quit", 342, 390);
	}
}
