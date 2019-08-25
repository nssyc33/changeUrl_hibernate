package testGitHub.io.Service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class ContactDAOImpl implements ContactDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addContact(UrlData urlData) {
        this.sessionFactory.getCurrentSession().save(urlData);
    }

    @SuppressWarnings("unchecked")
    public List<UrlData> listContact() {
        System.out.println("SessionFactory is " + this.sessionFactory);
        Query q = this.sessionFactory.getCurrentSession().createQuery("from UrlData where 1 = 1");
        return q.list();
    }

    public void removeContact(int id) {
    	UrlData contact = (UrlData) this.sessionFactory.getCurrentSession().load(UrlData.class, id);

        if ( null != contact ) {
            this.sessionFactory.getCurrentSession().delete(contact);
        }
    }
}
