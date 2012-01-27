package TicTacToe;

public class MinimaxAgent extends Agent {

	private final UtilityStrategy strategy;
	private int nodesVisited;
	
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
		final Minimax mm = new Minimax(state, strategy);
		final Position pos = mm.findBestAction();
		nodesVisited = mm.getNodesVisited();
		return pos;
	}

	@Override
	public String getName() {
		return "Minimax-" + strategy.getName();
	}
	
	@Override
	public int getNodesVisited() {
		return nodesVisited;
	}

}
