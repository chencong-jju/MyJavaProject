package com.cc.SnakeGame;

import java.awt.Color;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class Food extends JButton {
	private int x;
	private int y;

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setPosition() {
		this.setBounds(x, y, 10, 10);
	}

	Food(Snake snake) {
		super();
		this.setBackground(Color.GREEN);
		x = getRandom() * 10;
		y = getRandom() * 10;
		for (int i = 0; i < snake.getList().size();) {
			if (x == snake.getList().get(i).getX() && y == snake.getList().get(i).getY()) {
				i = 0;
				x = getRandom() * 10;
				y = getRandom() * 10;
			} else {
				i++;
			}
		}
		setPosition();
	}

	Food(int x, int y) {
		super();
		this.setBackground(Color.GREEN);
		this.x = x * 10;
		this.y = y * 10;
		setPosition();
	}

	/**
	 * 产生1~58的随机数
	 */
	public int getRandom() {
		int t;
		t = (int) (Math.random() * 58 + 1);
		return t;
	}
}
