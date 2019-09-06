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
	
	public String nextIndexStandardKey(String stnKey){
		StringBuilder stnKeyBuilder = new StringBuilder(stnKey);
		if(stnKey.indexOf("Y")> -1){
			if(stnKey.substring(7,8).equals("Y")){
				if("YYYYYYYY".equals(stnKey)){
					stnKeyBuilder = new StringBuilder("11111111");
				}else{
					stnKeyBuilder = makeStringBuilder(8, stnKeyBuilder, stnKey);
				}
			}else if(stnKey.substring(6,7).equals("Y")){
				stnKeyBuilder = makeStringBuilder(7, stnKeyBuilder, stnKey);
			}else if(stnKey.substring(5,6).equals("Y")){
				stnKeyBuilder = makeStringBuilder(6, stnKeyBuilder, stnKey);
			}else if(stnKey.substring(4,5).equals("Y")){
				stnKeyBuilder = makeStringBuilder(5, stnKeyBuilder, stnKey);
			}else if(stnKey.substring(3,4).equals("Y")){
				stnKeyBuilder = makeStringBuilder(4, stnKeyBuilder, stnKey);
			}else if(stnKey.substring(2,3).equals("Y")){
				stnKeyBuilder = makeStringBuilder(3, stnKeyBuilder, stnKey);
			}else if(stnKey.substring(1,2).equals("Y")){
				stnKeyBuilder = makeStringBuilder(2, stnKeyBuilder, stnKey);
			}else if(stnKey.substring(0,1).equals("Y")){
				stnKeyBuilder = new StringBuilder("11111111");
			}
		}else{
			String stdVar = stnKey.substring(7,8);
			int position = mainString.indexOf(stdVar);
			char as = mainString.charAt(position+1);
			stnKeyBuilder.setCharAt(7, as);
		}
		return stnKeyBuilder.toString();
	}
	
	private StringBuilder makeStringBuilder(int index, StringBuilder stnKeyBuilder, String stnKey){
		int po = 10;
		if(stnKey.substring(0,index+1).lastIndexOf("Y") > -1){
			int yIndex = stnKey.substring(0,index).lastIndexOf("Y");
			for(int i=index-1;i>-1;i--){
				if(!"Y".equals(stnKey.substring(i,i+1)) && yIndex!= i){
					po = i;
					break;
				}
			}
			String stdVar = stnKey.substring(0,index).substring(po, po+1);
			int position = mainString.indexOf(stdVar);
			char as = mainString.charAt(position+1);
			for(int j=po; j<8; j++){
				if(j == po){
					stnKeyBuilder.setCharAt(j, as);
				}else{
					stnKeyBuilder.setCharAt(j, '1');
				}
			}
		}else{	
			po = index-1;
			String stdVar = stnKey.substring(0,index).substring(po, po+1);
			int position = mainString.indexOf(stdVar);
			char as = mainString.charAt(position+1);
			for(int j=po; j<8; j++){
				if(j == po){
					stnKeyBuilder.setCharAt(j, as);
				}else{
					stnKeyBuilder.setCharAt(j, '1');
				}
			}
		}
		return stnKeyBuilder;
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
