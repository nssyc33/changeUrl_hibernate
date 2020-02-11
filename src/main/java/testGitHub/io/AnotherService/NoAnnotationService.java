package testGitHub.io.AnotherService;

import org.springframework.beans.factory.annotation.Autowired;

import testGitHub.io.Service.YesAnnotationService;

public class NoAnnotationService {

	@Autowired
	YesAnnotationService yesAnnotationService;
	
	public void callService(){
		System.out.println(yesAnnotationService.callService("호출!"));
	}
}
