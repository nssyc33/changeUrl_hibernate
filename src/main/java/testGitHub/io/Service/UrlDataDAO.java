package testGitHub.io.Service;

import java.util.List;

public interface UrlDataDAO {
	
    public void addUrl(UrlData urlData);
    
    public List<UrlData> listUrlData();
    
    public void removeUrlData(int id);
    
    public int getExistsUrlCount(String standardKey);
    
    public String getOriUrl(String standardKey);
}
