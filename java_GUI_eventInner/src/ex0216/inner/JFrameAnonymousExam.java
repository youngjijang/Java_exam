package ex0216.inner;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class JFrameAnonymousExam extends JFrame{ //BorderLayout 이다. - 5개의 영역으로 컴포넌트가 올려진다.
	
	JButton btn1 = new JButton("클릭1");
	JButton btn2 = new JButton("클릭2");
	JTextField text = new JTextField(10);
	
	int count;
	
    public JFrameAnonymousExam() {
    	super("JFrameExam예제입니다.^^");
    	
    	Container con = super.getContentPane();
    	
    	con.setBackground(Color.CYAN);
    	btn1.setBackground(Color.pink);
    	
    	//JFrame의 레이아웃을 변경해보자
    	//super.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 50));
    	super.setLayout(new FlowLayout());
    	
    	// 버튼 2개을 추가해보자!!
    	//con.add(btn1 , BorderLayout.NORTH);
    	//con.add(btn2, BorderLayout.WEST);
    	
    	con.add(btn1);
    	con.add(btn2);
        con.add(text);
    	
    	//창의 크기 설정
    	super.setSize(500, 600);
    	
    	//정가운데 창 놓기
    	super.setLocationRelativeTo(null);
    	
    	//JFrame 보여줘.
    	super.setVisible(true);
    	
    	//x를 클릭했을때 JFrame종료하기
    	super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	//이벤트등록
    	// 이벤트주체.addXxxListener(Annoymous형태);
    	
    	btn1.addActionListener(new ActionListener() {
    		//재정의
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			//기능
    			text.setText(++count +"번");
    		}
    	});
    	
    	
    	btn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(e.getActionCommand()+"클릭되어 종료합니다.");
				System.exit(0);
			}
		});
    	
    	
    	this.addMouseMotionListener( new MouseMotionAdapter() {
    		@Override
    		public void mouseDragged(MouseEvent e) {
    			int x = e.getX();
    			int y = e.getY();
    			
    			text.setText("x = " + x +", y = " + y);
    		}
		});
    	
    	this.addMouseListener(new MouseAdapter() {
    		@Override
    		public void mouseClicked(MouseEvent e) {
    			int x = e.getX();
    			int y = e.getY();
    			
    			JFrameAnonymousExam.this.setTitle("x: "+ x +", y : " + y);
    		}
    		
		});
    	
    	
    }//생성자끝
	public static void main(String[] args) {
		new JFrameAnonymousExam();

	}	
}//JFrameExam End
/////////////////////////////////////////////////////////



////////////////////////////////////////////////////













