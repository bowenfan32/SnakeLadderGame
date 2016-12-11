package view;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StatusBar extends JPanel
{
	private JLabel status1 = new JLabel("some status", JLabel.LEFT);

	public StatusBar()
	{
		setLayout(new GridLayout(1, 1));
		add(status1);
		
		status1.setBorder(BorderFactory.createLineBorder(Color.GRAY));
	}

	public void setStatus(String status)
	{
		status1.setText(status);
	}
}
