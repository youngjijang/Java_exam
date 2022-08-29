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

public class JFrameAnonymousExam extends JFrame{ //BorderLayout �̴�. - 5���� �������� ������Ʈ�� �÷�����.
	
	JButton btn1 = new JButton("Ŭ��1");
	JButton btn2 = new JButton("Ŭ��2");
	JTextField text = new JTextField(10);
	
	int count;
	
    public JFrameAnonymousExam() {
    	super("JFrameExam�����Դϴ�.^^");
    	
    	Container con = super.getContentPane();
    	
    	con.setBackground(Color.CYAN);
    	btn1.setBackground(Color.pink);
    	
    	//JFrame�� ���̾ƿ��� �����غ���
    	//super.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 50));
    	super.setLayout(new FlowLayout());
    	
    	// ��ư 2���� �߰��غ���!!
    	//con.add(btn1 , BorderLayout.NORTH);
    	//con.add(btn2, BorderLayout.WEST);
    	
    	con.add(btn1);
    	con.add(btn2);
        con.add(text);
    	
    	//â�� ũ�� ����
    	super.setSize(500, 600);
    	
    	//����� â ����
    	super.setLocationRelativeTo(null);
    	
    	//JFrame ������.
    	super.setVisible(true);
    	
    	//x�� Ŭ�������� JFrame�����ϱ�
    	super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	//�̺�Ʈ���
    	// �̺�Ʈ��ü.addXxxListener(Annoymous����);
    	
    	btn1.addActionListener(new ActionListener() {
    		//������
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			//���
    			text.setText(++count +"��");
    		}
    	});
    	
    	
    	btn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(e.getActionCommand()+"Ŭ���Ǿ� �����մϴ�.");
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
    	
    	
    }//�����ڳ�
	public static void main(String[] args) {
		new JFrameAnonymousExam();

	}	
}//JFrameExam End
/////////////////////////////////////////////////////////



////////////////////////////////////////////////////













