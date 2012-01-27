package TicTacToe;

/**
 * Represents an x,y position on the tic-tac-toe board.
 * Top left is space #1 or (0,0), bottom right is space #9 or (2,2).
 */
public class Position {

	private final int row;
	private final int column;
	private final int width = 3;
	
	public Position(int column, int row) {
		this.column = column;
		this.row = row;
	}
	
	/**
	 * @param space see {@link TicTacToe.Position#getSpace()}
	 */
	public Position(int space) {
		row = (int) Math.floor((space - 1) / width);
		column = (space - 1) % 3;
	}

	
	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}
	
	/**
	 * The space number, which reads like
	 * 
	 * 1  2  3
	 * 4  5  6
	 * 7  8  9
	 * 
	 * @return the space number
	 */
	public int getSpace() {
		return column + 1 + width * row;
	}
	
	@Override
	public String toString() {
		return "(" + column + "," + row + ")";
	}
	
	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + column;
		result = prime * result + row;
		return result;
	}


	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof Position) {
			final Position other = (Position) obj;
			return column == other.column && row == other.row;
		}
		return false;
	}

}
