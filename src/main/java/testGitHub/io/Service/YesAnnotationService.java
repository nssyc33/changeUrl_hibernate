package testGitHub.io.Service;

import org.springframework.stereotype.Service;

@Service
public class YesAnnotationService {

	public String callService(String asVar){
		return "YesAnnotationService : "+ asVar; 
	}
}
