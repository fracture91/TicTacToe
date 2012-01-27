package TicTacToe;

import java.io.FileWriter;
import java.io.IOException;

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
	 * @throws IOException if log files mess up
	 */
	public static void main(String[] args) throws IOException {
		if(args.length != 2) {
			System.out.println("Incorrect number of arguments");
			printHelp();
			return;
		}
		
		final Agent[] agents = new Agent[2];
		for(int i = 0; i < 2; i++) {
			AgentType type = AgentType.valueOf(args[i].toUpperCase());
			switch(type) {
			case WIN:
				agents[i] = new MinimaxAgent(new WinUtility());
				break;
			case TIE:
				agents[i] = new MinimaxAgent(new TieUtility());
				break;
			case SIMPLE:
				agents[i] = new SimpleAgent();
				break;
			case HUMAN:
				agents[i] = new HumanAgent();
				break;
			}
		}
		
		//pass instances of proper type to director
		final Director dir = new Director(agents[0], agents[1]);
		dir.runGame();
		
		final String filename = "log_" + agents[0].getName() +
								"_vs_" + agents[1].getName() + ".txt";
		final FileWriter output = new FileWriter(filename);
		output.write(dir.getLog());
		output.close();
	}
	
	private static void printHelp() {
		System.out.println(
			"Program accepts two arguments which represent the types of the two players.\n" +
			"Valid arguments: 'win', 'tie', 'simple', 'human'"
		);
	}

}
