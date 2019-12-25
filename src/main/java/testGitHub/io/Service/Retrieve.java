package testGitHub.io.Service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import testGitHub.io.Dao.UrlDataDAO;
import testGitHub.io.Entity.UrlData;
@Transactional
@Service
public class Retrieve {

	@Autowired
    private UrlDataDAO urlDataDAO;
	
	public ArrayList<UrlData> getUrlData() throws Exception{
		return (ArrayList<UrlData>) urlDataDAO.listUrlData();
	}
	
	public String getOriginUrl(String standardKey){
		return urlDataDAO.getOriUrl(standardKey);
	}
	
	public boolean dupCheckStandardKey(String standardKey){
		if(urlDataDAO.getExistsUrlCount(standardKey)>0){
			return true;
		}else{
			return false;
		}
	}
}
