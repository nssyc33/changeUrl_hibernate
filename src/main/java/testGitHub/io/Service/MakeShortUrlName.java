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
	
	private String makeShortUrlStandardFrame(){
		String nowTime = new SimpleDateFormat("ss").format(System.currentTimeMillis());
		int nowSec = Integer.parseInt(nowTime);
		
		if(nowSec == 0){
			nowSec = 1;
		}
		int start = 60%nowSec;
		String result = "";
		for(int i=0;i<8;i++){
			result += mainString.substring(start, start+1);
		}
		return result;
	}
	
	private String nextIndexStandardKey(String stnKey){
		StringBuilder changeVar = new StringBuilder(stnKey);
		if(stnKey.indexOf("Y")> -1){
			if(stnKey.substring(7,8).equals("Y")){
				if("YYYYYYYY".equals(stnKey)){
					changeVar = new StringBuilder("11111111");
				}else{
					int po = 10;
					if(stnKey.substring(0,8).lastIndexOf("Y") > -1){
						int yIndex = stnKey.substring(0,8).lastIndexOf("Y");
						for(int i=7;i>-1;i--){
							if(!"Y".equals(stnKey.substring(i,i+1)) && yIndex!= i){
								po = i;
								break;
							}
						}
					}
					String stdVar = stnKey.substring(0,7).substring(po, po+1);
					int position = mainString.indexOf(stdVar);
					char as = mainString.charAt(position+1);
					for(int j=po; j<8; j++){
						if(j == po){
							changeVar.setCharAt(j, as);
						}else{
							changeVar.setCharAt(j, '1');
						}
					}
				}
			}else if(stnKey.substring(6,7).equals("Y")){
				changeVar = makeStringBuilder(6, changeVar, stnKey);
			}else if(stnKey.substring(5,6).equals("Y")){
				changeVar = makeStringBuilder(5, changeVar, stnKey);
			}else if(stnKey.substring(4,5).equals("Y")){
				changeVar = makeStringBuilder(4, changeVar, stnKey);
			}else if(stnKey.substring(3,4).equals("Y")){
				changeVar = makeStringBuilder(3, changeVar, stnKey);
			}else if(stnKey.substring(2,3).equals("Y")){
				changeVar = makeStringBuilder(2, changeVar, stnKey);
			}else if(stnKey.substring(1,2).equals("Y")){
				changeVar = makeStringBuilder(1, changeVar, stnKey);
			}else if(stnKey.substring(0,1).equals("Y")){
				changeVar = new StringBuilder("11111111");
			}
		}else{
			String stdVar = stnKey.substring(7,8);
			int position = mainString.indexOf(stdVar);
			char as = mainString.charAt(position+1);
			changeVar.setCharAt(7, as);
		}
		return changeVar.toString();
	}
	
	private StringBuilder makeStringBuilder(int index, StringBuilder changeVar, String stnKey){
		String pointVar = stnKey.substring(index,index+1);
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
					changeVar.setCharAt(j, as);
				}else{
					changeVar.setCharAt(j, '1');
				}
			}
		}else{	
			po = index-1;
			String stdVar = stnKey.substring(0,index).substring(po, po+1);
			int position = mainString.indexOf(stdVar);
			char as = mainString.charAt(position+1);
			for(int j=po; j<8; j++){
				if(j == po){
					changeVar.setCharAt(j, as);
				}else{
					changeVar.setCharAt(j, '1');
				}
			}
		}
		return changeVar;
	}
	
	public String getOriUrl(String searchKey, ArrayList list){
		String subKey = "";
		for(int i=0;i<list.size();i++){
			subKey = (String)((HashMap)list.get(i)).get("subKey");
			if(searchKey.equals(subKey)){
				return (String)((HashMap)list.get(i)).get("oriUrl");
			}
		}
		return "";
	}
	
	public ArrayList<UrlData> getUrlData(){
		try{
			ArrayList<UrlData> asList = (ArrayList<UrlData>) urlDataDAO.listUrlData();
			System.out.println("list 크기입니다. : "+asList.size());
			return asList;
		}catch(Exception e){
			System.out.println("에러가 발생했습니다.");
			System.out.println("error message : "+e.getMessage());
			return new ArrayList<UrlData>();
		}
	}
	
	public void saveUrl(HashMap asMap){
		try{
			UrlData ud = new UrlData();
			String newKey = this.makeShortUrl();
			ud.setId(newKey);
			ud.setOriUrl((String)asMap.get("oriUrl"));
			ud.setSubUrl("http://localhost:"+(String)asMap.get("port")+"/"+newKey);
			ud.setSubKey(newKey);
			urlDataDAO.addUrl(ud);
		}catch(Exception e){
			System.out.println("error message : "+e.getMessage());
		}
	}
}
