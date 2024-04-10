package object;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import mariosweeper.MainWindow;

public class SuperObject {
	
	public BufferedImage image;
	public String name;
	public int x, y;
	
	public void draw(Graphics2D g2, MainWindow mw) {
		g2.drawImage(image, x, y, MainWindow.tileSize, MainWindow.tileSize, null);
	}

}
