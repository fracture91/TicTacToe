package TicTacToe;
import java.util.Set;

import javax.swing.tree.DefaultMutableTreeNode;


public class SearchNode extends DefaultMutableTreeNode {

	private static final long serialVersionUID = 1845825236979052915L;
	private int utility;
	private final State state;
	private final UtilityStrategy strategy;
	private Position lastAction;
	
	/**
	 * Creates a SearchNode with no parent or children
	 * @param state The state to associate with this node
	 * @param totalCost The total cost to reach this node's state
	 */
	public SearchNode(State state, Position lastAction, int utility, UtilityStrategy strategy) {
		this.state = state;
		this.lastAction = lastAction;
		this.utility = utility;
		this.strategy = strategy;
	}

	/**
	 * @return the total cost to reach this node's state
	 */
	public int getUtility() {
		return utility;
	}
	
	public void setUtility(int utility) {
		this.utility = utility;
	}

	/**
	 * @return the state associated with this node
	 */
	public State getState() {
		return state;
	}
	
	public Position getLastAction() {
		return lastAction;
	}

	@Override
	public int hashCode() {
		return state.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof SearchNode)) {
			return false;
		}
		final SearchNode other = (SearchNode) obj;
		if (state == null) {
			if (other.state != null) {
				return false;
			}
		} else if (!state.equals(other.state)) {
			return false;
		}
		return true;
	}

	/**
	 * Insert successors of this node's state as children of this node.
	 */
	public void expand() {
		final Set<Position> actions = state.getAllActions();
		for(Position i : actions) {
			int utility = 0xDEADBEEF;
			State nextState = state.clone();
			nextState.makeMove(i);
			if(nextState.isDone()) {
				utility = strategy.getUtility(nextState);
			}
			this.insert(new SearchNode(nextState, i, utility, strategy), this.getChildCount());
		}
	}

	@Override
	public String toString() {
		return "SearchNode (" + utility + ", \n" + state + ")";
	}
	
}
