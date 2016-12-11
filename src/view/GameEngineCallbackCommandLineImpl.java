package view;

import java.util.ArrayList;

import model.BoardModel;
import model.GameEngine;
import model.SnakePlayer;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;


public class GameEngineCallbackCommandLineImpl implements GameEngineCallback {

	public GameEngineCallbackCommandLineImpl() {
		super();
	}

	@Override
	public void logThis(String string) {
		System.out.println(string);

	}

	@Override
	public void customiseBoard(BoardModel board) {
		// TODO Auto-generated method stub

	}

	@Override
	public void registerNewPlayer() {
		// TODO Auto-generated method stub

	}

	@Override
	public void playerRegistered(String playerName) {
		System.out.println(String.format("%s registered to play the game", playerName));

	}

	@Override
	public void addPlayerToGame() {
		// TODO Auto-generated method stub

	}

	@Override
	public void playerAddedToGame(String playerName) {
		System.out.println(playerName + " added to the new game.");

	}

	@Override
	public void enableGameStart() {
		System.out.println("Game setup now valid, type 'Start' at any time to begin the game.");

	}

	@Override
	public void disableGameStart() {
		System.out.println("Game setup now invalid, please configure the game to start.");

	}

	@Override
	public void removeSnakePlayer(SnakePlayer snakePlayer) {
		System.out.println(String.format("SnakePlayer %s removed from the game", snakePlayer.getName()));

	}

	@Override
	public void diceRolled(int diceValue) {
		System.out.println("Dice rolled: " + Integer.toString(diceValue));

	}


	@Override
	public void gameEndedWithWinner(String winnersName) {
		System.out.println("**** GAME OVER " + winnersName + " is the winner ******");

	}

	@Override
	public void gameQuitBeforeCompletion() {
		System.out.println(String.format("Quit game called, exiting game now - there was no winner"));


	}

	@Override
	public void nextPlayersTurn(Player player) {
		System.out.println(String.format("Next player up: %s's turn", player.getName()));

	}

	@Override
	public void playerNeedsToSelectPieceToMove(Player player, GameEngine gameEngine) {
		System.out.println(String.format("Please select the token you would like to move:"));

	}

	@Override
	public void movePiece(Player player, int pieceId, int position) {
		System.out.println(String.format("Piece #%d moved for %s to position %d", pieceId, player.getName(), position));

	}

	@Override
	public void customizeSNNum(int x) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void customizeLDNum(int x) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void customizeSNLX(int x) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void customizeSNLY(int x) {
		// TODO Auto-generated method stub
		
	}


}
