package mariosweeper;

import javax.swing.*;

import gameplay.GameBoardPanel;

import java.awt.*;

public class Main {
	
	public static JLayeredPane layeredPane = new JLayeredPane();;
	public static MainWindow mainWindow = new MainWindow();
	public static JFrame window = new JFrame("MarioSweeper");
	public static GameBoardPanel gameBoardPanel= new GameBoardPanel(mainWindow);
	
	public static boolean displayBoard = false;
	
	public static void main(String[] args) {
		
		mainWindow.setOpaque(true);
		mainWindow.setBounds(0,0,MainWindow.SCREEN_WIDTH,MainWindow.SCREEN_HEIGHT);
		
		gameBoardPanel.setOpaque(false);
		gameBoardPanel.setBackground(Color.GREEN);
		gameBoardPanel.setBounds(MainWindow.tileSize*4,MainWindow.tileSize*3,MainWindow.tileSize*8,MainWindow.tileSize*8);
		
		layeredPane.setBounds(0,0,MainWindow.SCREEN_WIDTH,MainWindow.SCREEN_HEIGHT);
		
		layeredPane.add(mainWindow, Integer.valueOf(1));
		layeredPane.add(gameBoardPanel, Integer.valueOf(0));
	  		
		window.add(layeredPane);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(new Dimension(MainWindow.SCREEN_WIDTH+15, MainWindow.SCREEN_HEIGHT+20));
		window.setLayout(null);
		window.setVisible(true);
		window.setLocationRelativeTo(null);
		window.setResizable(false);
		   
		//GAME SETUP
		mainWindow.setupGame();
		mainWindow.startGameThread();
	}
	
	
}
