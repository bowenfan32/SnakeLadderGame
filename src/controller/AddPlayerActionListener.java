package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

import javax.swing.JOptionPane;

import model.GameEngine;
import view.GameAppFrame;
import view.LoginDialogueBox;

public class AddPlayerActionListener implements ActionListener {

	private GameEngine gameEngineImpl;
	private GameAppFrame frame;

	public AddPlayerActionListener(GameEngine gameEngineImpl, GameAppFrame frame) {
		this.gameEngineImpl = gameEngineImpl;
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// this gets called when the button or menu item for adding a player is pressed

		LoginDialogueBox loginDlg = new LoginDialogueBox(this.frame, this.gameEngineImpl);
        loginDlg.setVisible(true);
        // if logged in successfully
        if(loginDlg.isSucceeded()){
            // nothing to do, will be handled by the GameEngine callbacks
        	//gameEngineImpl.addPlayer(loginDlg.getUsername(), loginDlg.getPassword());
        }

	}

}
