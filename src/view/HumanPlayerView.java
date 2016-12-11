package view;


import java.util.*;
import java.awt.*;
import javax.swing.*;

import model.interfaces.Player;

public class HumanPlayerView extends Draw {


	private Player player;

	public HumanPlayerView(Player player) {
		this.player = player;
	}


	// Drawing the positions of the pieces using different colors
	public void draw(Graphics g) {
		if (this.player.getIndex() == 0) {
			g.setColor(Color.WHITE);
			g.fillOval((int) getX(this.player.getPos()) - 10, getY(this.player.getPos()) - 10, 20, 20);
			g.setColor(Color.BLACK);
			g.drawString("1", (int) getX(this.player.getPos()) - 5, getY(this.player.getPos()) + 5);
		}
		if (this.player.getIndex() == 1) {
			g.setColor(Color.RED);
			g.fillOval((int) getX(this.player.getPos()) + 10, getY(this.player.getPos()) - 10, 20, 20);
			g.setColor(Color.BLACK);
			g.drawString("2", (int) getX(this.player.getPos()) + 15, getY(this.player.getPos()) + 5);
		}
		if (this.player.getIndex() == 2) {
			g.setColor(Color.BLUE);
			g.fillOval((int) getX(this.player.getPos()) - 10, getY(this.player.getPos()) + 10, 20, 20);
			g.setColor(Color.BLACK);
			g.drawString("3", (int) getX(this.player.getPos()) - 5, getY(this.player.getPos()) + 25);
		}
		if (this.player.getIndex() == 3) {
			g.setColor(Color.CYAN);
			g.fillOval((int) getX(this.player.getPos()) + 10, getY(this.player.getPos()) + 10, 20, 20);
			g.setColor(Color.BLACK);
			g.drawString("4", (int) getX(this.player.getPos()) + 15, getY(this.player.getPos()) + 25);
		}
	}



}

