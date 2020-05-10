package testGitHub.io.JavaTest;

public class MethodClass {

	public static void main(String[] args) {
		
		MethodClass md = new MethodClass();
		md.test();
	}
	
	public void test(){
		MethodInter d = ()->{
			return "testtest";
		};
		this.testIn(d);
	}
	
	private void testIn(MethodInter md){
		System.out.println("인터페이스 시작 확인");
		System.out.println(md.tests());
		System.out.println("인터페이스 종료 확인");
		
	}
}
