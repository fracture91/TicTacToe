package TicTacToe;

import static TicTacToe.PlayerType.CROSS;
import static TicTacToe.PlayerType.NOUGHT;

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
			}
			//state.turn will have changed by now
			player = getPlayerByType(state.getTurn());
		}
	}
	
	
	
	private Agent getPlayerByType(PlayerType type) {
		return type == CROSS ? player1 : player2;
	}

	/**
	 * Try to get the given player to make a valid move
	 * @param player The player to ask to make a move
	 */
	private void performTurn(Agent player) {
		boolean valid = false;
		
		try {
			player.receiveMove(lastMove);
		}
		catch(Exception e) {
			final StateException se = new StateException(state.getTurn() +
					" Player " + player.getName() + "died.");
			se.initCause(e);
			throw se;
		}
		
		while(!valid) {
			try {
				lastMove = player.makeMove();
				state.makeMove(lastMove);
				valid = true;
			} catch(Exception e) {
				System.out.println("Erroneous move by player " + player.getName() + ":");
				System.out.println(e.getMessage());
			}
		}
	}
	
	/**
	 * Wrapper for System.out.println that will also append to log.
	 * @param line line to print
	 */
	private void println(String line) {
		System.out.println(line);
		log.append(line + "\n");
	}
	
	/**
	 * @return a string holding everything printed to the console during the last game
	 */
	public String getLog() {
		if(log != null) {
			return log.toString();
		}
		return "Game not played";
	}
}