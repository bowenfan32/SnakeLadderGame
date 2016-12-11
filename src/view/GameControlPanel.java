package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import controller.SelectPieceToMoveActionListener;
import model.GameEngine;

public class GameControlPanel extends JPanel {


	private GameEngine gameEngineImpl;
	private GameAppFrame gameAppFrame;

	private JLabel playerNameDisplayLabel;
	private JLabel diceResultLabel;

	private JPanel startGameButton;
	private JButton movePiece1Button;
	private JButton movePiece2Button;
	private JButton movePiece3Button;

	public GameControlPanel(GameEngine gameEngineImpl, GameAppFrame gameAppFrame) {

		this.gameEngineImpl = gameEngineImpl;
		this.gameAppFrame = gameAppFrame;

		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		JLabel playNameLabel = new JLabel("Player's name:");
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = 0.5;
		gbc.weighty = 0.3;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.LINE_END;
		this.add(playNameLabel, gbc);

		this.playerNameDisplayLabel = new JLabel("");
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = 0.5;
		gbc.weighty = 0.3;
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.insets = new Insets(0,10,0,0);
		gbc.anchor = GridBagConstraints.LINE_START;
		this.add(this.playerNameDisplayLabel, gbc);

		JLabel playerRolledLabel = new JLabel("You rolled:");
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = 0.6;
		gbc.weighty = 0.6;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.LINE_END;
		this.add(playerRolledLabel, gbc);

		this.diceResultLabel = new JLabel("");
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = 0.5;
		gbc.weighty = 0.5;
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.insets = new Insets(0,10,0,0);
		gbc.anchor = GridBagConstraints.LINE_START;
		this.add(this.diceResultLabel, gbc);


		JLabel pieceSelectionLabel = new JLabel("Select your piece to move:");
		gbc.fill = GridBagConstraints.NONE;
		gbc.weighty = 0.2;
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.CENTER;
		this.add(pieceSelectionLabel, gbc);


		JPanel pieceSelectionPanel = new JPanel();
		pieceSelectionPanel.setLayout(new FlowLayout());

		JButton movePiece1Button = new JButton("Piece 1");
		movePiece1Button.setEnabled(true);
		movePiece1Button.addActionListener(new SelectPieceToMoveActionListener(this.gameEngineImpl, this.gameAppFrame));

		JButton movePiece2Button = new JButton("Piece 2");
		movePiece2Button.setEnabled(false);
		//movePiece2Button.addActionListener(new SelectPieceToMoveActionListener(this.gameEngineImpl, this.gameAppFrame));

		JButton movePiece3Button = new JButton("Piece 3");
		movePiece3Button.setEnabled(false);
		//movePiece3utton.addActionListener(new SelectPieceToMoveActionListener(this.gameEngineImpl, this.gameAppFrame));

		pieceSelectionPanel.add(movePiece1Button);
		pieceSelectionPanel.add(movePiece2Button);
		pieceSelectionPanel.add(movePiece3Button);

		gbc.fill = GridBagConstraints.REMAINDER;
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 2;
		this.add(pieceSelectionPanel, gbc);

		gbc.fill = GridBagConstraints.REMAINDER;
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 2;
		this.add(Box.createVerticalStrut(500) , gbc);

	}

	public void setCurrentPlayersName(String name) {
		this.playerNameDisplayLabel.setText(name);
		this.invalidate();

	}

	public void setCurrentPlayersDiceResult(int diceValue) {
		this.diceResultLabel.setText(Integer.toString(diceValue));
		this.invalidate();
	}

}
