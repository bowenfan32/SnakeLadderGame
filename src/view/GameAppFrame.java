package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.GameEngine;
import model.interfaces.GameEngineCallback;

public class GameAppFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private static final int MinWindowWidth = 1180;
	private static final int MinWindowHeight = 900;

	//private Container contentPane;
	private GameEngineCallback callBack;
	private GameEngine gameEngineImpl;
	//private GamePlayManager gamePlayManager;

	protected StatusBar stausBar;
	private BoardPanel gameBoardPanel;
	private GameControlPanel gameControlsPanel;
	private MainOptionsPanel mainPanel;

	public GameAppFrame(String title, GameEngine gameEngineImpl) throws HeadlessException {
		super(title);

		this.gameEngineImpl = gameEngineImpl;
		//this.callBack = new GUIClientGameEngineCallback(this);
		//this.gameEngineImpl.addGameEngineCallback(this.callBack);

		//toolBar = new ToolBar("Game Toolbar", this.gameEngineImpl, this);
		this.stausBar = new StatusBar();
		this.gameBoardPanel = new BoardPanel(this.gameEngineImpl.getBoardModel());
		this.gameControlsPanel = new GameControlPanel(this.gameEngineImpl, this);

		setBounds(250, 250, MinWindowWidth, MinWindowHeight);
		setMinimumSize(getSize());
		setLayout(new BorderLayout());

		add(stausBar, BorderLayout.SOUTH);
		setJMenuBar(new MenuBar(this.gameEngineImpl, this));

		this.mainPanel = new MainOptionsPanel(this.gameEngineImpl, this);

		this.updateFrame();

		//this.contentPane = getContentPane();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void updateFrame() {

		BorderLayout layout = (BorderLayout) this.getContentPane().getLayout();

		//tempFrame.removeAll();


		if (this.gameEngineImpl.getGameInProgress()) {
//		if (true) {
			// game is underway
			Component tempComponent = layout.getLayoutComponent(BorderLayout.CENTER);
			if (tempComponent != null) {
				this.remove(tempComponent);
			}

			// add game panels
			this.add(this.gameControlsPanel, BorderLayout.EAST);
			this.add(this.gameBoardPanel, BorderLayout.CENTER);

			//tempFrame.validate();

		} else {
			// no game currently in progress
			// remove other frames that may be being displayed
			Component tempComponent = layout.getLayoutComponent(BorderLayout.EAST);
			if (tempComponent != null) {
				this.remove(tempComponent);
			}
			tempComponent = layout.getLayoutComponent(BorderLayout.CENTER);
			if (tempComponent != null) {
				this.remove(tempComponent);
			}

			// add game panels
			this.add(mainPanel, BorderLayout.CENTER);


		}

		this.invalidate();
		this.repaint();
	}

	public void updatePlayerList() {
		this.mainPanel.updatePlayerList();
	}

	public void enableStartGameButton() {
		this.mainPanel.enableStartButton();

	}

	public void setCurrentPlayersName(String name) {
		this.gameControlsPanel.setCurrentPlayersName(name);

	}

	public void setCurrentPlayersDiceResult(int diceValue) {
		this.gameControlsPanel.setCurrentPlayersDiceResult(diceValue);

	}
}
