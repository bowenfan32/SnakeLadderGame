package model;

import view.DiceView;

public class Dice {

	private int n = -1;
	public DiceView view;

	public Dice() {
		this.view = new DiceView(this);
	}

	public int getValue() {
		return n;
	}

	public int getNewDiceValue() {
		this.n = (int) (Math.random() * 6) + 1;
		return n;
	}

}
