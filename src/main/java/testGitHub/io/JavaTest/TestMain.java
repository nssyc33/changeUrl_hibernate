package testGitHub.io.JavaTest;

import java.util.HashMap;
import java.util.Map;

public class TestMain {
	public static void main(String[] args) {
		
		String a = new String("test1");
		String b = new String("test2");
		b = a;
		
		a = new String("test3");
		System.out.println(a);
		System.out.println(b);
		
		
	}
}
