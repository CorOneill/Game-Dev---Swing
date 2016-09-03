package engine;

import javax.swing.JFrame;

public class Frame extends JFrame{
	public Frame(int pX, int pY, int pWidth, int pHeight){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(pX, pY, pWidth, pHeight);
		this.setVisible(true);
	}
}
