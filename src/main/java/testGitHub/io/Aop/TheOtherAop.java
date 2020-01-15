package testGitHub.io.Aop;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class TheOtherAop {

	/*@Around("execution(* testGitHub.io.Service.*.*(..))")
	public Object timeLog(ProceedingJoinPoint pjp) throws Throwable {		
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~AOP 시작합니다.");
		long startTime = System.currentTimeMillis();
		Object result = pjp.proceed();
		long endTime = System.currentTimeMillis();
		return result;
	}*/
}
