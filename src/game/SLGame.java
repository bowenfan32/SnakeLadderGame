/* Written by Charles Theva as part of the Introductory Programming Course  */
/* You are free to refactor and modify the program 							*/
package game;

import model.GameEngine;
import view.GUIClientGameEngineCallback;
import view.GameAppFrame;
import view.GameEngineCallbackCommandLineImpl;

// The main system level class
public class SLGame {

	public static void main(String[] args) {

		final GameEngine gameEngine = new GameEngine(); /* gameEngine manages the creation and running of SL games */

		GameAppFrame appFrame = new GameAppFrame("Green Team Snakes and Ladders", gameEngine); /* this is the frame that is UI for the game */

		// these are the connectors between the logic (gameEngine) and the UI (command line and appFrame)
		// add command line callback
		gameEngine.addGameEngineCallback(new GameEngineCallbackCommandLineImpl());
		// add GUI callback
		gameEngine.addGameEngineCallback(new GUIClientGameEngineCallback(appFrame));

		// show the appFrame
		appFrame.setVisible(true);
	}
}
