package gameplay;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import mariosweeper.KeyHandler;
import mariosweeper.MainWindow;
import mariosweeper.UI;

public class GameBoardPanel extends JPanel {
   private static final long serialVersionUID = 1L;  // to prevent serial warning

   // Define named constants for the game properties
   public static final int ROWS = 10;      // number of cells
   public static final int COLS = 10;

   // Define named constants for UI sizes
   public static final int CELL_SIZE = 60;  // Cell width and height, in pixels
   public static final int CANVAS_WIDTH  = CELL_SIZE * COLS; // Game board width/height
   public static final int CANVAS_HEIGHT = CELL_SIZE * ROWS;

   // Define properties (package-visible)
   // The game board composes of ROWSxCOLS cells
   Cell cells[][] = new Cell[ROWS][COLS];
   MainWindow mw;
   UI ui;
   
   
	//STATS
    public static int score = 0;
    public static int coinCollected = 0;
    public static final int clearBonus = 20000;
	public String levelChoice; 

   /** Constructor */
   public GameBoardPanel(MainWindow mw) {
	  this.mw = mw;
	  levelChoice = mw.ui.getSelectedLevel(mw.ui.lvlCmdNum); 
	  score = 0;
      coinCollected = 0;
      super.setLayout(new GridLayout(ROWS, COLS, 2, 2));  // JPanel

      // Allocate the 2D array of Cell, and added into content-pane.
      for (int row = 0; row < ROWS; ++row) {
         for (int col = 0; col < COLS; ++col) {
            cells[row][col] = new Cell(row, col);
            super.add(cells[row][col]);
         }
      }
      // Allocate a common listener as the MouseEvent listener for all the
      //  Cells (JButtons)
      CellMouseListener listener = new CellMouseListener();

      // Every cell adds this common listener
      for (int row = 0; row < ROWS; ++row) {
    	   for (int col = 0; col < COLS; ++col) {
    	      cells[row][col].addMouseListener(listener);   // For all rows and cols
    	   }
      }
      
      // Set the size of the content-pane and pack all the components
      //  under this container.

   }

   // Initialize and re-initialize a new game
   public void newGame() {
	  System.out.println("NEW GAME");
	  
	     // Get a new mine map
      MineMap mineMap = new MineMap(this);
      mineMap.newMineMap(levelChoice);

      // Reset cells, mines, and flags
      for (int row = 0; row < ROWS; row++) {
         for (int col = 0; col < COLS; col++) {
            // Initialize each cell with/without mine
            cells[row][col].newGame(mineMap.isMined[row][col]);
         }
      }

   }
   


   // Return the number of mines [0, 8] in the 8 neighboring cells
   //  of the given cell at (srcRow, srcCol).
   private int getSurroundingMines(int srcRow, int srcCol) {
      int numMines = 0;
      for (int row = srcRow - 1; row <= srcRow + 1; row++) {
         for (int col = srcCol - 1; col <= srcCol + 1; col++) {
            // Need to ensure valid row and column numbers too
            if (row >= 0 && row < ROWS && col >= 0 && col < COLS) {
               if (cells[row][col].isMined) numMines++;
            }
         }
      }
      return numMines;
   }

   // Reveal the cell at (srcRow, srcCol)
   // If this cell has 0 mines, reveal the 8 neighboring cells recursively
   private void revealCell(int srcRow, int srcCol) {
	   int numMines = getSurroundingMines(srcRow, srcCol);
	   if (numMines != 0) {
		   cells[srcRow][srcCol].setText(numMines + "");
	   }
	   cells[srcRow][srcCol].isRevealed = true;
	   cells[srcRow][srcCol].paint();  // based on isRevealed
	   if (numMines == 0) {
		   // Recursively reveal the 8 neighboring cells
		   for (int row = srcRow - 1; row <= srcRow + 1; row++) {
			   for (int col = srcCol - 1; col <= srcCol + 1; col++) {
				   // Need to ensure valid row and column numbers too
				   if (row >= 0 && row < ROWS && col >= 0 && col < COLS) {
					   if (!cells[row][col].isRevealed) {
						   revealCell(row, col);
						   coinCollected++;
					   }
				   }
			   }
		   }
	   }
	}

   // Return true if the player has won (all cells have been revealed or were mined)
   public boolean hasWon() {
	   for (int row = 0; row < ROWS; row++) {
           for (int col = 0; col < COLS; col++) {
        	   Cell cell = cells[row][col];
               if (!cell.isMined && !cell.isRevealed) {
                   return false;
               }
           }
       }
       return true;
   }

   // Define a Listener Inner Class
   private class CellMouseListener extends MouseAdapter {
	      @Override
	      public void mouseClicked(MouseEvent e) {         // Get the source object that fired the Event
	         Cell sourceCell = (Cell)e.getSource();
	         // For debugging
	         System.out.println("You clicked on (" + sourceCell.row + "," + sourceCell.col + ")");

	         // Left-click to reveal a cell; Right-click to plant/remove the flag.
	         if (e.getButton() == MouseEvent.BUTTON1) {  // Left-button clicked
	            if (sourceCell.isMined) {
	            	sourceCell.setText("*");
	            	getScore(false, coinCollected, levelChoice);
	            	// Game Over 
	            	mw.stopMusic();
	            	mw.ui.gameWon = false;
	            	// RESULT SCREEN
	            	
	            	mw.gameState = mw.resultState;
	            	
	            } else {
	            	revealCell(sourceCell.row, sourceCell.col);
	            }
	         } else if (e.getButton() == MouseEvent.BUTTON3) { // right-button clicked
	            if (!sourceCell.isRevealed) {
	            	if (sourceCell.isFlagged) {
	            		sourceCell.setText("");
	            		sourceCell.isFlagged = false;
	            	} else {
	            		sourceCell.setText("?");
	            		sourceCell.isFlagged = true;
	            	}
	            }
	         }

	         // Check if the player has won, after revealing this cell
	         if (hasWon()) {
	        	 getScore(true, coinCollected, levelChoice);
	        	 mw.stopMusic();
	        	 mw.ui.gameWon = true;
	        	// RESULT SCREEN
	        	 mw.gameState = mw.resultState; 
	         }
	      }
	   }
   
   public void getScore(boolean hasCleared, int coinNum, String levelChoice) {
	   	//STATS
	   	hasCleared = hasWon();
	   	coinNum = coinCollected;
	   	
	   	if(hasCleared) {
	   		score = (coinNum*100 + clearBonus) * getlvlBonus(levelChoice);
	   	}else {
	   		score = (coinNum*100) * getlvlBonus(levelChoice);
	   	}
   }
   
   public int getlvlBonus(String levelChoice) {
	   int lvlBonus =0;
	   switch(levelChoice) {
	   case "LOW":
		   lvlBonus =1;
		   break;
	   case "MEDIUM":
		   lvlBonus =2;
		   break;
	   case "HIGH":
		   lvlBonus =3;
		   break;
	   	case "SUPER":
	   		lvlBonus =4;
	   		break;
	   }
	   return lvlBonus;
   }
   
}

