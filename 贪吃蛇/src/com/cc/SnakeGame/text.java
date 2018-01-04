package com.cc.SnakeGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.Timer;
//注释测试
public class text {
	private Food food;
	private Snake snake;//�߶���
	private Timer timer;//��ʱ���������ߵ��ƶ��ٶ�
	private gameFrame frame;//��Ϸ��ܣ����ߺ�ʳ����Ƶ������

	public static void main(String[] args) {
		text t = new text();
		t.go();
	}

	public void go() {
		snake = new Snake();
		food = new Food(snake);
		timer = new Timer(100, new timeListener());
		frame = new gameFrame();
		frame.showFood(food);
		frame.showSnake(snake);
		frame.showScore(snake.getScore());
		frame.getBeginButton().addActionListener(new BeginButtonListener());
		frame.getBeginButton().addKeyListener(new MyKeyListener());
		frame.getResetButton().addActionListener(new ResetButtonListener());
	}

	class timeListener implements ActionListener {
		public void actionPerformed(ActionEvent event)// ʵ�ֽӿ��еķ��������¼��Ĵ�������
		{
			snake.snakeMove();
			if (snake.isDied()) {
				timer.stop();
			}
			if (snake.eat(food)) {
				food = new Food(snake);
				frame.showFood(food);
				frame.showScore(snake.getScore());
				frame.getFrame().repaint();
			}
		}
	}

	class BeginButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (snake.isDied()) {
				timer.stop();
				go();
			} else
				timer.start();
		}
	}

	class ResetButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			timer.stop();
			go();
		}
	}

	class MyKeyListener extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			snake.keyMove(e.getKeyCode());
		}
	}
}
