package TicTacToe;

import java.util.Enumeration;


public class Minimax {


	private final SearchNode root;
	private UtilityStrategy strategy;
	
	/**
	 * Create a Minimax instance with the given initial State and UtilityStrategy
	 * @param initialState The initial state of the problem
	 * @param strategy The strategy to use when determining utility
	 */
	public Minimax(State initialState, UtilityStrategy strategy) {
		root = new SearchNode(initialState, null, 0xDEADBEEF, strategy);
		this.strategy = strategy;
	}
	
	/**
	 * Returns the best Position to take to maximize utility.
	 */
	public Position findBestAction() {
		root.expand();
		
		int max = Integer.MIN_VALUE;
		SearchNode maxNode = null;
		for(@SuppressWarnings("unchecked")
				Enumeration<SearchNode> e = root.children(); e.hasMoreElements();) {
			SearchNode i = e.nextElement();
			int utility = minValue(i);
			i.setUtility(utility);
			max = Math.max(max, utility);
			if(max == utility) {
				maxNode = i;
			}
		}
		
		return maxNode.getLastAction();
	}
	
	private int minValue(SearchNode node) {
		if(node.getState().isDone()) {
			return node.getUtility();
		}
		
		int min = Integer.MAX_VALUE;
		node.expand();
		
		for(@SuppressWarnings("unchecked")
				Enumeration<SearchNode> e = node.children(); e.hasMoreElements();) {
			SearchNode i = e.nextElement();
			int utility = maxValue(i);
			i.setUtility(utility);
			min = Math.min(min, utility);
		}
		
		return min;
	}

	private int maxValue(SearchNode node) {
		if(node.getState().isDone()) {
			return node.getUtility();
		}
		
		int max = Integer.MIN_VALUE;
		node.expand();
		
		for(@SuppressWarnings("unchecked")
				Enumeration<SearchNode> e = node.children(); e.hasMoreElements();) {
			SearchNode i = e.nextElement();
			int utility = minValue(i);
			i.setUtility(utility);
			max = Math.max(max, utility);
		}
		
		return max;
	}

}
