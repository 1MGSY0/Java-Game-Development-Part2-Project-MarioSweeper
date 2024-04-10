package mariosweeper;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.*;

import Entity.Coin;
import gameplay.GameBoardPanel;

import java.text.*;

import object.OBJ_Coin;

public class UI {
	
	MainWindow mw;
	Graphics2D g2;
	GameBoardPanel gbp;
	
	//FONT
	Font maruMonica;
	
	//IMAGES
	BufferedImage coinImage;
	
	//MESSAGES
	public boolean messageOn = false;
	public String message = "";
	int messageCounter;
	
	//TIMER
	public double playTime;
	DecimalFormat dFormat = new DecimalFormat("#0.00");
	
	//STATUS
	public int titleMenuCmd = 1;
	public int pauseMenuCmd = 0;
	public int resultMenuCmd = 0;
	public int lvlCmdNum = 0;
	public boolean gameWon = false;
	
	public UI(MainWindow mw) {
		
		this.mw = mw;
		
		//FONT
		try {
			InputStream is = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
			maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//UI IMAGES
		Coin coin = new Coin(mw);
		coinImage = coin.hori;
		
	}
	
	public void draw(Graphics2D g2) {
		
		this.g2 = g2;
		
		g2.setFont(maruMonica);
		g2.setColor(Color.white);
		
		//TITLE STATE
		if (mw.gameState == mw.titleState) {
			drawTitleScreen();
		}
		//PLAY STATE
		if(mw.gameState == mw.playState) {
			drawPlayScreen();
		}
		
		//PAUSE STATE
		if(mw.gameState == mw.pauseState) {
			drawPauseScreen();
		}
		
		//HELP STATE
		if(mw.gameState == mw.helpState) {
			drawHelpScreen();
		}
		
		//SETTING STATE
		if(mw.gameState == mw.settingState) {
			drawSettingScreen();
		}
		
		//SETTING STATE
		if(mw.gameState == mw.resultState) {
			drawResultScreen();
		}
	}
	
	public void drawTitleScreen() {
		
		String text;
		int xOffset, yOffset;
		
		//TITLE NAME
		text = "MARIOSWEEPER";
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, MainWindow.tileSize*9/4));
		g2.setColor(Color.red);
		xOffset = 0;
		yOffset = -MainWindow.tileSize*2;
		drawStringfromCenter(text, xOffset+MainWindow.tileSize/8, yOffset+MainWindow.tileSize/8);
		g2.setColor(Color.white);
		drawStringfromCenter(text, xOffset, yOffset);
		
