package testGitHub.io.Service;

import org.springframework.stereotype.Service;
import testGitHub.io.Interface.PlayService;

@Service
public class StaticVariableService {

	public static String staticVar;
	
	public void StaticTest(){
		System.out.println("staticVar 변수 확인  : " + staticVar);
		/*PlayService p = (a,b) -> a-b;
	    System.out.println(p.operator(14, 12));
	    System.out.println(p.operator(13, 11));
	    */

	}

	public void StaticPlusTime(Long time){
		staticVar = NVLS(staticVar)+ time;
	}
	
	private String NVLS(Object str){
		if(str == null){
			return "";
		}else{
			return str.toString();
		}
	}
}
