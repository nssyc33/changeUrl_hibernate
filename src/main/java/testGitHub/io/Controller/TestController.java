package testGitHub.io.Controller;

import java.io.File;
import java.io.IOException;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

	@Autowired
	ResourceLoader resourceloader;
	
	@RequestMapping("/exSubUrl/downloadtest_real.do")
	public ResponseEntity<Resource> test() throws IOException{
		Resource resource = resourceloader.getResource("classpath:"+"/image/ch.jpg");
		File file = resource.getFile();
		
		/*apache tika core*/
		Tika tika = new Tika();
		String mediaType = tika.detect(file);
		System.out.println("mediaType : " + mediaType);
		
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+resource.getFilename())
				.header(HttpHeaders.CONTENT_TYPE, mediaType)
				.header(HttpHeaders.CONTENT_LENGTH, file.length()+"")
				.body(resource);
	}
}