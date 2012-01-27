package TicTacToe;

public class SimpleAgent extends Agent {

	public SimpleAgent() {
		name = "Simple";
	}
	
	@Override
	public Position getNextMove() {
		for(int i = 0; i < state.getHeight(); i++) {
			for(int j = 0; j < state.getWidth(); j++) {
				Position pos = new Position(j, i);
				if(state.getTypeAt(pos) == null) {
					return pos;
				}
			}
		}
		//should never happen
		return null;
	}

}
