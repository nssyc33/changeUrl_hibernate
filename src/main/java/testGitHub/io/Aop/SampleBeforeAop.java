package testGitHub.io.Aop;
import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.stereotype.Component;


@Component
//@Aspect
public class SampleBeforeAop implements MethodBeforeAdvice{

	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("MethodBeforeAdvice 구현체");
	}
	
	public void before_sub() throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("MethodBeforeAdvice before_sub");
	}
	/*private static final Logger logger = LoggerFactory.getLogger(SampleAop.class);

	//target 메소드의 동작 시간을 로그한다.
//	@Around("execution(* testGitHub.io.Service.*.*(..))")
	public Object timeLog(ProceedingJoinPoint pjp) throws Throwable {		
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~AOP 시작합니다.");
		long startTime = System.currentTimeMillis();
		Object result = pjp.proceed();
		long endTime = System.currentTimeMillis();
		return result;
	}
	
	public void CheckLog_around() throws Throwable {		
		System.out.println("AOP around가 호출되었습니다.");
	}
	
	public void CheckLog_before() throws Throwable {		
		System.out.println("AOP before가 호출되었습니다.");
	}
	
	public void CheckLog_after() throws Throwable {		
		System.out.println("AOP after가 호출되었습니다.");
	}
	
	public void CheckLog_returning() throws Throwable {		
		System.out.println("AOP returning가 호출되었습니다.");
	}
	
	public void CheckLog_throwing() throws Throwable {		
		System.out.println("AOP throwing가 호출되었습니다.");
	}*/
}