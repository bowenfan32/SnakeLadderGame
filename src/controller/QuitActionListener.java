package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.GameAppFrame;

public class QuitActionListener implements ActionListener {

	public QuitActionListener() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.exit(0);

	}
}
