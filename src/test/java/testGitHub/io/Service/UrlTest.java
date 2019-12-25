package testGitHub.io.Service;

import static org.junit.Assert.assertThat;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import testGitHub.io.Dao.UrlDataDAO;
import testGitHub.io.Dao.UrlDataDAOImpl;
import testGitHub.io.Entity.UrlData;

public class UrlTest {

	@Test
	public void nextIndexStandardKey() {
		MakeShortUrlName msun = new MakeShortUrlName();
		assertThat(msun.nextIndexStandardKey("1111111Y"), CoreMatchers.is("1111112Y"));
		assertThat(msun.nextIndexStandardKey("111111YY"), CoreMatchers.is("111112YY"));
		assertThat(msun.nextIndexStandardKey("111211YY"), CoreMatchers.is("111212YY"));
		assertThat(msun.nextIndexStandardKey("111212YY"), CoreMatchers.is("111213YY"));
		assertThat(msun.nextIndexStandardKey("11121YYY"), CoreMatchers.is("11122YYY"));
	}
	
	@Test
	public void makeShortUrlStandardFrame(){
		MakeShortUrlName msun = new MakeShortUrlName();
		assertThat(msun.makeShortUrlStandardFrame(7), CoreMatchers.is("88888888"));
		assertThat(msun.makeShortUrlStandardFrame(50), CoreMatchers.is("uuuuuuuu"));
	}
	
	@Test
	public void saveUrl(){
		UrlDataDAO urlDataDAO = new UrlDataDAOImpl();
		UrlData ud = new UrlData();
		MakeShortUrlName msun = new MakeShortUrlName();
		String newKey = msun.makeOtherExpUrl();
		ud.setId(newKey);
		ud.setOriUrl("www.naver.com");
		ud.setSubUrl("http://localhost:"+8080+"/"+newKey);
		ud.setSubKey(newKey);
		urlDataDAO.addUrl(ud);
	}

}
