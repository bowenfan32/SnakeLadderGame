package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;

import com.sun.xml.internal.ws.api.Component;

import controller.AddPlayerActionListener;
import controller.RegisterPlayerActionListener;
import controller.StartGameActionListener;
import controller.CustomiseGameActionListener;

import model.GameEngine;

public class MainOptionsPanel extends JPanel {

	private GameEngine gameEngineImpl;
	private GameAppFrame gameAppFrame;

	private JPanel internalFrame;
	private JList<String> playerList;

	private JButton joinGameButton;
	private JButton registerButton;
	private JButton startGameButton;
	private JButton customiseGameButton;

	public MainOptionsPanel(GameEngine gameEngineImpl, GameAppFrame gameAppFrame) {

		this.internalFrame = new JPanel(new BorderLayout());
		this.gameEngineImpl = gameEngineImpl;
		this.gameAppFrame = gameAppFrame;

		setLayout(new GridBagLayout());

		int frameHeight = (int)Math.rint(this.gameAppFrame.getSize().height*0.75);
		int frameWidth = (int)Math.rint(this.gameAppFrame.getSize().width*0.75);

		this.internalFrame.setSize(new Dimension(frameHeight, frameWidth));
		this.internalFrame.setBorder(BorderFactory.createTitledBorder(this.getBorder(), "New Game", 0, 0, new Font("Dialog", Font.BOLD, 12), Color.BLACK));

		// add player list (left)
		this.playerList = new JList<String>();
		this.playerList.setModel(this.gameEngineImpl.getPlayersNamesList());
		JScrollPane listScroller = new JScrollPane(this.playerList);
		listScroller.setPreferredSize(new Dimension(100, 180));
		JPanel leftPanel = new JPanel();
		// leftPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN));
		leftPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 30));
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));
		JLabel playersLabel = new JLabel("Players");
		playersLabel.setAlignmentX(LEFT_ALIGNMENT);
		leftPanel.add(playersLabel);
		leftPanel.add(listScroller);
		this.internalFrame.add(leftPanel, BorderLayout.WEST);

		// add buttons to right
		JPanel rightPanel = new JPanel();
		// rightPanel.setBorder(BorderFactory.createLineBorder(Color.RED));
		rightPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 20));
		rightPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.HORIZONTAL;

		// Join Button
		this.joinGameButton = new JButton("Join Game");
		this.joinGameButton.setAlignmentX(CENTER_ALIGNMENT);
		this.joinGameButton.addActionListener(new AddPlayerActionListener(this.gameEngineImpl, this.gameAppFrame));

		// Register Button
		this.registerButton = new JButton("Register");
		this.registerButton.setAlignmentX(CENTER_ALIGNMENT);
		this.registerButton.addActionListener(new RegisterPlayerActionListener(this.gameEngineImpl, this.gameAppFrame));

		// Start Game Button
		this.startGameButton = new JButton("Start Game");
		this.startGameButton.setAlignmentX(CENTER_ALIGNMENT);
		this.startGameButton.addActionListener(new StartGameActionListener(this.gameEngineImpl, this.gameAppFrame));

		///Customise Game Button
		
				this.customiseGameButton = new JButton("Customise Game");
				this.customiseGameButton.setAlignmentX(CENTER_ALIGNMENT);
				this.customiseGameButton.addActionListener(new CustomiseGameActionListener(this.gameEngineImpl, this.gameAppFrame));


		rightPanel.add(this.joinGameButton, gbc);
		rightPanel.add(Box.createRigidArea(new Dimension(0,5)), gbc);
		rightPanel.add(this.registerButton, gbc);
		rightPanel.add(Box.createRigidArea(new Dimension(0,5)), gbc);
		rightPanel.add(this.startGameButton, gbc);
		rightPanel.add(this.customiseGameButton, gbc);
		rightPanel.add(Box.createRigidArea(new Dimension(0,5)), gbc);
		this.internalFrame.add(rightPanel, BorderLayout.EAST);

		add(this.internalFrame);


	}
	
	public void updatePlayerList() {
		this.playerList.setModel(this.gameEngineImpl.getPlayersNamesList());
	}

	public void enableStartButton() {
		this.startGameButton.setEnabled(true);
		this.invalidate();
	}
}
