package Entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import mariosweeper.KeyHandler;
import mariosweeper.MainWindow;

public class Boo extends Entity{
	
	MainWindow mw;
	KeyHandler keyH;
	
	public Boo(MainWindow mw, KeyHandler keyH) {

		this.mw = mw;
		this.keyH = keyH;
		
		setDefaultValues();
		getBooImage();
	}
	
	public void getBooImage() {
		
		try {	
			bUp = ImageIO.read(getClass().getResourceAsStream("/Boo/Boo_B_up.png"));
			bDown = ImageIO.read(getClass().getResourceAsStream("/Boo/Boo_B_down.png"));
			lUp = ImageIO.read(getClass().getResourceAsStream("/Boo/Boo_R_up.png"));
			rUp = ImageIO.read(getClass().getResourceAsStream("/Boo/Boo_L_up.png"));
			shy = ImageIO.read(getClass().getResourceAsStream("/Boo/Boo_Shy.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void setDefaultValues() {

		imageScale = 4;
		imageWidth = MainWindow.tileSize*imageScale;
		imageHeight = MainWindow.tileSize*imageScale;
		x = MainWindow.SCREEN_WIDTH/2-imageWidth/2;
		y = MainWindow.SCREEN_HEIGHT*11/24-imageHeight/2;
		action = "rest";
	}
	
	public void setPlayScreenValues() {

		imageScale = 2;
		imageWidth = MainWindow.tileSize*imageScale;
		imageHeight = MainWindow.tileSize*imageScale;
		x = MainWindow.SCREEN_WIDTH/6-imageWidth/2;
		y = MainWindow.SCREEN_HEIGHT*3/4-imageHeight/2;
		action = "rest";
	}

	public void update() {
		
		if (mw.gameState == mw.titleState) {
			setDefaultValues();
			if(keyH.upPressed==true) {
				action = "up";
			}
			if(keyH.downPressed==true) {
				action = "down";
			}
			if(keyH.leftPressed==true) {
				action = "left";
			}
			if(keyH.rightPressed==true) {
				action = "right";
			}
			if(keyH.hPressed==true) {
				action = "shy";
			}
		}
		if (mw.gameState==mw.playState) {
			setPlayScreenValues();
			if(keyH.hPressed==true) {
				action = "shy";
				mw.ui.showMessage("Nice!");
			}
			needHide();
		}
	}
	
	public void draw(Graphics2D g2) {

		BufferedImage image = bDown;
		switch(action) {
		case "rest":
			image = bDown;
			break;
		case "up":
			image = bUp;
			break;
		case "down":
			image = bUp;
			break;
		case "left":
			image = lUp;
			break;
		case "right":
			image = rUp;
			break;
		case "shy":
			image = shy;
			break;
		}

		g2.drawImage(image, x, y, imageWidth, imageHeight, null);

	}
	
	public void needHide() {
		mw.ui.showMessage("Hide to avoid losing coins!");
		if (action == "shy") {

		}
	}

}
