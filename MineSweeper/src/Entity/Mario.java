package Entity;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import gameplay.GameBoardPanel;
import mariosweeper.KeyHandler;
import mariosweeper.MainWindow;


public class Mario extends Entity{
	
	MainWindow mw;
	KeyHandler keyH;
	Random random = new Random();
	public int timeCounter1 = 0;
	public int timeCounter2 = 0;
	public boolean needHide = false;
	public int flipTime = 2000;
	
	public Mario(MainWindow mw, KeyHandler keyH) {

		this.mw = mw;
		this.keyH = keyH;
		setDefaultValues();
		getMarioImage();
	}
	
	public void getMarioImage() {
		
		try {	
			jump = ImageIO.read(getClass().getResourceAsStream("/Mario/Mario_JumpL.png"));
			stand = ImageIO.read(getClass().getResourceAsStream("/Mario/Mario_StandingL.png"));
			standL = ImageIO.read(getClass().getResourceAsStream("/Mario/Mario_Standing.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void setDefaultValues() {

		imageScale = 2;
		imageWidth = MainWindow.tileSize*imageScale;
		imageHeight = MainWindow.tileSize*imageScale;
		x = MainWindow.SCREEN_WIDTH*5/6-imageWidth/2;
		y = MainWindow.SCREEN_HEIGHT*3/4-imageHeight/2;
		action = "rest";
	}

	public void update() {
		
		if (mw.gameState==mw.playState) {
			if(needHide==true) {
				
				if(keyH.hPressed==true) {
					timeCounter1++;	
					if(timeCounter1 > 200) {
						timeCounter1 = 0;timeCounter2 = 0;
						flipTime = random.nextInt(1000);
						action = "rest";
						needHide = false;
					}
				}else if (keyH.hPressed==false){
					if(timeCounter2 > 1500) {
						action = "jump";
						if (GameBoardPanel.coinCollected>0) {
							GameBoardPanel.coinCollected--;
						}	
						timeCounter2++;
					}
				}
				
			}
			if(needHide==false) {
				if (flipTime < timeCounter2) {
					action = "look";
					needHide = true;
				}else if (flipTime>timeCounter2) {
					timeCounter2++;
				}
			}
			
		}
	}
	
	public void draw(Graphics2D g2) {

		BufferedImage image = standL;
		switch(action) {
		case "rest":
			image = stand;
			break;
		case "look":
			image = standL;
			break;
		case "jump":
			image = jump;
			break;
		}

		g2.drawImage(image, x, y, imageWidth, imageHeight, null);

	}
	
}

