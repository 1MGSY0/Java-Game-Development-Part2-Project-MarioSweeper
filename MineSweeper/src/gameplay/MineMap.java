package gameplay;

import java.util.Random;

//Locations of Mines
public class MineMap {
	// package access
	Difficulty difficulty;
	GameBoardPanel gbp;
	
	boolean[][] isMined = new boolean[GameBoardPanel.ROWS][GameBoardPanel.COLS];
	
	// Constructor
	public MineMap(GameBoardPanel gbp) {
		this.gbp = gbp;
	}
	
	public void newMineMap(String lvlChoice) {
		lvlChoice = "LOW";
		   switch(lvlChoice) {
		   case "LOW":
			   Difficulty.EASY.getNumMines();
			   break;
		   case "MEDIUM":
			   Difficulty.INTERMEDIATE.getNumMines();
			   break;
		   case "HIGH":
			   Difficulty.ADVANCED.getNumMines();
			   break;
		   	case "SUPER":
		   		Difficulty.EXTREME.getNumMines();
		   		break;
		   }
		   generateMines();
	}
	
	private void generateMines() {
		    Random random = new Random();
		    int minesLeft = difficulty.getNumMines();
		    //generating random locations for mines
		    while (minesLeft > 0) {
		        int x = random.nextInt(9);
		        int y = random.nextInt(9);
	
		        if (!isMined[x][y]) {
		            isMined[x][y] = true;
		            minesLeft--;
		        }
		    }
	}
}