		//MENU
		text = "MARIO'S IQ";
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, MainWindow.tileSize*2/5));
		if (titleMenuCmd == 0) {
			g2.setColor(Color.red);
		}else {g2.setColor(Color.white);}
		yOffset = MainWindow.tileSize*2;
		drawStringfromCenter(text, xOffset, yOffset);
		
		
		text = "<  " + getSelectedLevel(lvlCmdNum) + "  >";
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, MainWindow.tileSize*3/4));
		yOffset = MainWindow.tileSize*3;
		drawStringfromCenter(text, xOffset, yOffset);
		
		
		text = "START";
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, MainWindow.tileSize*5/4));
		if (titleMenuCmd == 1) {
			g2.setColor(Color.red);
		}else {g2.setColor(Color.white);}
		yOffset = MainWindow.tileSize*89/20;
		drawStringfromCenter(text ,xOffset, yOffset);
		
		text = "HELP";
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, MainWindow.tileSize/2));
		if (titleMenuCmd == 2) {
			g2.setColor(Color.red);
		}else {g2.setColor(Color.white);}
		xOffset = - MainWindow.tileSize - MainWindow.tileSize/4;
		yOffset = MainWindow.tileSize*5 - MainWindow.tileSize/4;
		drawStringfromCenter(text, xOffset, yOffset);

		text = "SETTING";
		if (titleMenuCmd == 3) {
			g2.setColor(Color.red);
		}else {g2.setColor(Color.white);}
		xOffset = MainWindow.tileSize;
		drawStringfromCenter(text, xOffset, yOffset);
		
		text = "QUIT";
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, MainWindow.tileSize/2));
		if (titleMenuCmd == 4) {
			g2.setColor(Color.red);
		}else {g2.setColor(Color.white);}
		xOffset = MainWindow.tileSize*7;
		yOffset = MainWindow.tileSize*23/4;
		drawStringfromCenter(text, xOffset, yOffset);
		
	}
	
	public void drawPlayScreen() {
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,MainWindow.tileSize/2));
		g2.setColor(Color.white);
		g2.drawImage(coinImage, MainWindow.tileSize*3/2, MainWindow.tileSize/2, MainWindow.tileSize*3/4, MainWindow.tileSize*3/4,null);
		g2.drawString(" X " + GameBoardPanel.coinCollected, MainWindow.tileSize*2, MainWindow.tileSize);
		
		//TIME
		playTime += (double)1/60; //display time per second
		g2.drawString("Time:"+dFormat.format(playTime), MainWindow.tileSize*25/2, MainWindow.tileSize);
		
		//MESSAGE
		if(messageOn == true) {
			
			g2.setFont(g2.getFont().deriveFont(Font.PLAIN,MainWindow.tileSize/2));
			drawStringfromCenter(message,0, -MainWindow.tileSize*5);
			
			messageCounter++;
			if(messageCounter > 120) {
				messageCounter = 0;
				messageOn = false;
			}
		}
	}
	
	public void drawPauseScreen() {
		
		String text;
		int xOffset, yOffset;
		
		g2.setBackground(Color.black);
		
		text = "PAUSED";
		xOffset = 0;
		yOffset = 0;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, MainWindow.tileSize*2));
		drawStringfromCenter(text, xOffset, yOffset);
		
		//MENU
		text = "RESUME";
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, MainWindow.tileSize*3/4));
		if (pauseMenuCmd == 0) {
			g2.setColor(Color.red);
		}else {g2.setColor(Color.white);}
		yOffset = MainWindow.tileSize*2;
		drawStringfromCenter(text, xOffset, yOffset);
		
		
		text = "RESTART";
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, MainWindow.tileSize*3/4));
		if (pauseMenuCmd == 1) {
			g2.setColor(Color.red);
		}else {g2.setColor(Color.white);}
		yOffset = MainWindow.tileSize*3;
		drawStringfromCenter(text, xOffset, yOffset);
		
		
		text = "QUIT";
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, MainWindow.tileSize*3/4));
		if (pauseMenuCmd == 2) {
			g2.setColor(Color.red);
		}else {g2.setColor(Color.white);}
		yOffset = MainWindow.tileSize*4;
		drawStringfromCenter(text ,xOffset, yOffset);
		
		text = "HELP";
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, MainWindow.tileSize/2));
		if (pauseMenuCmd == 3) {
			g2.setColor(Color.red);
		}else {g2.setColor(Color.white);}
		xOffset = - MainWindow.tileSize - MainWindow.tileSize/4;
		yOffset = MainWindow.tileSize*5 - MainWindow.tileSize/4;
		drawStringfromCenter(text, xOffset, yOffset);

		text = "SETTING";
		if (pauseMenuCmd == 4) {
			g2.setColor(Color.red);
		}else {g2.setColor(Color.white);}
		xOffset = MainWindow.tileSize;
		drawStringfromCenter(text, xOffset, yOffset);
		
	}
	
	public void drawHelpScreen() {
		
		String text;
		int xOffset, yOffset;
		
		g2.setBackground(Color.black);
		
		text = "HELP";
		xOffset = 0;
		yOffset = -MainWindow.tileSize*5;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, MainWindow.tileSize*2));
		drawStringfromCenter(text, xOffset, yOffset);
		
		text = "CONTROLS";
		yOffset = -MainWindow.tileSize*4;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, MainWindow.tileSize/2));
		drawStringfromCenter(text, xOffset, yOffset);
		
		text = "Navigation: W,A,S,D or Direction Key"
				+ "	Continue: ENTER"
				+ "	Exit: ESCAPE"
				+ "	Pause: P"
				+ "	Hide: SPACE";
		yOffset += MainWindow.tileSize/2;
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, MainWindow.tileSize/4));
		drawStringfromCenter(text, xOffset, yOffset);
		
	}
	
	public void drawSettingScreen() {
		
		String text;
		int xOffset, yOffset;
		
		g2.setBackground(Color.black);
		
		text = "SETTING";
		xOffset = 0;
		yOffset = 0;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, MainWindow.tileSize*2));
		drawStringfromCenter(text, xOffset, yOffset);
	}
	
	public void drawResultScreen() {
		
		String text;
		int xOffset, yOffset;
		
		text = "Score: " + GameBoardPanel.score ;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,MainWindow.tileSize/2));
		g2.setColor(Color.white);				
		xOffset = 0;
		yOffset = MainWindow.tileSize;
		drawStringfromCenter(text, xOffset, yOffset);
		
		text = "Clear time: " + dFormat.format(playTime) + " s";
		yOffset = 0;
		drawStringfromCenter(text, xOffset, yOffset);
		
		if (gameWon) {
			text = "CLEARED!";
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,MainWindow.tileSize*2));
			g2.setColor(Color.yellow);	
			yOffset = -MainWindow.tileSize;
			drawStringfromCenter(text, xOffset, yOffset);
		}else {
			text = "GAME OVER!";
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,MainWindow.tileSize*2));
			g2.setColor(Color.yellow);	
			yOffset = -MainWindow.tileSize;
			drawStringfromCenter(text, xOffset, yOffset);
		}
		
		
		//RESULT MENU
		text = "REPLAY";
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, MainWindow.tileSize*3/4));
		if (resultMenuCmd == 0) {
			g2.setColor(Color.red);
		}else {g2.setColor(Color.white);}
		yOffset = MainWindow.tileSize*3;
		drawStringfromCenter(text, xOffset, yOffset);
			
		text = "TO TITLE";
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, MainWindow.tileSize*3/4));
		if (resultMenuCmd == 1) {
			g2.setColor(Color.red);
		}else {g2.setColor(Color.white);}
		yOffset = MainWindow.tileSize*4;
		drawStringfromCenter(text, xOffset, yOffset);

	}
	
	public String getSelectedLevel(int lvlNum) {
		
		String selectedLevel = "LOW";
		
		switch(lvlNum) {
		case 0:
			selectedLevel = "LOW";
			break;
		case 1:
			selectedLevel = "MEDIUM";
			break;
		case 2:
			selectedLevel = "HIGH";
			break;
		case 3:
			selectedLevel = "SUPER";
			break;
		}
		return selectedLevel;
	}
	
	public void showMessage(String text) {
		
		message = text;
		messageOn = true;
	}
	
	public int getTextLength(String text) {
		
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		return length;
	}
	
	public int getTextHeight(String text) {
		
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getHeight();
		return length;
	}
	
	public int getXforCenteredText(String text) {
		
		int length = getTextLength(text);
		int x = MainWindow.SCREEN_WIDTH/2 - length/2;
		return x;
	}
	
	public int getYforCenteredText(String text) {
		
		int length = getTextHeight(text);
		int y = MainWindow.SCREEN_HEIGHT/2 - length/2;
		return y;
	}
	
	public void drawStringfromCenter(String text, int xOffset, int yOffset) {
		int x, y;
		x = getXforCenteredText(text) + xOffset;
		y = getYforCenteredText(text) + yOffset;
		g2.drawString(text, x, y);
	}
	
}
