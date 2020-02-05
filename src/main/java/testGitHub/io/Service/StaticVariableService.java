package testGitHub.io.Service;

import org.springframework.stereotype.Service;

@Service
public class StaticVariableService {

	public static String staticVar;
	
	public void StaticTest(){
		System.out.println("staticVar 변수 확인  : " + staticVar);
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
