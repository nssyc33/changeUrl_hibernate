package testGitHub.io.Service;

import java.text.SimpleDateFormat;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import testGitHub.io.Dao.UrlDataDAO;
import testGitHub.io.Entity.UrlData;

public class Save {

	@Autowired
    private UrlDataDAO urlDataDAO;
	
	private static final String mainString = "1234567890AaBbCcDdEeFfGghHiIjJkKLlmMNnOopPqQRrSsTtuUvVWwXxyY";

	
	
	public String makeOtherExpUrl(){
		int startIndex = Integer.parseInt(new SimpleDateFormat("ss").format(System.currentTimeMillis()));
		String standardKey = makeShortUrlStandardFrame(startIndex);
		System.out.println(standardKey);
		//if(dupCheckStandardKey(standardKey)){
			return nextIndexStandardKey(standardKey);
		//}else{
		//	return standardKey;
		//}
	}
	
	public String makeShortUrlStandardFrame(int startIndex){
		String result = "";
		if(startIndex == 0){
			startIndex = 1;
		}
		
		for(int i=0;i<8;i++){
			result += mainString.substring(startIndex, startIndex+1);
		}
		
		return result;
	}
	
	public String nextIndexStandardKey(String standardKey){
		StringBuffer stnKeyBuffer = new StringBuffer(standardKey);
		StringBuffer mainStringBuilder = new StringBuffer(mainString);
		int findNotMIndex = -1;
		String subsIndexString = "";
		for(int i = stnKeyBuffer.length()-1; i > -1; i--){
			subsIndexString = String.valueOf(stnKeyBuffer.charAt(i));
			if(!subsIndexString.equals("Y")){
				findNotMIndex = i;
				break;
			}
		}
		if(findNotMIndex == -1){
			stnKeyBuffer = new StringBuffer("11111111"); 
		}else{
			stnKeyBuffer.setCharAt(findNotMIndex, mainString.charAt(mainStringBuilder.indexOf(subsIndexString)+1));
		}
		return stnKeyBuffer.toString();
	}
	
	public void saveUrl(Map asMap) throws Exception{
		try{
			UrlData ud = new UrlData();
			String newKey = this.makeOtherExpUrl();
			ud.setId(newKey);
			ud.setOriUrl((String)asMap.get("oriUrl"));
			ud.setSubUrl("http://localhost:"+(Integer)asMap.get("port")+"/"+newKey);
			ud.setSubKey(newKey);
			urlDataDAO.addUrl(ud);
		}catch(Exception e){
			throw new Exception("저장 도중 에러가 발생하였습니다.");
		}
	}
}
