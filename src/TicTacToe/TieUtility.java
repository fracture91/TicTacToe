package TicTacToe;

public class TieUtility implements UtilityStrategy {

	private PlayerType favored;

	@Override
	public int getUtility(State state) {
		if(state.isDone()) {
			if(state.getWinner() == favored) {
				return 0;
			}
			else if(state.getWinner() == null) {
				return 1;
			}
			else {
				return -1;
			}
		}
		throw new StateException("Cannot determine utility of unfinished state!\n" + state);
	}
	
	@Override
	public void setFavoredPlayer(PlayerType favored) {
		this.favored = favored;
	}

	@Override
	public String getName() {
		return "Tie";
	}

}
