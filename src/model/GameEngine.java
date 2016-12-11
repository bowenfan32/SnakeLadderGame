package model;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;
import java.util.logging.Level;
import java.util.stream.Collectors;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListModel;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import model.Dice;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;

public class GameEngine {

	private static final int MIN_NUM_PLAYERS = 2;
	static final int PIECETOMOVENOTSELECTED = -323323749;
	private Boolean gameInProgress = false;
	private ArrayList<GameEngineCallback>gameEngineCallbacks = new ArrayList<GameEngineCallback>();

	private MySQLAccess userDatabaseManager = new MySQLAccess();

	// game play variables:
	private BoardModel boardModel;
	private ArrayList<Player> players = new ArrayList<Player>();
	//private int playerCount = 0;
	private Dice dice;
	private int currentPlayerIndex = 0;
	protected int selectedPieceToMove = PIECETOMOVENOTSELECTED;
	//private int pos1 = -1;
	private DefaultListModel<String> playerNamesModel; // used by 'signup' list in mainOptionsPanel


	boolean snakeWins = false;
	private boolean waitingOnUserToSelectPiece = false;
	protected boolean gameWon = false;
	protected boolean exitGame = false;


	static Scanner scan = new Scanner(System.in);

	public GameEngine() {
		super();
		this.boardModel = createBoard();
		this.dice = createDice();


		/* do nothing now

		char ch;
		do {
			ch = displayMenu();
			switch (ch) {
			case '1':
				setupGame();
				break;
			case '2':
				this.newGame.customiseBoard();
				break;
			case '3':
				this.registerPlayer();
				break;
			}
		} while (ch != '4');

		 */
	}

	private Dice createDice() {

		dice = new Dice();
		return dice;

	}

	private BoardModel createBoard() {
		return new BoardModel(this);
	}

	public void addGameEngineCallback(GameEngineCallback callBack) {
		this.getGameEngineCallbacks().add(callBack);

	}

	public Boolean getGameInProgress() {
		return this.gameInProgress;

	}

	/*
	public char displayMenu() {
		System.out.println("******* Snakes Menu ********");
		System.out.println("Play Game       : 1");
		System.out.println("Customize Board : 2");
		System.out.println("Register        : 3");
		System.out.println("Exit            : 4");

		System.out.println("Enter 1/2/3/4     : ");
		System.out.println("**************************");

		do {
			String input = scan.nextLine();
			if (input.length() > 0) {
				return input.charAt(0);
			}
		} while(true);


	}
	 */

	public void startGame() {

		// start the game
		this.gameInProgress = true;

		this.play();


	}

	public void pieceToMoveSelectedByUser(int pieceIndex) {
		this.selectedPieceToMove = pieceIndex;
		this.waitingOnUserToSelectPiece = false;
		this.play();
	}

