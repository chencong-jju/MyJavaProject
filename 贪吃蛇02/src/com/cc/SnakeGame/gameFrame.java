package com.cc.SnakeGame;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

public class gameFrame{
	private JFrame frame;


	private JButton button;//边框按钮
	private JButton beginButton;//开始按钮
	private JButton resetButton;//重置按钮
	private JButton scoreButton;//计分板

	public JButton getBeginButton() {
		return beginButton;
	}
	public JButton getResetButton() {
		return resetButton;
	}
	public JButton getScoreButton() {
		return scoreButton;
	}
	public JFrame getFrame() {
		return frame;
	}
	
	
	gameFrame() {
		beginButton = new JButton("Begin");
		beginButton.setBounds(0, 600, 100, 40);
		resetButton = new JButton("Reset");
		resetButton.setBounds(500, 600, 100, 40);
		scoreButton = new JButton();
		scoreButton.setBounds(100, 600, 400, 40);
		frame = new JFrame("贪吃蛇");
		frame.setLayout(null);
		for (int i = 0; i < 60; i++) {
			button = new JButton();
			button.setBackground(Color.red);
			button.setBounds(i * 10, 0, 10, 10);
			frame.add(button);
			button = new JButton();
			button.setBackground(Color.red);
			button.setBounds(i * 10, 600 - 10, 10, 10);
			frame.add(button);
		}
		for (int i = 0; i < 60; i++) {
			button = new JButton();
			button.setBackground(Color.red);
			button.setBounds(0, i * 10, 10, 10);
			frame.add(button);
			button = new JButton();
			button.setBackground(Color.red);
			button.setBounds(600 - 10, i * 10, 10, 10);
			frame.add(button);
		}
		frame.add(beginButton);
		frame.add(resetButton);
		frame.add(scoreButton);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600 + 8 + 8, 600 + 30 + 8 + 40);
		frame.setVisible(true);
	}
	//显示食物
	public void showFood(Food food) {
		frame.add(food);
	}
	//显示蛇 
	public void showSnake(Snake snake) {
		for (int i = 0; i < snake.getList().size(); i++) {
			frame.add(snake.getList().get(i));
		}
	}

	public void showScore(int score){
		scoreButton.setText("Score : "+score);
	}
}
