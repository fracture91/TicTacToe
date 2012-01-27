package TicTacToe;

/**
 * Represents an agent that can play tic-tac-toe.
 * Could be autonomous or a human.
 */
public abstract class Agent {
	
	protected State state;
	protected PlayerType type;

	/**
	 * Lets this agent know that a move has been accepted so it can update its internal state.
	 * Should be called for both opponent's moves and this agent's moves.
	 * @param lastMove The position of the last move
	 */
	public void receiveMove(Position lastMove) {
		if(lastMove != null) {
			state.makeMove(lastMove);
		}
	}
	
	/**
	 * @return the next position this agent is taking
	 */
	public abstract Position getNextMove();
	
	/**
	 * Initialize the agent
	 * @param initialState State to initialize as
	 * @param myType PlayerType to play as
	 */
	public void initialize(State initialState, PlayerType myType) {
		state = initialState;
		type = myType;
	}
	
	public abstract String getName();
	
	/**
	 * @return {@link TicTacToe.MinimaxAgent#getNodesVisited()} for the last turn
	 */
	public int getNodesVisited() {
		return 0;
	}

}
