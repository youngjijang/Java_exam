package ex0216.inner;
/**
 * InnerŬ������ ������ġ�� ���� ��� �� ���� 3���� ���°� �����Ѵ�.
     1) �ν��Ͻ� �ɹ�Ŭ����
           class A{
                class B{ }
           }

     2) ����(static) �ɹ�Ŭ���� 
           class A{
               static class B{ }
            }

     3) ����(�޼ҵ� ���ο��� �����) Ŭ����
             class A{
                   public void aa(){
                          class B{  }
                   }
             }

 * */

  class Test{ //OuterŬ���� ����
	 int a=50;
	 static int b=100;
	 
	 public void aa() {}
	 public static void bb() {}
	 
	 ///1) �ν��Ͻ� �ɹ�Ŭ����////////////////////////////////
	 /**
	  *  : Ŭ�����տ� access modifier 4���� ��� �����ϴ�
	  *  :  non-static ���𰡴�������,
	  *     static ����ȵ�!!! - jdk version 16���� ����!!!
	  *  :  non-static, static ��� ����=ȣ�� ����!!!
	  * */
	  class InstanceInner{
		 int a=10;
		// static int c=50;
		 public void test() {
			 System.out.println(a);// 10
			 System.out.println(this.a);// 10
			 System.out.println(Test.this.a);//50
			 
			 
			 System.out.println(b);//100
			 
			 aa();
			 bb();
		 }
		/* public static void test2() {
			 
		 }*/
	 }//InstanceInner End
	  
	  ///////////////////////////////////////////////
	  /**
	   * ����(static) �ɹ�Ŭ���� 
	   *   :Ŭ�����տ� access modifier 4���� ��� �����ϴ�
	   *   : non-static, static ���� ��� �����ϴ�.
	   *   : outer�� non-static ���ٺҰ� , static�� ���ٰ���
	   *   : static inner Ŭ������ ����Ҷ� OuterŬ������ �������� �ٷ� ���ٰ����ϴ�.
	   * */
	  static class StaticInner{
		   int a=10;
		   static int c=4;
		   
		   public void test1() {
			   System.out.println(a);//10
			   System.out.println(this.a);//10
			   
			   //System.out.println(Test.this.a); //non-static ���� �Ұ�
			   
			   System.out.println(b);
			   //System.out.println(this.b);
			   
			   //System.out.println(Test.this.b);
			   
			   System.out.println(Test.b);
			   
			   //aa();
			   
			   bb();
			   
		   }
		   public static void test02() {}
	   }//staticŬ������
	  
	  ////////////////////////////////////////////////
	  /**
	   * 3) ����(�޼ҵ� ���ο��� �����) Ŭ����
	   *   : ����������, static Ŭ�����տ� �ü� ����!
	   * */
	  public void localInnerTest() {
		   System.out.println("----localInnerTest() �޼ҵ��Դϴ�.------");
		   
		  class LocalInner{
			   int a=7;
			   //static int c=4;
			   
			   public void test() {
				   System.out.println("LocalInner�ȿ� test()�޼ҵ��Դϴ�..");
			   }
		   }//LocalInner End
		  
		  
		  LocalInner local = new LocalInner();
		  local.test();
		  
	  }//�޼ҵ峡
	  
}//TestEnd 
/////////////////////////////////////////////////
public class InnerClassAccessExam {
	public static void main(String[] args) {
		System.out.println("*** 1) �ν��Ͻ� �ɹ�Ŭ���� *****************");
		Test t = new Test();
		Test.InstanceInner  testInstace = t.new InstanceInner();
		testInstace.test();
		
		System.out.println("*** 2) static �ɹ�Ŭ���� *****************");
		Test.StaticInner tis = new Test.StaticInner();
		tis.test1();
		
		System.out.println("***3) ����(�޼ҵ� ���ο��� �����) Ŭ����************");
		t.localInnerTest();//�޼ҵ�ȣ��
	}
}













