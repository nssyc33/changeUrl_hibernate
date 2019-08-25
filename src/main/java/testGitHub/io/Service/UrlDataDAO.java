package testGitHub.io.Service;

import java.util.List;

public interface UrlDataDAO {
    public void addUrlData(UrlData urlData);
    public List<UrlData> listUrlData();
    public void removeUrlData(int id);
}
