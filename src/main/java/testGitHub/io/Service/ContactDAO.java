package testGitHub.io.Service;

import java.util.List;

public interface ContactDAO {
    public void addContact(UrlData urlData);
    public List<UrlData> listContact();
    public void removeContact(int id);
}
