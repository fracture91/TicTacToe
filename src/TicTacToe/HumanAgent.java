package TicTacToe;

import java.util.Scanner;

public class HumanAgent extends Agent {
	
	@Override
	public Position getNextMove() {
		System.out.println("Make a move, human.  " +
				"Type the number corresponding with the space you want:");
		final Scanner scan = new Scanner(System.in);
		return new Position(scan.nextInt());
	}

	@Override
	public String getName() {
		return "Human";
	}

}
