package view;

import java.awt.*;

import model.Dice;

public class DiceView {

	private Dice diceModel;

	public DiceView(Dice diceModel) {
		this.diceModel = diceModel;
	}

	// simulate the drawing of the dice
	public int roll(Graphics g) {
		int n = 0;
		for (int i = 1; i <= 20; i++) {
			n = (int) (Math.random() * 6) + 1;
			set(g, n, 100);
		}
		set(g, this.diceModel.getValue(), 800);
		return n;
	}

	// Draw the dice value using a series of dots using fillOval
	public void draw(Graphics g, int n) {
		g.setColor(Color.BLACK);
		g.fill3DRect(500, 200, 45, 45, false);
		g.setColor(Color.WHITE);
		if (n == 1) {
			g.fillOval(520, 220, 5, 5);
		} else if (n == 2) {
			g.fillOval(510, 220, 5, 5);
			g.fillOval(530, 220, 5, 5);
		} else if (n == 3) {
			g.fillOval(520, 210, 5, 5);
			g.fillOval(510, 230, 5, 5);
			g.fillOval(530, 230, 5, 5);
		} else if (n == 4) {
			g.fillOval(510, 210, 5, 5);
			g.fillOval(530, 210, 5, 5);
			g.fillOval(510, 230, 5, 5);
			g.fillOval(530, 230, 5, 5);
		} else if (n == 5) {
			g.fillOval(510, 210, 5, 5);
			g.fillOval(530, 210, 5, 5);
			g.fillOval(520, 220, 5, 5);
			g.fillOval(510, 230, 5, 5);
			g.fillOval(530, 230, 5, 5);
		} else if (n == 6) {
			g.fillOval(510, 210, 5, 5);
			g.fillOval(530, 210, 5, 5);
			g.fillOval(510, 220, 5, 5);
			g.fillOval(530, 220, 5, 5);
			g.fillOval(510, 230, 5, 5);
			g.fillOval(530, 230, 5, 5);
		}
	}


	// sets the dice value and draws it
	public void set(Graphics g, int val, int time) {
		if (val >= 1 && val <= 6) {

			draw(g, val);
			try {
				Thread.sleep(time);
			} catch (Exception e) {
			}
		}
	}


}
