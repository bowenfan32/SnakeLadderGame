package controller;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingUtilities;

import model.GameEngine;
import view.GameAppFrame;

public class StartGameActionListener implements ActionListener {

	private GameEngine gameEngineImpl;
	private GameAppFrame frame;

	public StartGameActionListener(GameEngine gameEngineImpl, GameAppFrame frame) {
		this.gameEngineImpl = gameEngineImpl;
		this.frame = frame;
	}


	@Override
	public void actionPerformed(ActionEvent e) {

		if (this.gameEngineImpl.gameAbleToStart()) {
			//game can start, valid game setup
			this.gameEngineImpl.startGame();

			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {
					frame.updateFrame();

				}
			});

		} else {
			// do nothing, button should be non-responsive (or display message?)
		}


	}

}
