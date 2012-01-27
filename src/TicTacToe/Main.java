package TicTacToe;

/**
 * Interprets command line arguments, gets the director going, saves log file.
 */
public class Main {

	enum AgentType {
		WIN,
		TIE,
		SIMPLE,
		HUMAN
	}
	
	/**
	 * @param args Two commandline arguments representing the type of agent to use
	 * 				Either "win", "tie", "simple", or "human", case insensitive.
	 */
	public static void main(String[] args) {
		if(args.length != 2) {
			System.out.println("Incorrect number of arguments");
			printHelp();
			return;
		}
		for(int i = 0; i < 2; i++) {
			AgentType type = AgentType.valueOf(args[i].toUpperCase());
			switch(type) {
			case WIN:
				//todo
				break;
			case TIE:
				//todo
				break;
			case SIMPLE:
				//todo
				break;
			case HUMAN:
				//todo
				break;
			}
		}
		//pass instances of proper type to director
		final Director dir = new Director(new HumanAgent(), new HumanAgent());
		dir.runGame();
		//todo: log dir.getLog()
	}
	
	private static void printHelp() {
		System.out.println(
			"Program accepts two arguments which represent the types of the two players.\n" +
			"Valid arguments: 'win', 'tie', 'simple', 'human'"
		);
	}

}
