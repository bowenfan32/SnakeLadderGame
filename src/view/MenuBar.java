package view;

import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controller.AddPlayerActionListener;
import controller.QuitActionListener;
import controller.RegisterPlayerActionListener;
import controller.ShowScoreboardActionListener;
import controller.StartGameActionListener;
import model.GameEngine;

public class MenuBar extends JMenuBar {

	private GameEngine gameEngineImpl;
	private GameAppFrame frame;
	
	private JMenu gameMenu;
	private JMenuItem addPlayer;
	private JMenuItem registerPlayer;
	private JMenuItem startGame;
	
	private JMenu scoreboardMenu;
	private JMenuItem showScoreboard;
	
	private JMenu quitMenu;
	private JMenuItem quitGame;
	
	   
	public MenuBar(GameEngine gameEngineImpl, GameAppFrame frame) {
		
		this.gameEngineImpl = gameEngineImpl;
		this.frame = frame;
		
		// game
		gameMenu = new JMenu("Game");
		this.add(gameMenu);

		addPlayer = new JMenuItem("Join Game");
		addPlayer.setMnemonic(KeyEvent.VK_A);
		addPlayer.addActionListener(new AddPlayerActionListener(this.gameEngineImpl, this.frame));
		gameMenu.add(addPlayer);
		

		registerPlayer = new JMenuItem("Register Player");
		registerPlayer.setMnemonic(KeyEvent.VK_R);
		registerPlayer.addActionListener(new RegisterPlayerActionListener(this.gameEngineImpl, this.frame));
		gameMenu.add(registerPlayer);
		
		
		startGame = new JMenuItem("Start Game");
		startGame.setMnemonic(KeyEvent.VK_S);
		startGame.addActionListener(new StartGameActionListener(this.gameEngineImpl, this.frame));
		gameMenu.add(startGame);
		
		
		// scoreboard
		scoreboardMenu = new JMenu("Scroreboard");
		this.add(scoreboardMenu);
		
		showScoreboard = new JMenuItem("Show Scoreboard");
		showScoreboard.setMnemonic(KeyEvent.VK_B);
		// TODO add actionListener
		showScoreboard.addActionListener(new ShowScoreboardActionListener(this.gameEngineImpl, this.frame));
		scoreboardMenu.add(showScoreboard);
		
		
		// quit
		quitMenu = new JMenu("Quit");
		this.add(quitMenu);
		
		quitGame = new JMenuItem("Quit Game");
		quitGame.setMnemonic(KeyEvent.VK_Q);
		quitMenu.addActionListener(new QuitActionListener());
		quitMenu.add(quitGame);
		
	}

}
