package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.GameEngine;
import view.GameAppFrame;
import view.LoginDialogueBox;
import view.RegisterDialogueBox;

public class RegisterPlayerActionListener implements ActionListener {

	private GameEngine gameEngineImpl;
	private GameAppFrame frame;
	
	public RegisterPlayerActionListener(GameEngine gameEngineImpl, GameAppFrame frame) {
		this.gameEngineImpl = gameEngineImpl;
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// this gets called when the button or menu item for registering a player is pressed 
		
		
		RegisterDialogueBox registerDlg = new RegisterDialogueBox(this.frame, this.gameEngineImpl);
		registerDlg.setVisible(true);
        // if logon successfully
        if(registerDlg.isSucceeded()){
            // nothing to do, will be handled by the GameEngine
        
        }
        
	}

}
