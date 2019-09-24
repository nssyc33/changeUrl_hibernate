package testGitHub.io.Dao;

import java.util.List;

import testGitHub.io.Entity.UrlData;

public interface UrlDataDAO {
	
    public void addUrl(UrlData urlData);
    
    public List<UrlData> listUrlData();
    
    public void removeUrlData(int id);
    
    public int getExistsUrlCount(String standardKey);
    
    public String getOriUrl(String standardKey);
}
