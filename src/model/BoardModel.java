package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import model.interfaces.GameEngineCallback;
import model.interfaces.Player;
import view.SnakeView;



public class BoardModel {

	public GameEngine gameEngine;

	public ArrayList<HumanPlayer> players;
	public int playerCount;
	public boolean onSnakePlayerTail;
	public boolean onSnakePlayerHead;
	public HumanPlayer player;
	public Snake[] snakesArray = new Snake[10];
	public Ladder[] ladderArray = new Ladder[10];
	public ArrayList<SnakePlayer> snakePlayerList = new ArrayList<SnakePlayer>();
	public int snakesCount;
	public int laddersCount;
	public int snakePlayerCount;
	public Random random = new Random();
	public Dice dice;
	public ArrayList<Integer> excludeList = new ArrayList<Integer>();
	public int head;
	public int tail;
	//NEW CUSTOM CHANGE********************
	//NEW CUSTOM CHANGE********************
	int numSnakes;
    int numLadders;
    int posGUIx; 
    int posGUIy;
  //NEW CUSTOM CHANGE********************
  //NEW CUSTOM CHANGE********************
	
    static Scanner scan = new Scanner(System.in);
	//private static JFrame frame = new JFrame("Assignment Demo");

	public BoardModel(GameEngine gameEngine) {
		this.gameEngine = gameEngine;
		this.setup();

	}
	/*
	public BoardData(Player player, SnakeView[] snakesArray, Ladder[] ladderArray, ArrayList<SnakePlayer> snakePlayerList,
			int snakesCount, int laddersCount, int snakePlayerCount, Random random, Dice dice,
			ArrayList<Integer> excludeList) {
		this.player = player;
		this.snakesArray = snakesArray;
		this.ladderArray = ladderArray;
		this.snakePlayerList = snakePlayerList;
		this.snakesCount = snakesCount;
		this.laddersCount = laddersCount;
		this.snakePlayerCount = snakePlayerCount;
		this.random = random;
		this.dice = dice;
		this.excludeList = excludeList;
	}
	 */

	// the standard positions of snakes and ladders
	private void setup() {
		add(new Ladder(5, 37));
		add(new Ladder(12, 51));
		add(new Ladder(53, 79));

		add(new Snake(75, 60));
		add(new Snake(53, 32));
		add(new Snake(95, 21));
		add(new Snake(45, 26));
		add(new Snake(28, 6));
	}


	// allows the number and positions of snakes and ladders to be customized
	//public void resetBoardToCustomise() {}

		//this.snakesCount = 0;
		//this.laddersCount = 0;
	
	//NEW CUSTOM CHANGE********************
	//NEW CUSTOM CHANGE********************
		
		public void customizeSNNum(int x) {

			snakesCount = 0;
			
			//repaint();
			numSnakes =x;
		}
		
		public void customizeLDNum(int y) {
			laddersCount = 0;
			numLadders =y;
		} //set number of snakes and ladders
			
		public void customizeSNLX(int x){ posGUIx = x;}
		public void customizeSNLY(int y){ posGUIy = y;}
		
		public void addSnakes(){
			add(new Snake(posGUIx, posGUIy));
			snakesCount++;
		}
		
		public void addLadders(){
			add(new Ladder(posGUIx, posGUIy));
		}
		public int numSnakes(){return numSnakes;}
		
		//NEW CUSTOM CHANGE********************
		//NEW CUSTOM CHANGE********************

		
		/*
		//repaint();
		int numSnakes;
		int numLadders;

		do {
			System.out.print("Enter number of snakes : 1..10 : ");
			numSnakes = scan.nextInt();
			System.out.print("Enter number of ladders : 1..10 : ");
			numLadders = scan.nextInt();
		} while (numSnakes < 1 || numSnakes > 10 || numLadders < 1 && numLadders > 10);
		for (int i = 0; i < numSnakes; i++) {
			int head, tail;
			do {
				System.out.print("Enter Head pos of snake " + (i + 1) + " : ");
				head = scan.nextInt();
				System.out.print("Enter Tail pos of snake " + (i + 1) + " : ");
				tail = scan.nextInt();
				if (tail >= head)
					System.out.println("Head must be higher than the tail. ReEnter");
			} while (head <= tail);
			add(new Snake(head, tail));
		}
		for (int i = 0; i < numLadders; i++) {
			int bottom, top;
			do {
				System.out.print("Enter Bottom pos of ladder " + (i + 1) + " : ");
				bottom = scan.nextInt();
				System.out.print("Enter Top pos of ladder " + (i + 1) + " : ");
				top = scan.nextInt();
				if (bottom >= top)
					System.out.println("Top must be higher than the Bottom. ReEnter");
			} while (top <= bottom);
			add(new Ladder(bottom, top));

		}

		 */
	
	


	public int getPlayerCount() {
		return this.playerCount;
	}

	public void setPlayerCount(int pCount) {
		this.playerCount = pCount;
	}

