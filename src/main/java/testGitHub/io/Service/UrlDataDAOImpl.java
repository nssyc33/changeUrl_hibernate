package testGitHub.io.Service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class UrlDataDAOImpl implements UrlDataDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addUrl(UrlData urlData) {
        this.sessionFactory.getCurrentSession().save(urlData);
    }

    @SuppressWarnings("unchecked")
    public List<UrlData> listUrlData() {
        System.out.println("SessionFactory is " + this.sessionFactory);
        Query q = this.sessionFactory.getCurrentSession().createQuery("from UrlData where 1 = 1");
        return q.list();
    }

    public void removeUrlData(int id) {
    	UrlData urlData = (UrlData) this.sessionFactory.getCurrentSession().load(UrlData.class, id);
        if ( null != urlData ) {
            this.sessionFactory.getCurrentSession().delete(urlData);
        }
    }
    
    public int getExistsUrlCount(String standardKey) {
    	Query q = this.sessionFactory.getCurrentSession().createQuery("from UrlData where 1 = 1 and subKey = '"+standardKey+"'");
        return q.list().size();
    }
    
    public String getOriUrl(String standardKey) {
    	Query q = this.sessionFactory.getCurrentSession().createQuery("from UrlData where 1 = 1 and subKey = '"+standardKey+"'");
        ArrayList resultList = (ArrayList) q.list();
        UrlData urlData = (UrlData)resultList.get(0);
        return urlData.getOriUrl();
    }
}
