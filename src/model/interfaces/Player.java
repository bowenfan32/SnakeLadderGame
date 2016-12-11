package model.interfaces;

import view.Draw;

public abstract class Player {

	protected String name;
	protected int pos = 1;
	protected int index; // represents the player index 0, 1, 2 or 3 if 4 players
	public Draw view;

	public Player() {
		super();
	}

	public String getName() {
		return name;
	}

	public int getPos() {
		return pos;
	}

	public abstract int move(int positionsToMove);

	public int getIndex() {
		return index;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}
}