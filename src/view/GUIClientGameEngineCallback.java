package view;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import model.BoardModel;
import model.GameEngine;
import model.SnakePlayer;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;

public class GUIClientGameEngineCallback implements GameEngineCallback {

	private GameAppFrame gameAppFrame;

	public GUIClientGameEngineCallback(GameAppFrame gameAppFrame) {
		this.gameAppFrame = gameAppFrame;
	}

	@Override
	public void logThis(String string) {
		//gameAppFrame.stausBar.setStatus(String.format("Log: %s\n", string));
		JOptionPane.showMessageDialog(this.gameAppFrame,
				string,
                "Ok",
                JOptionPane.INFORMATION_MESSAGE);
		
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
		gameAppFrame.stausBar.setStatus(String.format("%s now registered and added to the game", playerName));

	}

	@Override
	public void addPlayerToGame() {
		// TODO Auto-generated method stub

	}

	@Override
	public void playerAddedToGame(String playerName) {
		/* JOptionPane.showMessageDialog(this.gameAppFrame,
                "Hi " + playerName + "! You have successfully registered for the game.",
                "Login",
                JOptionPane.INFORMATION_MESSAGE);
        */

		gameAppFrame.stausBar.setStatus(String.format("%s added to the game", playerName));
		this.gameAppFrame.updatePlayerList();
	}

	@Override
	public void enableGameStart() {
		this.gameAppFrame.enableStartGameButton();
		gameAppFrame.stausBar.setStatus(String.format("Game setup now valid, press 'Start Game' to begin"));
	}

	@Override
	public void disableGameStart() {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeSnakePlayer(SnakePlayer snakePlayer) {
		// TODO Auto-generated method stub

	}

	@Override
	public void nextPlayersTurn(Player player) {
		this.gameAppFrame.setCurrentPlayersName(player.getName());

	}

	@Override
	public void diceRolled(int diceValue) {
		this.gameAppFrame.setCurrentPlayersDiceResult(diceValue);
		gameAppFrame.stausBar.setStatus(String.format("Dice rolled: %d", diceValue));
	}

	@Override
	public void movePiece(Player player, int pieceId, int position) {
		// TODO Auto-generated method stub

	}

	@Override
	public void gameEndedWithWinner(String winnersName) {
		JOptionPane.showMessageDialog(this.gameAppFrame,
                "Congradulations " + winnersName + "! You have won the game.",
                "Ok",
                JOptionPane.INFORMATION_MESSAGE);
		this.gameAppFrame.updateFrame();
	}

	@Override
	public void gameQuitBeforeCompletion() {
		JOptionPane.showMessageDialog(this.gameAppFrame,
                "Game exited before finish. Returning to menu now.",
                "Ok",
                JOptionPane.INFORMATION_MESSAGE);
		this.gameAppFrame.updateFrame();

	}

	@Override
	public void playerNeedsToSelectPieceToMove(Player player, GameEngine gameEngine) {
		// TODO Auto-generated method stub

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
