package main;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

// This class' purpose is purely to create a window that will be displayed

public class Window extends Canvas {

	private static final long serialVersionUID = -4810618286807932601L;
	
	/* Below we will set up our frame
	 * A few functions we will set up is the dimensions 
	 * the preferred size, max & min size, close operation
	 * set re-sizable to false and visible to true
	 * We will also add our a game object into the frame
	 */
	public Window(int width, int height, String title, Game game){
		JFrame frame = new JFrame (title);
		
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);
		frame.toFront();
		game.start();
		
	}//Window

}
