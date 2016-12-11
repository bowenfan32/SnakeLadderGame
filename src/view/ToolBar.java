package view;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JToolBar;

import model.GameEngine;

public class ToolBar extends JToolBar {

	private GameEngine gameEngineImpl;
	private GameAppFrame frame;
	
	private JButton addPlayer = new JButton("Add player");
	private JButton startRoundButton = new JButton("Deal round");
	private JComboBox gameSpeedSelector;
	
	public ToolBar(String name, GameEngine gameEngineImpl, GameAppFrame frame) {
		super(name);
		
		this.gameEngineImpl = gameEngineImpl;
		this.frame = frame;
		
		String[] speedStrings = { "Slow", "Medium", "Fast"};
		this.gameSpeedSelector = new JComboBox<String>(speedStrings);
		this.gameSpeedSelector.setSelectedIndex(0);
		
//		this.gameSpeedSelector.addActionListener(new SpeedComboActionListener(this.frame));
		
		// addPlayer.addActionListener(new button1ActionListener);
//		addPlayer.addActionListener(new AddPlayerActionListener(this.gameEngineImpl, this.frame));
//		startRoundButton.addActionListener(new StartRoundActionListener(this.gameEngineImpl, this.frame));
		
		add(addPlayer);
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(new JLabel("Game speed:"));
		add(this.gameSpeedSelector);
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(startRoundButton);
		add(Box.createRigidArea(new Dimension(10, 10)));
	}

}
