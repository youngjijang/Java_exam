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
		
		super.setLocationRelativeTo(null);//창을 가운데 놓음
		
		super.setVisible(true);
		
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//이벤트 동록
		//이벤트주체.addxxxListener(이벤트 처리헤논 객체)
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
		//이벤트 발생시키는 주체얻기
		Object obj = e.getSource();
		if(obj==btn1) {
			System.out.println("버튼을 눌렀군요.");
			System.exit(0);
		}else {
			System.out.println(e.getActionCommand()+"를 눌렀군요.");
			//버튼ㅇㅣ 눌러진 횟수를 text 박스에 올리기
			
			text.setText(++num +" 번");
		}
	}

}

/**
 * 마우스 드래그 이벤트 만들기
 * @author KOSTA
 *
 */
class MouseExam extends MouseMotionAdapter{
	JTextField text;
	public MouseExam(JTextField text) {
		this.text = text;
	}//생성자를 통해서 이미 만들어 놓은 주소를 보냄!!!!!!!!!!!!!!!!다른 메소드에서 써먹으력고
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