	// Computes the new position taking into account the positions of the
	// snakes and ladders
	public int newPos(int pos) {
		int val = pos;

		for (int i = 0; i < this.laddersCount; i++)
			if (pos == this.ladderArray[i].getBottom())
				val = this.ladderArray[i].getTop();

		for (int i = 0; i < this.snakesCount; i++)
			if (pos == this.snakesArray[i].getHead())
				val = this.snakesArray[i].getTail();

		for (int i = 0; i < getSnakePlayerCount(); i++) {
			if (pos == this.snakePlayerList.get(i).getTail()) {
				this.onSnakePlayerTail = true;
			}
		}

		for (int i = 0; i < getSnakePlayerCount(); i++) {
			if (pos == this.snakePlayerList.get(i).getHead()) {
				this.onSnakePlayerHead = true;
			}
		}

		if (val < pos) {
			// if player has escape points
			if (this.player.getEscapePoints() > 0) {
				this.player.setEscapePoints(this.player.getEscapePoints() - 1);
				for (GameEngineCallback callback : this.gameEngine.getGameEngineCallbacks()) {
					callback.logThis("You are bitten by a snake. A Escape Point is auto consumed...");
				}
				return pos;
				// Else if on snake player head, simply go to its tail
			} else if (this.onSnakePlayerHead) {
				for (GameEngineCallback callback : this.gameEngine.getGameEngineCallbacks()) {
					callback.logThis("You are bitten by a snake.");
				}
				//scan.nextInt();
				// else if on normal snake head, go to tail and generate a snake
				// player
			} else {
				for (GameEngineCallback callback : this.gameEngine.getGameEngineCallbacks()) {
					callback.logThis("You are bitten by a snake. A snake player is generated!");
				}
				//scan.nextInt();
				add(randomSnakePlayer());
			}
		} else if (val > pos) {



			//scan.nextInt();
			// gain a escape point if escape point < 3
			if (this.player.getEscapePoints() < 3) {
				this.player.setEscapePoints(this.player.getEscapePoints() + 1);
				for (GameEngineCallback callback : this.gameEngine.getGameEngineCallbacks()) {
					callback.logThis("You are going up the ladder. You have gained a Escape Point!");
				}
			} else {
				for (GameEngineCallback callback : this.gameEngine.getGameEngineCallbacks()) {
					callback.logThis("You are going up the ladder. No EscapePoints earned, you already have the maximum number.");
				}
			}
		} else if (this.onSnakePlayerTail) {
			removeSnakePlayer(pos);
			for (GameEngineCallback callback : this.gameEngine.getGameEngineCallbacks()) {
				callback.logThis("A snake player is destroyed!");
			}
		}
		return val;
	}

	public void removeSnakePlayer(int pos) {
		for (int i = 0; i < getSnakePlayerCount(); i++) {
			if (pos == this.snakePlayerList.get(i).getTail()) {
				this.snakePlayerList.remove(i);
				setSnakePlayerCount(getSnakePlayerCount() - 1);
			}
		}
	}
	public SnakePlayer randomSnakePlayer() {
		// Ensure snake player do not spawn on any existing squares
		for (int i = 0; i < getPlayerCount(); i++) {
			this.excludeList.add(this.players.get(i).getPos());
		}
		for (int i = 0; i < this.laddersCount; i++) {
			this.excludeList.add(this.ladderArray[i].getBottom());
			this.excludeList.add(this.ladderArray[i].getTop());
		}
		for (int i = 0; i < this.snakesCount; i++) {
			this.excludeList.add(this.snakesArray[i].getHead());
			this.excludeList.add(this.snakesArray[i].getTail());
		}

		do {
			this.head = this.random.nextInt(80 - 1 + 1) + 1;
			this.tail = this.random.nextInt(80 - 1 + 1) + 1;

		} while (this.head < this.tail || this.excludeList.contains(this.head) || this.excludeList.contains(this.tail));
		// random until tail is has lower position than head

		// return new SnakePlayer(this.head, this.tail, "SnakePlayer " + (getSnakePlayerCount() + 1), this, this.dice, this.head,
		//	getSnakePlayerCount() + 1);
		return new SnakePlayer(this.head, this.tail, "SnakePlayer " + (getSnakePlayerCount() + 1), this, this.head);

		// for testing:
		// return new SnakePlayer(11, 5, "SnakePlayer " + snakePlayerCount + 1,
		// this, dice, 11,
		// snakePlayerCount + 1);
	}

	public int getSnakePlayerCount() {
		return this.snakePlayerCount;
	}

	public void setSnakePlayerCount(int snakePlayerCount) {
		this.snakePlayerCount = snakePlayerCount;
	}

	public void addPlayers(ArrayList<HumanPlayer> players) {
		this.players = players;
		this.setPlayerCount(players.size());
	}

	public void add(Snake newSnake) {
		if (this.snakesCount < 10) {
			this.snakesArray[this.snakesCount] = newSnake;
			this.snakesCount++;
		}
	}

	public void add(Ladder newLadder) {
		if (this.laddersCount < 10) {
			this.ladderArray[this.laddersCount] = newLadder;
			this.laddersCount++;
		}
	}

	public void add(SnakePlayer sp) {
		if (getSnakePlayerCount() < 10) {
			this.snakePlayerList.add(sp);
			setSnakePlayerCount(getSnakePlayerCount() + 1);
			
			// also add the snake player to gameEngine list of players
			this.gameEngine.add(sp);
		}
	}

}