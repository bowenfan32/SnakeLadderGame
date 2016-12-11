package model;

import java.util.Scanner;

import model.interfaces.Player;
import view.BoardPanel;
import view.HumanPlayerView;

public class HumanPlayer extends Player {

	BoardModel gameBoard;
	static Scanner scan = new Scanner(System.in);
	private int escapePoints;
	public HumanPlayerView view;
	private int selectedPiece = -1;


	public HumanPlayer(BoardModel board, int index, int pos, String username, int escapePoints) {
		this.gameBoard = board;
		this.name = username;
		this.pos = pos;
		this.index = index;
		this.escapePoints = escapePoints;

		this.view = new HumanPlayerView(this);
	}

	@Override
	public int move(int positionsToMove) {
		//System.out.println("***** Turn of  " + name + " ******");

		//do {
		/*
			System.out.print("Press Enter to throw dice ");
			resp = scan.nextLine();
			System.out.println("Rolling the dice .... Please wait");
			val = dice.roll();
			System.out.println("You threw a " + val);
		 */

		// tell the gameBoard who's turn it is now
		gameBoard.player = this;

		// computes the new position based on the dice
		// value
		if (pos + positionsToMove <= 100) {
			pos += positionsToMove;
		} else {
			pos = 100;
		}
		pos = gameBoard.newPos(pos);
		//gameBoard.repaint(); // causes the board and pieces to be redrawn
		//System.out.println(name + " is now at position " + pos);

		//if (val == 6)
		//	System.out.println("You get to throw again");
		//} while (val == 6);
		return gameBoard.newPos(pos);
	}



	public int getEscapePoints() {
		return escapePoints;
	}

	public void setEscapePoints(int escapePoints) {
		this.escapePoints = escapePoints;
	}

	public void setPieceToMove(int selectedPiece) {
		this.selectedPiece = selectedPiece;

	}
}
