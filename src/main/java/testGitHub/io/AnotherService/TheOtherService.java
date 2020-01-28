package testGitHub.io.AnotherService;

import org.springframework.stereotype.Component;

@Component
public class TheOtherService {
	
	public String changeString(String str){
		return "TheOtherServiceChange_"+str;
	}
}
