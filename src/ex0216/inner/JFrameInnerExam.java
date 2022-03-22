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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class JFrameInnerExam extends JFrame implements ActionListener{ //BorderLayout 이다. - 5개의 영역으로 컴포넌트가 올려진다.
	
	JButton btn1 = new JButton("클릭1");
	JButton btn2 = new JButton("클릭2");
	JTextField text = new JTextField(10);
	
    public JFrameInnerExam() {
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
    	// 이벤트주체.addXxxListener(이벤트처리해놓은객체);
    	btn1.addActionListener(this);
    	btn2.addActionListener(this);
    	this.addMouseMotionListener( new MouseExam() );
    	this.addMouseListener(new MouseExam02());//현재객체를 전달!!!
    	
    }//생성자끝
	public static void main(String[] args) {
		new JFrameInnerExam();

	}
	
	/**
	 * 버튼을 클릭했을때 호출되는 메소드 
	 * */
	int count;
	@Override
	public void actionPerformed(ActionEvent e) {
		//System.out.println("버튼이 눌려졌네요...");
		//이벤트 발생시키는 주체 얻기
		Object obj = e.getSource();
		if(obj == btn1) {
			System.out.println(e.getActionCommand() +" 버튼일 눌려졌어요.");
			//버튼이 눌려진 횟수를 text박스에 보이기
			text.setText(count++  +"번");
			
		}else if(obj ==btn2) {
			System.out.println(e.getActionCommand() +" 버튼일 눌려졌으니 종료하겠습니다.");
			System.exit(0);
		}
		
	}
	
	////Inner 클래스 추가 /////////////////////////////////
	/**
	 * 마우스를 드래그 했을때 이벤트핸들러 만들기 
	 * */
	class MouseExam extends MouseMotionAdapter{
		@Override
		public void mouseDragged(MouseEvent e) {
			//마우스 좌표를 구해서 text박스에 올린다.
			int x = e.getX();
			int y = e.getY();
			text.setText("x = " + x + " , y = " + y);
			
		}
	}//MouseExam End 

	 
	class MouseExam02 extends MouseAdapter{
		@Override
		public void mouseClicked(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			
			//System.out.println("x = " + x + " , y = " + y);
			//Jframe의 title을 좌표로 변경하기
			JFrameInnerExam.this.setTitle("x = " + x + " , y = " + y); //Outer클래스쪽의 객체를 접근하는 방법 : Outer클래스이름.this 
			
			//JButton 글씨 변경
			btn1.setText("x = " + x + " , y = " + y);
		}
	}//MouseExam02 End
	
}//JFrameExam End
/////////////////////////////////////////////////////////



////////////////////////////////////////////////////













