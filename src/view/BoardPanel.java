package view;
import java.awt.*;
import javax.swing.*;

import model.BoardModel;
import model.SnakePlayer;

import java.util.*;

public class BoardPanel extends JPanel implements Runnable {

	private int boardWidth = 800;
	private int boardHeight = 800;

	private int XMARGIN = 20;
	private int YMARGIN = 20;


	private BoardModel boardModel;




	public BoardPanel(BoardModel board) {
		/*
		Container contentPane = frame.getContentPane();
		contentPane.setLayout(new BorderLayout());

		frame.getContentPane().add(this, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1280, 1040);
		frame.setVisible(true);
		setup();
		new Thread(this).start();
		 */

		setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		setMinimumSize(new Dimension(boardWidth, boardHeight));
		this.boardModel = board;
		new Thread(this).start();
	}

	// This method is used to wiggle the snake
	// The timing can be changed by varying the sleep time
	public void run() {
		double inc = 0.05;
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
			}
			Draw.factor += inc;
			;
			if (Draw.factor > 0.5 || Draw.factor < -0.5)
				inc = -inc;
			repaint();
		}
	}

	// this method is called in response to repaint
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++) {
				if ((i + j) % 2 == 0)
					g.setColor(Color.YELLOW);
				else
					g.setColor(Color.ORANGE);
				g.fillRect(XMARGIN + 80 * i, YMARGIN + 80 * j, 80, 80);
			}
		g.setColor(Color.BLACK);
		for (int i = 0; i < 100; i++)
			if ((i / 10) % 2 == 0)
				g.drawString("" + (i + 1), XMARGIN + 10 + i % 10 * 80, YMARGIN - 5 + 800 - i / 10 * 80);
			else
				g.drawString("" + (i + 1), XMARGIN + 740 - (i % 10 * 80), YMARGIN - 5 + 800 - i / 10 * 80);

		for (int i = 0; i < this.boardModel.snakesCount; i++)
			this.boardModel.snakesArray[i].view.draw(g);
		for (int i = 0; i < this.boardModel.laddersCount; i++)
			this.boardModel.ladderArray[i].view.draw(g);
		// Snake player is part of Player therefore must not include snake
		// players when drawing token
		for (int i = 0; i < this.boardModel.playerCount; i++)
			this.boardModel.players.get(i).view.draw(g);
		for (int i = 0; i < this.boardModel.snakePlayerCount; i++)
			this.boardModel.snakePlayerList.get(i).view.draw(g);
	}

}