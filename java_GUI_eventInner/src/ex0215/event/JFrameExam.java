package ex0215.event;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class JFrameExam extends JFrame implements ActionListener{
	
	JButton btn1 = new JButton("Ŭ��");
	JButton btn2 = new JButton("����");
	JTextField text = new JTextField(10);

	public JFrameExam() {
		super("JFrameExam�����Դϴ�.");
		
		Container con = super.getContentPane();
		
		super.setLayout(new FlowLayout());
		
//		super.add(btn1,BorderLayout.NORTH);
//		super.add(btn2);
		con.add(btn1);
		con.add(btn2);
		con.add(text);
		
		super.setSize(500, 600);
		
		super.setLocationRelativeTo(null);//â�� ��� ����
		
		super.setVisible(true);
		
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//�̺�Ʈ ����
		//�̺�Ʈ��ü.addxxxListener(�̺�Ʈ ó����� ��ü)
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		this.addMouseMotionListener(new MouseExam(text));
		this.addMouseListener(new MouseExam02());
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new JFrameExam();
	}
	int num;
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//�̺�Ʈ �߻���Ű�� ��ü���
		Object obj = e.getSource();
		if(obj==btn1) {
			System.out.println("��ư�� ��������.");
			System.exit(0);
		}else {
			System.out.println(e.getActionCommand()+"�� ��������.");
			//��ư���� ������ Ƚ���� text �ڽ��� �ø���
			
			text.setText(++num +" ��");
		}
	}

}

/**
 * ���콺 �巡�� �̺�Ʈ �����
 * @author KOSTA
 *
 */
class MouseExam extends MouseMotionAdapter{
	JTextField text;
	public MouseExam(JTextField text) {
		this.text = text;
	}//�����ڸ� ���ؼ� �̹� ����� ���� �ּҸ� ����!!!!!!!!!!!!!!!!�ٸ� �޼ҵ忡�� ������°�
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		int x = e.getX();
		int y = e.getY();
		
		//System.out.println("x = " + x +" y = "+y);
		text.setText("x = " + x +" y = "+y);
	}
}

class MouseExam02 extends MouseAdapter{
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int x = e.getX();
		int y = e.getY();
		
		System.out.println("x = " + x +" y = "+y);
	}
}
