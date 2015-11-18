package main;
/* In this class we will dictate the waves by using the score.
 * This will then have a knock on effect in our game class 
 * when spawning enemies, and progressing the waves.
 */
public class Spawn {
	
	private Handler handler;
	private Hud hud;
	
	private int scoreRecord;

	public Spawn(Handler handler, Hud hud){
		this.handler = handler;
		this.hud = hud;
	}//spawn
	
	public void tick(){
		scoreRecord++;
		if(scoreRecord >= 1000){
			scoreRecord = 0;
			hud.setWave(hud.getWave()+1);
		}//if 
	}//tick
}//class
