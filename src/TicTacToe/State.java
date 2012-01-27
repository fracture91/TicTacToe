package TicTacToe;

import static TicTacToe.PlayerType.CROSS;
import static TicTacToe.PlayerType.NOUGHT;

/**
 * Holds a board and whose turn it is.
 */
public class State implements Cloneable {

	private static final int height = 3;
	private static final int width = 3;
	private final PlayerType[][] board;
	private PlayerType turn = CROSS;
	private boolean done = false;
	private PlayerType winner = null;
	
	public State() {
		board = new PlayerType[height][width];
		checkDone();
		//default value is null
	}

	private State(PlayerType[][] board, PlayerType turn) {
		this.board = board;
		this.turn = turn;
	}
	
	/**
	 * Make the next move at the given position.  Change turn.
	 * @param pos Position to place the next thingy at
	 */
	public void makeMove(Position pos) {
		if(getTypeAt(pos) == null) {
			setTypeAt(pos, turn);
			checkDone();
			turn = turn == CROSS ? NOUGHT : CROSS;
		}
		else {
			throw new StateException("Cannot make move on occupied position "
					+ pos.getSpace() + " " + pos);
		}
	}
	
	private PlayerType getTypeAt(Position pos) {
		try {
			return board[pos.getRow()][pos.getColumn()];
		} catch(ArrayIndexOutOfBoundsException e) {
			final StateException se = new StateException("Position out of bounds: " + pos);
			se.initCause(e);
			throw se;
		}
	}
	
	private void setTypeAt(Position pos, PlayerType type) {
		try {
			board[pos.getRow()][pos.getColumn()] = type;
		} catch(ArrayIndexOutOfBoundsException e) {
			final StateException se = new StateException("Cannot move out of bounds to " + pos);
			se.initCause(e);
			throw se;
		}
	}
	
	public State clone() {
		final PlayerType[][] cloneBoard = new PlayerType[height][width];
		for(int i = 0; i < board.length; i++) {
			cloneBoard[i] = board[i].clone();
		}
		return new State(cloneBoard, turn);
	}

	public PlayerType getTurn() {
		return turn;
	}

	/**
	 * Check if the game is over, and, if so, determine the winner.
	 */
	private void checkDone() {
		//kinda gross
		
		//rows
		for(int i = 0; i < board.length; i++) {
			PlayerType rowType = board[i][0];
			if(rowType == null) {
				break;
			}
			boolean goodRow = true;
			for(int j = 1; j < board[i].length; j++) {
				if(board[i][j] != rowType) {
					goodRow = false;
					break;
				}
			}
			if(goodRow) {
				done = true;
				winner = rowType;
				return;
			}
		}
		
		//columns
		for(int i = 0; i < board[0].length; i++) {
			PlayerType colType = board[0][i];
			if(colType == null) {
				break;
			}
			boolean goodCol = true;
			for(int j = 1; j < board.length; j++) {
				if(board[j][i] != colType) {
					goodCol = false;
					break;
				}
			}
			if(goodCol) {
				done = true;
				winner = colType;
				return;
			}
		}
		
		final PlayerType center = board[1][1];
		if(center == null) {
			return;
		}
		
		//diagonal
		if(board[0][0] == center && board[2][2] == center ||
				board[0][2] == center && board[2][0] == center) {
			done = true;
			winner = center;
			return;
		}
		
		for(PlayerType[] i : board) {
			for(PlayerType j : i) {
				if(j == null) {
					return;
				}
			}
		}
		
		//if we reached this point, there are no null spaces
		done = true;
		winner = null;
		
	}
	
	public boolean isDone() {
		return done;
	}

	public PlayerType getWinner() {
		return winner;
	}
	
	public String toString() {
		final StringBuilder str = new StringBuilder();
		
		for(int row = 0; row < board.length; row++) {
			for(int column = 0; column < board[row].length; column++) {
				Position currentPos = new Position(column, row);
				PlayerType currentType = getTypeAt(currentPos);
				if(currentType != null) {
					str.append(currentType.getCharacter());
				}
				else {
					str.append(currentPos.getSpace());
				}
				str.append(' ');
			}
			str.append('\n');
		}
		
		return str.toString();
	}

}
