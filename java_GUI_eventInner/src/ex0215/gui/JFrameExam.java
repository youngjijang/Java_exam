package ex0215.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class JFrameExam extends JFrame {
	
	JButton btn1 = new JButton("클릭");
	JButton btn2 = new JButton("영지");
	JTextField text = new JTextField(10);

	public JFrameExam() {
		super("JFrameExam예제입니다.");
		
		Container con = super.getContentPane();
		
		super.setLayout(new FlowLayout());
		
//		super.add(btn1,BorderLayout.NORTH);
//		super.add(btn2);
		con.add(btn1);
		con.add(btn2);
		con.add(text);
		
		super.setSize(500, 600);
		
		super.setVisible(true);
		
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new JFrameExam();
	}

}


