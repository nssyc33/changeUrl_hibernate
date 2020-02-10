package testGitHub.io.Service;

import org.springframework.stereotype.Service;
import testGitHub.io.Interface.PlayService;

@Service
public class StaticVariableService {

	private static String staticVar;
	private String privateVar;
	
	
	public void StaticTest(){
		System.out.println("staticVar 변수 확인  : " + staticVar);
		System.out.println("privateVar 변수 확인  : " + privateVar);
		/*PlayService p = (a,b) -> a-b;
	    System.out.println(p.operator(14, 12));
	    System.out.println(p.operator(13, 11));
	    */

	}

	public void StaticPlusTime(Long time){
		staticVar = NVLS(staticVar)+ time;
		privateVar = NVLS(privateVar)+ time;
	}
	
	private String NVLS(Object str){
		if(str == null){
			return "시작";
		}else{
			return str.toString();
		}
	}
}
