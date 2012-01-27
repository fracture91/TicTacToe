package TicTacToe;

public enum PlayerType {
	
	CROSS('X'),
	NOUGHT('O');
	
	private final char character;
	
	private PlayerType(char character) {
		this.character = character;
	}
	
	public char getCharacter() {
		return character;
	}
}
