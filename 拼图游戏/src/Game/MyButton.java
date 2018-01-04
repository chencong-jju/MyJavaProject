package Game;

import javax.swing.JButton;

public class MyButton extends JButton {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int RealPosition;// 按钮在图片上的原始位置
	private int Position;// 按钮的实际位置

	MyButton(int RealPosition, int Position) {
		super();
		this.RealPosition = RealPosition;
		this.Position = Position;
	}

	public int getRealPosition() {
		return RealPosition;
	}

	public void setRealPosition(int realPosition) {
		RealPosition = realPosition;
	}

	public int getPosition() {
		return Position;
	}

	public void setPosition(int position) {
		Position = position;
	}

}
