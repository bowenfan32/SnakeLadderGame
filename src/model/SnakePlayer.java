package model;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import model.interfaces.Entity;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;
import view.SnakePlayerView;


public class SnakePlayer extends Player implements Entity {

	private int head;
	private int tail;
	private BoardModel gameBoard;


	public SnakePlayerView view;
	/*
	private Player[] player = new Player[10];
	private int index = 0;
	private Snake[] ss = new Snake[10];
	private Ladder[] ls = new Ladder[10];
	private SnakePlayer[] sps = new SnakePlayer[10];
	*/

	ArrayList<Integer> snakeHeadList = new ArrayList<Integer>();

	int snakesCount = 0;
	int laddersCount = 0;
	int snakePlayerCount = 0;

	static Scanner scan = new Scanner(System.in);

	public SnakePlayer(int h, int t, String username, BoardModel bd, int pos) {
		head = 10; //h
		tail = 1; //t
		this.name = username;
		this.pos = pos;
		this.gameBoard = bd;
		this.view = new SnakePlayerView(this);
	}

	@Override
	public int move(int positionsToMove) {

		// Get all the head position of snakes and snake players
		for (int i = 0; i < gameBoard.snakesCount; i++) {
			snakeHeadList.add(gameBoard.snakesArray[i].getHead());
		}
		for (int i = 0; i < gameBoard.getSnakePlayerCount(); i++) {
			snakeHeadList.add(gameBoard.snakePlayerList.get(i).getHead());
		}
		do {

			// computes the new position based on the dice
			if (pos + positionsToMove <= 100) {
				pos += positionsToMove;
			} else {
				pos = 100;
			}
			// If landed on a player piece, move it to its tail
			for (int i = 0; i < gameBoard.getPlayerCount(); i++) {
				if (pos == gameBoard.players.get(i).getPos()) {
					
					gameBoard.players.get(i).setPos(this.getTail());
					
					for (GameEngineCallback callback : this.gameBoard.gameEngine.getGameEngineCallbacks()) {
						callback.logThis(String.format("%s consumed %s, you have been moved to %d", this.getName(), gameBoard.players.get(i).getName(), this.getTail()));
					}
				}
			}

			pos = newPos(pos);

			// slow down the game here artificially
			try {
				Thread.sleep(1200);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		} while (snakeHeadList.contains(head));
		return newPos(pos);
	}

	// Computes the new position taking into account the positions of the
	// snakes and ladders
	public int newPos(int pos) {
		int val = pos;



		// for (int i = 0; i < snakesCount; i++)
		// if (pos == ss[i].getHead())
		// val = ss[i].getTail();
		//
		// if (val < pos) {
		// System.out.println("You are bitten by a snake. Press 1 to continue");
		// }

		this.head = val;

		return val;
	}


	@Override
	public int change() {
		return 0;
	}

	public int getHead() {
		return head;
	}

	public int getTail() {
		return tail;
	}
}
