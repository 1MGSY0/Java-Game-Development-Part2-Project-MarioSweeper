package gameplay;

//contains the enumeration of the difficulty levels
public enum Difficulty {
	EASY(10, 1.0), 
	INTERMEDIATE(15, 1.25), 
	ADVANCED(20, 1.5), 
	EXTREME(30, 3.0);
	
	private final int numMines;
	private final double scoreBonus;
	
	private Difficulty(int numMines, double scoreBonus) {
		this.numMines = numMines;
		this.scoreBonus = scoreBonus;
	}
	
	public int getNumMines() {
		return this.numMines;
	}
	public double getScoreBonus() {
		return this.scoreBonus;
	}
}
