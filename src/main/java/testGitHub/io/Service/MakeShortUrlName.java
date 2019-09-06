package testGitHub.io.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.SystemPropertyUtils;

@Transactional
@Service
public class MakeShortUrlName {
	
	@Autowired
    private UrlDataDAO urlDataDAO;
	
	private static final String mainString = "1234567890AaBbCcDdEeFfGghHiIjJkKLlmMNnOopPqQRrSsTtuUvVWwXxyY";

	public int duplicateCheckStandardKey(String standardKey){
		int count = urlDataDAO.getExistsUrlCount(standardKey);
		return count;
	}
	
	public String makeShortUrl(){
		String standardKey = makeShortUrlStandardFrame();
		if(duplicateCheckStandardKey(standardKey) > 0){
			return nextIndexStandardKey(standardKey);
		}else{
			return standardKey;
		}
	}
	
	public String makeShortUrlStandardFrame(){
		String result = "";
		int startIndex = Integer.parseInt(new SimpleDateFormat("ss").format(System.currentTimeMillis()));
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
	
	
	public ArrayList<UrlData> getUrlData() throws Exception{
		return (ArrayList<UrlData>) urlDataDAO.listUrlData();
	}
	
	public void saveUrl(HashMap asMap) throws Exception{
		try{
			UrlData ud = new UrlData();
			String newKey = this.makeShortUrl();
			ud.setId(newKey);
			ud.setOriUrl((String)asMap.get("oriUrl"));
			ud.setSubUrl("http://localhost:"+(Integer)asMap.get("port")+"/"+newKey);
			ud.setSubKey(newKey);
			urlDataDAO.addUrl(ud);
		}catch(Exception e){
			throw new Exception("저장 도중 에러가 발생하였습니다.");
		}
	}
	
	public String getOriUrl(String standardKey){
		return urlDataDAO.getOriUrl(standardKey);
	}
}
