package testGitHub.io.AnotherService;

public class AnotherService {

	
	private TheOtherService theOtherService;
	
	
	public String changeString(String str){
		return "change_"+ theOtherService.changeString(str);
	}

	public void setTheOtherService(TheOtherService theOtherService) {
		this.theOtherService = theOtherService;
	}
	
}
