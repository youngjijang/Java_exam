package ex0216.inner;
/**
 * Inner클래스는 선언위치와 선언 방법 에 따라 3가지 형태가 존재한다.
     1) 인스턴스 맴버클래스
           class A{
                class B{ }
           }

     2) 정적(static) 맴버클래스 
           class A{
               static class B{ }
            }

     3) 로컬(메소드 내부에서 선언된) 클래스
             class A{
                   public void aa(){
                          class B{  }
                   }
             }

 * */

  class Test{ //Outer클래스 역할
	 int a=50;
	 static int b=100;
	 
	 public void aa() {}
	 public static void bb() {}
	 
	 ///1) 인스턴스 맴버클래스////////////////////////////////
	 /**
	  *  : 클래스앞에 access modifier 4가지 모두 가능하다
	  *  :  non-static 선언가능하지만,
	  *     static 선언안됨!!! - jdk version 16부터 가능!!!
	  *  :  non-static, static 모든 접근=호출 가능!!!
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
	   * 정적(static) 맴버클래스 
	   *   :클래스앞에 access modifier 4가지 모두 가능하다
	   *   : non-static, static 선언 모두 가능하다.
	   *   : outer의 non-static 접근불가 , static만 접근가능
	   *   : static inner 클래스를 사용할때 Outer클래스의 생성없이 바로 접근가능하다.
	   * */
	  static class StaticInner{
		   int a=10;
		   static int c=4;
		   
		   public void test1() {
			   System.out.println(a);//10
			   System.out.println(this.a);//10
			   
			   //System.out.println(Test.this.a); //non-static 접근 불가
			   
			   System.out.println(b);
			   //System.out.println(this.b);
			   
			   //System.out.println(Test.this.b);
			   
			   System.out.println(Test.b);
			   
			   //aa();
			   
			   bb();
			   
		   }
		   public static void test02() {}
	   }//static클래스끝
	  
	  ////////////////////////////////////////////////
	  /**
	   * 3) 로컬(메소드 내부에서 선언된) 클래스
	   *   : 접근제한자, static 클래스앞에 올수 없다!
	   * */
	  public void localInnerTest() {
		   System.out.println("----localInnerTest() 메소드입니다.------");
		   
		  class LocalInner{
			   int a=7;
			   //static int c=4;
			   
			   public void test() {
				   System.out.println("LocalInner안에 test()메소드입니다..");
			   }
		   }//LocalInner End
		  
		  
		  LocalInner local = new LocalInner();
		  local.test();
		  
	  }//메소드끝
	  
}//TestEnd 
/////////////////////////////////////////////////
public class InnerClassAccessExam {
	public static void main(String[] args) {
		System.out.println("*** 1) 인스턴스 맴버클래스 *****************");
		Test t = new Test();
		Test.InstanceInner  testInstace = t.new InstanceInner();
		testInstace.test();
		
		System.out.println("*** 2) static 맴버클래스 *****************");
		Test.StaticInner tis = new Test.StaticInner();
		tis.test1();
		
		System.out.println("***3) 로컬(메소드 내부에서 선언된) 클래스************");
		t.localInnerTest();//메소드호출
	}
}













