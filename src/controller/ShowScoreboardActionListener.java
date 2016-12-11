package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingUtilities;

import model.GameEngine;
import view.GameAppFrame;

	public class ShowScoreboardActionListener implements ActionListener {

		private GameEngine gameEngineImpl;
		private GameAppFrame frame;
		
		public ShowScoreboardActionListener(GameEngine gameEngineImpl, GameAppFrame frame) {
			this.gameEngineImpl = gameEngineImpl;
			this.frame = frame;
		}


		@Override
		public void actionPerformed(ActionEvent e) {
			
				//game can start, valid game setup
				this.gameEngineImpl.showScore();
				
				SwingUtilities.invokeLater( new Runnable() {

					@Override
					public void run() {
						frame.updateFrame();
					}
				});

			
			
		}

	}
