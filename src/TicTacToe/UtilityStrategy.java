package TicTacToe;

/**
 * Provides a way to determine utility of a state in the minimax algorithm.
 */
public interface UtilityStrategy {
	
	/**
	 * @param state The State to examine
	 * @return The utility of the given state
	 */
	int getUtility(State state);
	
	/**
	 * Set the player to favor in utility calculations.
	 * For example, if you want CROSS to win, getUtility will return higher numbers
	 * for states of greater utility to CROSS.
	 * 
	 * @param favored Player to favor
	 */
	void setFavoredPlayer(PlayerType favored);

	/**
	 * @return A friendly name for this strategy.
	 */
	String getName();

}
