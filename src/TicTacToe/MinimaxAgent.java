package TicTacToe;

public class MinimaxAgent extends Agent {

	private UtilityStrategy strategy;
	
	public MinimaxAgent(UtilityStrategy strategy) {
		this.strategy = strategy;
	}
	
	@Override
	public void initialize(State initialState, PlayerType myType) {
		super.initialize(initialState, myType);
		strategy.setFavoredPlayer(myType);
	}

	@Override
	public Position getNextMove() {
		Minimax mm = new Minimax(state, strategy);
		return mm.findBestAction();
	}

	@Override
	public String getName() {
		return "Minimax-" + strategy.getName();
	}

}
