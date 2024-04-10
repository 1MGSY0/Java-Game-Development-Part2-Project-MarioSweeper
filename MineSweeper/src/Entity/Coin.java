package Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import mariosweeper.KeyHandler;
import mariosweeper.MainWindow;

public class Coin extends Entity{
		
	MainWindow mw;
	
	public Coin(MainWindow mw) {

		this.mw = mw;
		
		setDefaultValues();
		getCoinImage();
	}
	
	public void getCoinImage() {
		
		try {	
			hori = ImageIO.read(getClass().getResourceAsStream("/Coin/Coin_1.png"));
			verti = ImageIO.read(getClass().getResourceAsStream("/Coin/Coin_2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void setDefaultValues(){
		
		imageScale = 1;
		imageWidth = MainWindow.tileSize*imageScale;
		imageHeight = MainWindow.tileSize*imageScale;
		x = MainWindow.SCREEN_WIDTH/4-imageWidth/2;
		y = MainWindow.SCREEN_HEIGHT/4-imageHeight/2;
	}
	
	public void update() {
		spriteCounter++;
		if(spriteCounter > 15) {
			if(spriteNum == 1) {
				spriteNum = 2;
			}else if(spriteNum ==2) {
				spriteNum = 1;
			}
			spriteCounter = 0;
		}
	}
	
	public void draw(Graphics2D g2) {
		BufferedImage image = hori;
		if (spriteNum == 1) {
			image = hori;
		}
		if(spriteNum == 2) {
			image = verti;
		}
		
		g2.drawImage(image, x, y, imageWidth, imageHeight, null);
	}
}
