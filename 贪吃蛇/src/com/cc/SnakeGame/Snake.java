package com.cc.SnakeGame;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

public class Snake {
	private LinkedList<Food> list;
	private Food Head;
	private Food Tail;
	private int moveDirection;
	private int score = 0;

	Snake() {
		list = new LinkedList<Food>();
		Head = new Food(29, 29);
		Head.setBackground(Color.BLUE);
		Tail = new Food(27, 29);
		list.add(new Food(28, 29));
		list.addFirst(Head);
		list.addLast(Tail);
		moveDirection = KeyEvent.VK_RIGHT;
	}

	public LinkedList<Food> getList() {
		return list;
	}

	public Food getHead() {
		return Head;
	}

	public void setHead(Food head) {
		Head = head;
	}

	public Food getTail() {
		return Tail;
	}

	public void setTail(Food tail) {
		Tail = tail;
	}

	//蛇吃食物
	public Boolean eat(Food food) {
		if (food.getX() == Head.getX() && food.getY() == Head.getY()) {
			list.addLast(food);
			score++;
			return true;
		}
		return false;
	}

	public int getScore() {
		return score;
	}

	//判断蛇是否死亡
	public Boolean isDied() {
		int x = Head.getX();
		int y = Head.getY();
		for (int i = 1; i < list.size(); i++) {
			if (x == list.get(i).getX() && y == list.get(i).getY()) {
				return true;
			}
		}
		if (x <= 0 || x >= 590 || y <= 0 || y >= 590)
			return true;
		else
			return false;
	}

	public void snakeMove() {
		Food last = list.removeLast();
		Food head = list.removeFirst();
		head.setBackground(Color.GREEN);
		last.setBackground(Color.BLUE);
		switch (moveDirection) {
		case KeyEvent.VK_DOWN:
			last.setX(head.getX());
			last.setY(head.getY() + 10);
			break;
		case KeyEvent.VK_UP:
			last.setX(head.getX());
			last.setY(head.getY() - 10);
			break;
		case KeyEvent.VK_LEFT:
			last.setX(head.getX() - 10);
			last.setY(head.getY());
			break;
		case KeyEvent.VK_RIGHT:
			last.setX(head.getX() + 10);
			last.setY(head.getY());
			break;
		}
		last.setPosition();
		list.addFirst(head);
		list.addFirst(last);
		Head = list.getFirst();
		Tail = list.getLast();
	}

	public void keyMove(int keyDirection) {
		int t = Math.abs(keyDirection - moveDirection);
		switch (keyDirection) {
		case KeyEvent.VK_DOWN:
			if (t != 2 && t != 0) {
				moveDirection = KeyEvent.VK_DOWN;
			}
			break;
		case KeyEvent.VK_UP:
			if (t != 2 && t != 0) {
				moveDirection = KeyEvent.VK_UP;
			}
			break;
		case KeyEvent.VK_LEFT:
			if (t != 2 && t != 0) {
				moveDirection = KeyEvent.VK_LEFT;
			}
			break;
		case KeyEvent.VK_RIGHT:
			if (t != 2 && t != 0) {
				moveDirection = KeyEvent.VK_RIGHT;
			}
			break;
		}
	}

	public int getMoveDirection() {
		return moveDirection;
	}

	public void setMoveDirection(int moveDirection) {
		this.moveDirection = moveDirection;
	}

	public void setList(LinkedList<Food> list) {
		this.list = list;
	}
}
