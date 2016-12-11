package model;

import model.interfaces.Entity;
import view.LadderView;

public class Ladder implements Entity {

	private int bottom;
	private int top;
	public LadderView view;

	public Ladder(int b, int t) {
		bottom = b;
		top = t;
		this.view = new LadderView(this);
	}

	public int change() {
		return top - bottom;
	}

	public int getBottom() {
		return this.bottom;
	}

	public int getTop() {
		return this.top;
	}
}
