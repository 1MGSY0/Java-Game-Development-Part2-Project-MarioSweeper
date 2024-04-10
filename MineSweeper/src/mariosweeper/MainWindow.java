package mariosweeper;

import java.awt.*;
import javax.swing.*;

import Entity.*;
import gameplay.GameBoardPanel;
import object.*;
import tile.TileManager;


public class MainWindow extends JPanel implements Runnable{
	private static final long serialVersionUID = 1L;
	
	GameBoardPanel gbp;
	   
	//SCREEN SETTINGS
	public static final int originalTileSize = 64;
	public static final int scale = 1;
	public final static int tileSize = originalTileSize*scale;
	public static final int maxScreenCol = 16;
	public static final int maxScreenRow = 12;
	public static final int SCREEN_WIDTH = tileSize*maxScreenCol; 
	public static final int SCREEN_HEIGHT = tileSize*maxScreenRow; 
	
	//FPS
	public static final int FPS = 60;
	
	//SYSTEM
	public UI ui = new UI(this);
	public Sound music = new Sound();
	public Sound se = new Sound();
	public KeyHandler keyH = new KeyHandler(this, gbp);
	public TileManager tileM = new TileManager(this);
	public AssetSetter aSetter = new AssetSetter(this);
	Thread gameThread;
	
	//ENTITY OBJECTS
	public Boo boo = new Boo(this, keyH);
	public Mario mario = new Mario(this, keyH);
	public SuperObject obj[] = new SuperObject[10];
	
	//GAME STATE
	public int gameState;
	public int preState;
	public final int titleState = 0;
	public final int playState = 1;
	public final int pauseState = 2;
	public final int helpState = 3;
	public final int settingState = 4;
	public final int resultState = 5;
	
	
	public MainWindow() {
	    
		setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		setBackground(Color.black);
		setDoubleBuffered(true);
		addKeyListener(keyH);
		setFocusable(true);

	}
	
	public void setupGame() {
		
		aSetter.setObject();
		playMusic(0);
		gameState = titleState;
	}
	
	public void startGameThread() {
		
		gameThread = new Thread(this);
		gameThread.start();	
	}

	@Override
	public void run() {
		
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;
					
		while(gameThread != null) {
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime)/drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;
			
			if (delta >= 1) {
				//Update information
				update();
				//Draw updated information
				repaint();
				delta--;
				drawCount++;
			}
			
			if(timer >= 1000000000) {
				System.out.println("FPS:"+ drawCount);
				drawCount = 0;
				timer = 0;
			}
			
		}
	}
		
	public void update() {
	
		if(gameState == titleState) {
			boo.update();
		}
		if(gameState == playState) {
			boo.update();	
			mario.update();
		}
		if(gameState == playState&&Main.displayBoard == false) {
			Main.layeredPane.setLayer(Main.gameBoardPanel, 2);
			Main.displayBoard = true;
		}else if (gameState != playState&&Main.displayBoard == true) {
			Main.layeredPane.setLayer(Main.gameBoardPanel, 0);
			Main.displayBoard = false;
		}

	}
	
	public void paintComponent(Graphics g) {
		//DEBUG
		long drawStart = 0;
		if(keyH.showSystemInfo == true) {
		
		drawStart = System.nanoTime();
		}
		
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		//TITLE STATE
		if (gameState == titleState) {
			
			//ENTITY
			boo.draw(g2);
			
			//UI
			ui.draw(g2);
		}
		
		//PLAY STATE
		if (gameState == playState) {
			//BACKGROUND
			tileM.draw(g2);
			
			//OBJECT
//			for (int i = 0; i < obj.length; i++) {
//				if(obj[i] != null) {
//					obj[i].draw(g2, this);
//				}
//			}
			
			//ENTITY
			boo.draw(g2);
			mario.draw(g2);
			
			//UI
			ui.draw(g2);
		}
		
		//PAUSE STATE
		if (gameState == pauseState) {
			//UI
			ui.draw(g2);
		}
		
		//HELP STATE
		if (gameState == helpState) {
			
			//UI
			ui.draw(g2);
		}
		
		//SETTING STATE
		if (gameState == settingState) {
			
			//UI
			ui.draw(g2);
		}
		
		//RESULT STATE
		if (gameState == resultState) {
			
			//UI
			ui.draw(g2);
		}

		//DEBUG
		if(keyH.showSystemInfo == true) {
			long drawEnd = System.nanoTime();
			long passed = drawEnd - drawStart;
			g2.setColor(Color.white);
			g2.drawString("Draw Time:" + passed, 10, 400);
			System.out.println("Draw Time:" + passed);
		}
		
		
		g2.dispose();
	}
	
	public void playMusic(int i) {
		
		music.setFile(i);
		music.play();
		music.loop();
	}
	
	public void playSelectedLvlMusic(String levelSelected) {
		
		int i = 1;
		switch(levelSelected) {
		case "low":
			i = 1;
			break;
		case "medium":
			i = 2;
			break;
		case "high":
			i = 3;
			break;
		case "extream":
			i = 4;
			break;
		}
		
		music.setFile(i);
		music.play();
		music.loop();
	}
	
	public void stopMusic() {
		
		music.stop();
	}
	
	public void playSE(int i) {
		
		se.setFile(i);
		se.play();
	}
	
}
