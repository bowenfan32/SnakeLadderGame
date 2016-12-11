package model;

import model.interfaces.Entity;
import view.SnakeView;

public class Snake implements Entity {

	private int head;
	private int tail;
	public SnakeView view;

	public Snake(int h, int t) {
		head = h;
		tail = t;
		this.view = new SnakeView(this);
	}

	public int change() {
		return tail - head;
	}

	public int getHead() {
		return head;
	}

	public int getTail() {
		return tail;
	}
}
