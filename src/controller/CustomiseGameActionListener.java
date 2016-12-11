package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import model.GameEngine;
import view.GameAppFrame;
//import view.LoginDialogueBox;
import view.CustomiseDialogueBox; // can we change this to input? Num snakes? num tokens?


public class CustomiseGameActionListener implements ActionListener {
	

	private GameEngine gameEngineImpl;
	private GameAppFrame frame;
	
	public CustomiseGameActionListener(GameEngine gameEngineImpl, GameAppFrame frame) {
		this.gameEngineImpl = gameEngineImpl;
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
//this.gameEngineImpl.customiseBoard(); //add customise ti this when fixed at game
		
		CustomiseDialogueBox customDlg = new CustomiseDialogueBox(this.frame, this.gameEngineImpl);
        customDlg.setVisible(true);
        // if logon successfully
        if(customDlg.isSucceeded()){
            // nothing to do, will be handled by the GameEngine
        }
	}
	

}
