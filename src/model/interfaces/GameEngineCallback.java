package model.interfaces;

import model.BoardModel;
import model.GameEngine;
import model.SnakePlayer;
import view.BoardPanel;
import view.HumanPlayerView;

public interface GameEngineCallback
{

	public void logThis(String string);

	public void customiseBoard(BoardModel boardData);

	public void registerNewPlayer();

	public void playerRegistered(String playerName);

	public void addPlayerToGame();

	public void playerAddedToGame(String playerName);

	public void enableGameStart();

	public void disableGameStart();

	public void removeSnakePlayer(SnakePlayer snakePlayer);

	public void nextPlayersTurn(Player player);

	public void diceRolled(int diceValue);

	public void playerNeedsToSelectPieceToMove(Player player, GameEngine gameEngine);

	public void movePiece(Player player, int pieceId, int position);

	public void gameEndedWithWinner(String winnersName);

	public void gameQuitBeforeCompletion();
	
	//NEW CUSTOM CHANGE********************
	//NEW CUSTOM CHANGE********************
	public void customizeSNNum(int x);
	
	public void customizeLDNum(int x);
	
	public void customizeSNLX(int x);
	
	public void customizeSNLY(int x);
	//NEW CUSTOM CHANGE********************
	//NEW CUSTOM CHANGE********************


}
