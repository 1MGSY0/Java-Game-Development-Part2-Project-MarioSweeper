package gameplay;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;

public class Cell extends JButton {
   private static final long serialVersionUID = 1L;  // to prevent serial warning

   // Define named constants for JButton's colors and fonts
   // to be chosen based on cell's state
   public static final Color BG_NOT_REVEALED = Color.GREEN;
   public static final Color FG_NOT_REVEALED = Color.RED;    // flag, mines
   public static final Color BG_REVEALED = Color.DARK_GRAY;
   public static final Color FG_REVEALED = Color.YELLOW; // number of mines
   public static final Font FONT_NUMBERS = new Font("Monospaced", Font.BOLD, 20);

   // Define properties (package-visible)
   
   int row, col;
   boolean isRevealed;
   boolean isMined;
   boolean isFlagged;
   
   //Constructor
   public Cell(int row, int col) {
      super();   // JTextField
      this.row = row;
      this.col = col;
      // Set JButton's default display properties
      super.setFont(FONT_NUMBERS);
   }

   public void newGame(boolean isMined) {
      this.isRevealed = false; // default
      this.isFlagged = false;  // default
      this.isMined = isMined;  // given
      super.setEnabled(true);  // enable button
      super.setText("");       // display blank
      paint();
   }

   public void paint() {
      super.setForeground(isRevealed? FG_REVEALED: FG_NOT_REVEALED);
      super.setBackground(isRevealed? BG_REVEALED: BG_NOT_REVEALED);
   }
}