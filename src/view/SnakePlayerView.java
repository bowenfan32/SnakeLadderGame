package view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import model.SnakePlayer;


public class SnakePlayerView extends Draw {

	private SnakePlayer snakePlayerModel;

	public SnakePlayerView(SnakePlayer snakePlayerModel) {
		this.snakePlayerModel = snakePlayerModel;
	}
	public void draw(Graphics g) {
		// the x and y coordinates of the snake end-points are based on the
		// board position of the head and the tail
		int x1 = getX(this.snakePlayerModel.getHead());
		int y1 = getY(this.snakePlayerModel.getHead());
		int x2 = getX(this.snakePlayerModel.getTail());
		int y2 = getY(this.snakePlayerModel.getTail());

		if (this.snakePlayerModel.getHead() < this.snakePlayerModel.getTail()) {
			System.out.println("Snake head must be higher than tail. Snake ignored");
			return;
		}

		// The number of steps is based on the length of the snake
		int steps = (int) Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1)) / 150 * 18 + 24;

		double xstep = (double) (x2 - x1) / (steps + 1);
		double ystep = (double) (y2 - y1) / (steps + 1);

		double inc;
		double x = x1, y = y1;

		boolean odd = true;
		for (int i = 0; i < steps + 1; i++) {
			if ((i % 12) % 12 == 0)
				inc = 0;
			else if ((i % 12) % 11 == 0)
				inc = 10 * factor;
			else if ((i % 12) % 10 == 0)
				inc = 13 * factor;
			else if ((i % 12) % 9 == 0)
				inc = 15 * factor;
			else if ((i % 12) % 8 == 0)
				inc = 13 * factor;
			else if ((i % 12) % 7 == 0)
				inc = 10 * factor;
			else if ((i % 12) % 6 == 0)
				inc = 0 * factor;
			else if ((i % 12) % 5 == 0)
				inc = -10 * factor;
			else if ((i % 12) % 4 == 0)
				inc = -13 * factor;
			else if ((i % 12) % 3 == 0)
				inc = -15 * factor;
			else if ((i % 12) % 2 == 0)
				inc = -13 * factor;
			else
				inc = -10 * factor;
			x += xstep;
			y += ystep;
			if (odd) {
				g.setColor(Color.RED);
				odd = false;
			} else {
				g.setColor(Color.BLUE);
				odd = true;
			}
			if (x2 > x1)
				g.fillOval((int) (x + inc), (int) (y - inc), 20 - 10 * i / steps, 20 - 10 * i / steps);
			else
				g.fillOval((int) (x - inc), (int) (y - inc), 20 - 10 * i / steps, 20 - 10 * i / steps);
		}
	}




}
