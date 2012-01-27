package TicTacToe;

public interface UtilityStrategy {
	
	/**
	 * @param state The State to examine
	 * @return The utility of the given state
	 */
	public int getUtility(State state);
	
	/**
	 * Set the player to favor in utility calculations.
	 * For example, if you want CROSS to win, getUtility will return higher numbers
	 * for states of greater utility to CROSS.
	 * 
	 * @param favored Player to favor
	 */
	public void setFavoredPlayer(PlayerType favored);

	/**
	 * A friendly name for this strategy.
	 */
	public String getName();

}
