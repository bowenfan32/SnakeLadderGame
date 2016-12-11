package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingUtilities;

import model.GameEngine;
import view.GameAppFrame;

public class SelectPieceToMoveActionListener implements ActionListener {

	private GameEngine gameEngineImpl;
	private GameAppFrame frame;

	public SelectPieceToMoveActionListener(GameEngine gameEngineImpl, GameAppFrame frame) {
		this.gameEngineImpl = gameEngineImpl;
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		this.gameEngineImpl.pieceToMoveSelectedByUser(1);

	}

}
