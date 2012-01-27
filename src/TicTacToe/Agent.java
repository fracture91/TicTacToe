package TicTacToe;

/**
 * Represents an agent that can play tic-tac-toe.
 * Could be autonomous or a human.
 */
public abstract class Agent {
	
	protected State state;
	protected PlayerType type;
	protected String name = "Agent";

	/**
	 * Lets this agent know which position the opponent took in his last move
	 * @param lastMove The position of the opponent's last move
	 */
	public void receiveMove(Position lastMove) {
		if(lastMove != null) {
			state.makeMove(lastMove);
		}
	}
	
	/**
	 * @return the next position this agent is taking
	 */
	public abstract Position makeMove();
	
	/**
	 * Initialize the agent
	 * @param initialState State to initialize as
	 * @param myType PlayerType to play as
	 */
	public void initialize(State initialState, PlayerType myType) {
		state = initialState;
		type = myType;
	}
	
	public String getName() {
		return name;
	}
}
