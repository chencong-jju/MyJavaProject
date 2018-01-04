package Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.Timer;

public class ImageGame {
	private long a;// 用于保存时间
	private Timer timer;
	private LinkedList<MyButton> list;
	private JButton timeButton;// 用于显示时间的按钮
	private JButton helpButton;// 查看原始图片按钮
	private JButton goButton;// 重置按钮
	private JFrame frame;
	private ImageIcon ic1;
	private String str = ".\\src\\res\\";// 图片地址或者(str = "./src/res/" str =
											// ".\\src\\res\\")

	public static void main(String[] args) {
		ImageGame t = new ImageGame();
		t.go();
	}

	public void go() {
		timer = new Timer(10, new timeListener());// 设置计时器，每10毫秒出发一次timeListener()；

		frame = new JFrame();
		frame.setLayout(null);

		list = GetButtonList();
		RandomButton(list);
		Show(list);

		helpButton = new JButton();
		helpButton.setBounds(0, 198, 30, 30);
		ic1 = new ImageIcon(str + "help.jpg");
		helpButton.setIcon(ic1);
		helpButton.addMouseListener(new helpButtonListener());
		frame.add(helpButton);

		goButton = new JButton();
		goButton.setBounds(198 - 30, 198, 30, 30);
		ic1 = new ImageIcon(str + "go.jpg");
		goButton.setIcon(ic1);
		goButton.addActionListener(new goButtonListener());
		frame.add(goButton);

		timeButton = new JButton("0  :  0  :  0  :  0");
		timeButton.setBounds(30, 198, 198 - 60, 30);
		frame.add(timeButton);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 作用：当关闭窗口时关闭程序
		frame.setSize(214, 236 + 30);// 设置框架大小
		frame.setVisible(true);
	}

	/**
	 * 获取9个随机的按钮
	 * 
	 * @return
	 */
	public void RandomButton(LinkedList<MyButton> list) {
		int[] RandomNum = new int[9];
		for (int i = 0; i < 9; i++) {
			RandomNum[i] = -1;
		}

		for (int i = 0; i < 9;) {
			int t = (int) (Math.random() * 9);
			if (RandomNum[t] == -1) {
				RandomNum[t] = i;
				i++;
			}
		}
		for (int i = 0; i < 9; i++) {
			MyButton bt = list.get(i);
			bt.setPosition(RandomNum[i]);
			list.set(i, bt);
		}
	}

	public LinkedList<MyButton> GetButtonList() {
		LinkedList<MyButton> list = new LinkedList<MyButton>();
		for (int i = 0; i < 9; i++) {
			MyButton bt = new MyButton(i, 0);
			if (i != 8) {
				ic1 = new ImageIcon(str + i / 3 + i % 3 + ".jpg");
				bt.setIcon(ic1);
			}
			bt.addActionListener(new buttonListener());
			list.add(bt);
		}
		return list;
	}

	/**
	 * 获取list中的空按钮
	 * 
	 * @param list
	 * @return
	 */
	public MyButton getBlankButton(LinkedList<MyButton> list) {
		int i = 0;
		while (list.get(i).getRealPosition() != 8) {
			i++;
		}
		return list.get(i);
	}

	/**
	 * 根据随机的9个按钮排布图片
	 * 
	 * @param list
	 */
	public void Show(LinkedList<MyButton> list) {
		for (int i = 0; i < 9; i++) {
			MyButton bt = list.get(i);
			bt.setBounds(bt.getPosition() % 3 * 66, bt.getPosition() / 3 * 66, 66, 66);
			frame.add(bt);
		}
	}

	public void ShowOriginalImage(LinkedList<MyButton> list) {
		for (int i = 0; i < 9; i++) {
			MyButton bt = list.get(i);
			bt.setBounds(bt.getRealPosition() % 3 * 66, bt.getRealPosition() / 3 * 66, 66, 66);
			frame.add(bt);
		}
	}

	/**
	 * 被点击的按钮和空白按钮交换
	 * 
	 * @param list
	 * @param bt
	 */
	public void Move(LinkedList<MyButton> list, MyButton bt) {
		MyButton blankButton = getBlankButton(list);
		int t = Math.abs((bt.getPosition() - blankButton.getPosition()));
		int a = (bt.getPosition() * blankButton.getPosition());
		/*
		 * 判断按钮是否与空白按钮相邻
		 */
		if ((t == 1 && a != 6 && a != 30) || (t == 3)) {
			int t1;
			int t2;
			t1 = bt.getPosition();
			t2 = blankButton.getPosition();
			bt.setPosition(t2);
			blankButton.setPosition(t1);
			list.set(bt.getRealPosition(), bt);
			list.set(blankButton.getRealPosition(), blankButton);
			Show(list);
		}
	}

	public Boolean Judge(LinkedList<MyButton> list) {
		for (int i = 0; i < list.size(); i++) {
			MyButton bt = list.get(i);
			if (bt.getRealPosition() != bt.getPosition()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 内部类，拼图按钮监听
	 * 
	 * @author Administrator
	 *
	 */
	class buttonListener implements ActionListener {
		public void actionPerformed(ActionEvent e)// 实现接口中的方法（对事件的处理方法）
		{
			MyButton bt = (MyButton) e.getSource();
			Move(list, bt);
			if (a == 0) {
				timer.start();// 调用计时器的stat()方法，开始计时
			}
			if (Judge(list)) {
				timer.stop();// 调用计时器的stop()方法，停止计时
				a = 0;// 将时间重置为0
			}
		}
	}

	/**
	 * 内部类，显示原图按钮监听
	 * 
	 * @author Administrator
	 */
	class helpButtonListener extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			ShowOriginalImage(list);
		}

		public void mouseReleased(MouseEvent e) {
			Show(list);
		}
	}

	/**
	 * 内部类，重置按钮监听
	 * 
	 * @author Administrator
	 *
	 */
	class goButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			RandomButton(list);
			Show(list);
			if (a != 0) {
				timer.stop();// 调用计时器的stop()方法，停止计时
				a = 0;// 将时间重置为0
			}
		}
	}

	/**
	 * 时间监听，每10ms触发一次
	 * 
	 * @author Administrator
	 *
	 */
	class timeListener implements ActionListener {

		public void actionPerformed(ActionEvent event)// 实现接口中的方法（对事件的处理方法）
		{
			long h = 0;// 时
			long m = 0;// 分
			long s = 0;// 秒
			String str;
			a++;
			h = a / 100 / 3600;
			m = a / 100 / 60 % 60;
			s = a / 100 % 60;
			if (a % 100 < 10) {
				str = "" + h + "  :  " + m + "  :  " + s + "  :  0" + a % 100;
			} else
				str = "" + h + "  :  " + m + "  :  " + s + "  :  " + a % 100;
			timeButton.setText(str);
		}
	}
}
