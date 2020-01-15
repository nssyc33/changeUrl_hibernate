package testGitHub.io.Aop;

import org.aspectj.lang.annotation.Aspect;

@Aspect
public class AnotherAop {

	public void fn_before(){
		System.out.println("fn_before");
	}
	
	public void fn_after(){
		System.out.println("fn_after");
	}
}
