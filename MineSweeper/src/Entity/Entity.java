package Entity;

import java.awt.image.BufferedImage;

public class Entity {

	public int x, y;
	int imageScale;
	public int imageWidth, imageHeight;
	
	//Boo
	public BufferedImage bUp, bDown, lUp, rUp, shy;
	
	//Block
	public BufferedImage block;
	
	//Mario
	public BufferedImage jump, stand, standL;
	
	//Coin
	public BufferedImage hori, verti;
	
	//Explosion
	public BufferedImage ex1, ex2, ex3;
	
	//Light
	public BufferedImage on, off;
	
	//Flag
	public BufferedImage flag;
	
	//Boom
	public BufferedImage boom1, boom2;
	
	//Image selection by action
	public String action;
	
	//Movement Constants
	public int spriteCounter = 0;
	public int spriteNum = 1;

}