	public void play () {

		Runnable game = new Runnable() {
			@Override
			public void run() {

				/* run the game loop until user input is required
				 * or the game is won, or game quit is called
				 * gameInProgress
				 * waitingOnUserToSelectPiece
				 * currentPlayer
				 * selectedPiece
				 * gameWon
				 * exitGame
				 *
				 * if not waiting for a piece to be selected, game is not won, exitGame is not true
				 *
				 */

				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				while (!waitingOnUserToSelectPiece && !gameWon && !exitGame) {

					// variable to track new position of piece (if moved)
					int newPosition = -1;

					// if the user has just selected their piece
					// then waitingOnUserToSelectPiece == false && selectedPiece != PIECETOMOVENOTSELECTED
					if (selectedPieceToMove != PIECETOMOVENOTSELECTED) {
						// don't advance yet, the current player needs to move
						if (players.get(currentPlayerIndex) instanceof HumanPlayer) {
							// human player
							((HumanPlayer)players.get(currentPlayerIndex)).setPieceToMove(selectedPieceToMove);
							newPosition = players.get(currentPlayerIndex).move(dice.getValue());
							// notify ui's that a move has been made
							for (GameEngineCallback callback : getGameEngineCallbacks()) {
								callback.movePiece(players.get(currentPlayerIndex), 1, players.get(currentPlayerIndex).getPos());
							}

							// selection has been used
							selectedPieceToMove = PIECETOMOVENOTSELECTED;

						} else {
							// error
							System.out.println("GameEngine.play() unexpected class found");
							selectedPieceToMove = PIECETOMOVENOTSELECTED; // stop infinite loop occuring
						}
					} else {
						// only if there hasn't been a turn this loop proceed here
						// there should only be one .move() per loop

						// now next players turn
						nextPlayersTurn();

						// 'roll' the dice
						dice.getNewDiceValue();

						// display the new dice value
						for (GameEngineCallback callback : getGameEngineCallbacks()) {
							callback.diceRolled(dice.getValue());
						}

						// check if the game needs to get a human player to select which piece they want to move
						if (players.get(currentPlayerIndex) instanceof HumanPlayer) {
							// human player
							// stop the game play to allow the user to select the piece to move
							waitingOnUserToSelectPiece = true;
							for (GameEngineCallback callback : getGameEngineCallbacks()) {
								callback.playerNeedsToSelectPieceToMove(players.get(currentPlayerIndex), GameEngine.this);
							}
						} else if (players.get(currentPlayerIndex) instanceof SnakePlayer) {
							// snake player
							// should be false, but just to be sure
							waitingOnUserToSelectPiece = false;
							newPosition = players.get(currentPlayerIndex).move(dice.getValue());

							// notify ui's that a move has been made
							for (GameEngineCallback callback : getGameEngineCallbacks()) {
								callback.movePiece(players.get(currentPlayerIndex), 1, players.get(currentPlayerIndex).getPos());
							}

						} else {
							// error
							System.out.println("GameEngine.play() unexpected class found");
						}

					}

					if (newPosition == 100) {
						gameWon = true;
					}




					// if game is won
					if (gameWon) {

						gameInProgress = false;
						
						// update the UI that a player has won the game
						for (GameEngineCallback callback : getGameEngineCallbacks()) {
							callback.gameEndedWithWinner(players.get(currentPlayerIndex).getName());
						}

						if (players.get(currentPlayerIndex) instanceof HumanPlayer) {
							// the winner was a human player
							try {
								userDatabaseManager.updateWinningScore(players.get(currentPlayerIndex).getName());
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} else if (players.get(currentPlayerIndex) instanceof SnakePlayer) {
							// a snake player won the game
							// update the score for all loosers of the game
							for (int i = 0; i < players.size(); i++){
								if (players.get(i) instanceof HumanPlayer) {
									// only update the human players
									String loser = players.get(i).getName();
									try {
										userDatabaseManager.updateLosingScore(loser);
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}

							}
						} else {
							// error
							System.out.println("GameEngine.play() unexpected class found");
						}

						// game over, current player has won
						gameFinished();

						break;
					}


					// if game is exited before winner
					if (exitGame) {
						gameInProgress = false;
						
						// update the UI's that the game has ended
						for (GameEngineCallback callback : getGameEngineCallbacks()) {
							callback.gameQuitBeforeCompletion();
						}

						// reset the game
						gameFinished();

						break;
					}

				}

			}

		};

		Thread gameThread = new Thread(game);
		gameThread.start();

	}

	protected void gameFinished() {
		
		this.players = new ArrayList<Player>();
		this.currentPlayerIndex = 0;
		this.playerNamesModel = null;
		this.boardModel = null;
		this.createBoard();

	}
	
	public void showScore() {
		
		// System.out.println(result.getString(1));
		try {
			ResultSet result;
			String returnedScore;
			List<String> results = new ArrayList<String>();
			result = userDatabaseManager.displayScore();
//			if (result.next()) {
//				returnedScore = results.add(result.getString(1));
//				
//			}
			for (int i = 0; i < results.size(); i++){
			System.out.println(results.get(i));
			}
			
			JTable table = new JTable(buildTableModel(result));
			JOptionPane.showMessageDialog(null, new JScrollPane(table),"Scoreboard",JOptionPane.PLAIN_MESSAGE);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static DefaultTableModel buildTableModel(ResultSet rs)
	        throws SQLException {

	    ResultSetMetaData metaData = rs.getMetaData();

	    // names of columns
	    Vector<String> columnNames = new Vector<String>();
	    int columnCount = metaData.getColumnCount();
	    for (int column = 1; column <= columnCount; column++) {
	        columnNames.add(metaData.getColumnName(column));
	    }

	    // data of the table
	    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	    while (rs.next()) {
	        Vector<Object> vector = new Vector<Object>();
	        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
	            vector.add(rs.getObject(columnIndex));
	        }
	        data.add(vector);
	    }

	    return new DefaultTableModel(data, columnNames);

	}

	public Error addPlayer(String username, String password) {

		ResultSet result;
		Error addPlayerError = null;

		// Query database, find matching username
		try {
			String returnedPassword;
			result = userDatabaseManager.login(username, password);
			// System.out.println(result.getString(1));
			if (result.next()) {
				returnedPassword = result.getString(1);
				if (returnedPassword.equals(password)) {
					this.addAuthenticatedPlayer(username);
					addPlayerError = null; // not necessary, just being explicit
				} else {
					System.out.println("login failed!!!!!");
					addPlayerError = new Error("The username or password was not found");
				}
			} else {
				System.out.println("login failed!!!!!");
				addPlayerError = new Error("The user was not found");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			addPlayerError = new Error(e.getMessage());
		}

		return addPlayerError;
	}

	private void addAuthenticatedPlayer(String username) {

		//this.playerCount = this.playerCount+1;
		//this.players.add(new HumanPlayer(this.board, this.players.size()-1, 1, username, 0));
		//this.players[this.playerCount-1] = ;

		this.players.add(new HumanPlayer(this.boardModel, this.players.size(), 1, username, 0));

		ArrayList<HumanPlayer> hPlayers = (ArrayList<HumanPlayer>) this.players
	        .stream()
	        .filter(p -> p instanceof HumanPlayer)
	        .map(p -> (HumanPlayer) p)
	        .collect(Collectors.toList());


		this.boardModel.addPlayers(hPlayers);

		for (GameEngineCallback callback : this.getGameEngineCallbacks()) {
			callback.playerAddedToGame(username);
		}

		if (this.gameAbleToStart()) {
			for (GameEngineCallback callback : this.getGameEngineCallbacks()) {
				callback.enableGameStart();
			}
		}
	}

	public Error registerPlayer(String username, String password) {
		/*
		 * System.out.println("Please enter a username: ");
		 * String username = scan.next();
		 * System.out.println("Please set a new password");
		 * String password = scan.next();
		 */

		Error registerPlayerError = null;

		//MySQLAccess sql = new MySQLAccess();
		try {
			this.userDatabaseManager.register(username, password);
		} catch (MySQLIntegrityConstraintViolationException e) {
//			System.out.println("Username already exists!\n");
			registerPlayerError = new Error("Username already found in player list!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			registerPlayerError = new Error(e.getMessage());
		}

		return registerPlayerError;
	}

	private void removeSnakePlayers(Snake snakeParent) {

		/* for each player, check if they are a snakePlayer,
		 * if so, check if their snakeParent matches the passed
		 * in snakeParent, and if it does, tell the callbacks to
		 * remove it, then also remove it from the players list
		 */

		SnakePlayer snakePlayer = null;

		for (GameEngineCallback callback : this.getGameEngineCallbacks()) {
			callback.removeSnakePlayer(snakePlayer);
		}
	}


	public ListModel<String> getPlayersNamesList() {

		this.playerNamesModel = new DefaultListModel<String>();
		if (this.players.size() != 0) {
			int i = 0;
			while (i < this.players.size()) {
				this.playerNamesModel.addElement(this.players.get(i).getName());
				i++;
			}
		}

		return this.playerNamesModel;
	}

	private void customiseBoard() {
		for (GameEngineCallback callback : this.getGameEngineCallbacks()) {
			callback.customiseBoard(this.boardModel);
		}
	}
	
	//NEW CUSTOM CHANGE********************
	//NEW CUSTOM CHANGE********************
	
	public void customizeSNNum(int x) {
		this.boardModel.customizeSNNum(x);
	}
	public void customizeLDNum(int x) {
		this.boardModel.customizeLDNum(x);
	}
	public void customizeSNLX(int x) {
		this.boardModel.customizeSNLX(x);
	}
	public void customizeSNLY(int x) {
		this.boardModel.customizeSNLY(x);
	}
	//NEW CUSTOM CHANGE********************
	//NEW CUSTOM CHANGE********************

	private void nextPlayersTurn() {

		// TODO: only advance to the next player if the current role wasn't a 6

		if (this.dice.getValue() == 6) {
			for (GameEngineCallback callback : this.getGameEngineCallbacks()) {
				callback.logThis("You rolled a 6, have another turn");
			}
			
		} else {
			this.currentPlayerIndex++;
			if (this.currentPlayerIndex == this.players.size()) {
				this.currentPlayerIndex = 0;
			}
		}

		for (GameEngineCallback callback : this.getGameEngineCallbacks()) {
			callback.nextPlayersTurn(this.players.get(currentPlayerIndex));
		}



	}

	public BoardModel getBoardModel() {
		return this.boardModel;
	}

	public Boolean gameAbleToStart() {
		return this.players.size() >= MIN_NUM_PLAYERS;
	}

	public ArrayList<GameEngineCallback> getGameEngineCallbacks() {
		return gameEngineCallbacks;
	}

	public void setGameEngineCallbacks(ArrayList<GameEngineCallback> gameEngineCallbacks) {
		this.gameEngineCallbacks = gameEngineCallbacks;
	}

	public void add(SnakePlayer newSnakePlayer) {

		// add to the local list of players
		// don't add to the boardModels list of snake players, as this method is called from that function
		
		this.players.add(newSnakePlayer);
	}

}
