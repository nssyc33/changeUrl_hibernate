package testGitHub.io.Service;

import static org.junit.Assert.*;

import org.junit.Test;

public class MakeShortUrlStandardFrameTest {

	private static final String mainString = "1234567890AaBbCcDdEeFfGghHiIjJkKLlmMNnOopPqQRrSsTtuUvVWwXxyY";
	
	@Test
	public void test() {
		MakeShortUrlName makeShortUrlName = new MakeShortUrlName();
		assertEquals("11111111", makeShortUrlName.makeShortUrlStandardFrame());       
	}

}
