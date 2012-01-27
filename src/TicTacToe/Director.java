package TicTacToe;

import static TicTacToe.PlayerType.CROSS;
import static TicTacToe.PlayerType.NOUGHT;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Responsible for pitting two agents against each other.
 * Prints out board after each move.
 * Holds all output to console so it can be logged by client.
 */
public class Director {
	
	private final Agent player1;
	private final Agent player2;
	private State state;
	private Position lastMove;
	private StringBuilder log;
	private Map<Agent, List<Long>> timeLogs;
	private Map<Agent, List<Integer>> nodeLogs;

	/**
	 * Create a game between two players
	 * @param player1 An agent who takes the first turn and is PlayerType.CROSS
	 * @param player2 An agent who takes the second turn and is PlayerType.NOUGHT
	 */
	public Director(Agent player1, Agent player2) {
		this.player1 = player1;
		this.player2 = player2;
	}
	
	/**
	 * Play the game.  Should catch any exceptions.
	 */
	public void runGame() {
		log = new StringBuilder();
		state = new State();
		player1.initialize(state.clone(), CROSS);
		player2.initialize(state.clone(), NOUGHT);
		timeLogs = new HashMap<Agent, List<Long>>();
		nodeLogs = new HashMap<Agent, List<Integer>>();
		timeLogs.put(player1, new ArrayList<Long>());
		timeLogs.put(player2, new ArrayList<Long>());
		nodeLogs.put(player1, new ArrayList<Integer>());
		nodeLogs.put(player2, new ArrayList<Integer>());
		
		println(player1.getName() + " vs. " + player2.getName());
		println(state.toString());
		
		lastMove = null;
		Agent player = player1;
		boolean done = false;
		
		while(!done) {
			PlayerType turn = state.getTurn();
			performTurn(player);
			println("Player " + player.getName() + " adds a " + turn +
					" to position " + lastMove.getSpace());
			println(state.toString());
			done = state.isDone();
			if(done) {
				println("Game complete.");
				PlayerType winner = state.getWinner();
				if(winner == null) {
					println("It's a tie.");
				}
				else {
					Agent winningPlayer = getPlayerByType(winner);
					println(winner.name() + " Player " + winningPlayer.getName() + " wins.");
				}
				print("\n\n");
				printStatistics(player1);
				print("\n");
				printStatistics(player2);
			}
			//state.turn will have changed by now
			player = getPlayerByType(state.getTurn());
		}
	}
	
	
	
	private Agent getPlayerByType(PlayerType type) {
		return type == CROSS ? player1 : player2;
	}

	private void tryToGiveMove(Agent player, Position move) {
		try {
			player.receiveMove(move);
		}
		catch(Exception e) {
			final StateException se = new StateException(state.getTurn() +
					" Player " + player.getName() + "died.");
			se.initCause(e);
			throw se;
		}
	}
	
	/**
	 * Try to get the given player to make a valid move
	 * @param player The player to ask to make a move
	 */
	private void performTurn(Agent player) {
		boolean valid = false;
		
		//give opponent's last move
		tryToGiveMove(player, lastMove);
		boolean human = player instanceof HumanAgent;
		
		final long startTime = System.nanoTime();
		   
		do {
			try {
				lastMove = player.getNextMove();
				state.makeMove(lastMove);
				valid = true;
			} catch(Exception e) {
				System.out.println("Erroneous move by player " + player.getName() + ":");
				System.out.println(e.getMessage());
				if(!human) {
					throw new StateException("AI has failed horribly");
				}
			}
		//allow humans another chance to give a valid move
		} while (!valid && human);
		
		timeLogs.get(player).add(System.nanoTime() - startTime);
		nodeLogs.get(player).add(player.getNodesVisited());
		
		//give its own last move
		tryToGiveMove(player, lastMove);
	}
	
	private void print(String text) {
		System.out.print(text);
		log.append(text);
	}
	
	/**
	 * Wrapper for System.out.println that will also append to log.
	 * @param line line to print
	 */
	private void println(String line) {
		print(line + "\n");
	}
	
	private void printStatistics(Agent player) {
		final List<Long> times = timeLogs.get(player);
		final List<Integer> nodes = nodeLogs.get(player);
		println(player.getName());
		print("Nodes,");
		printList(nodes);
		print("\nTimes,");
		printList(times);
	}
	
	private void printList(List<?> list) {
		final StringBuilder str = new StringBuilder();
		boolean first = true;
	    for (Object i : list) {
	    	if(first) {
	    		first = !first;
	    	} else {
	    		str.append(',');
	    	}
	        str.append(i);
	    }
	    print(str.toString());
	}
	
	/**
	 * @return a string holding everything printed to the console during the last game
	 * 			except human output/errors
	 */
	public String getLog() {
		if(log != null) {
			return log.toString();
		}
		return "Game not played";
	}
}
