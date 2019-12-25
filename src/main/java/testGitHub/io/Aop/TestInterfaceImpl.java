package testGitHub.io.Aop;
import org.springframework.stereotype.Component;

@Component
public class TestInterfaceImpl implements TestInterface{

	@Override
	public void testtest() {
		System.out.println("testtest 시작이 되었습니다.");
	}
}
