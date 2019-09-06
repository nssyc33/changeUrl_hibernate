package testGitHub.io.Service;

import static org.junit.Assert.*;

import org.junit.Test;

public class NextIndexStandardKeyTest {

	private static final String mainString = "1234567890AaBbCcDdEeFfGghHiIjJkKLlmMNnOopPqQRrSsTtuUvVWwXxyY";
	
	@Test
	public void test() {
		MakeShortUrlName makeShortUrlName = new MakeShortUrlName();
		assertEquals("YYYYYYY2", makeShortUrlName.nextIndexStandardKey("YYYYYYY1")); 
		assertEquals("1YYYYYY2", makeShortUrlName.nextIndexStandardKey("1YYYYYY1"));
		assertEquals("12345YYY", makeShortUrlName.nextIndexStandardKey("12344YYY"));
		assertEquals("12Y43YYY", makeShortUrlName.nextIndexStandardKey("12Y42YYY"));
	}

}
