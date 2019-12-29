package testGitHub.io.Controller;

import java.io.File;
import java.util.HashMap;

import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import testGitHub.io.Entity.TestVo;

@Controller
public class ResponseEntityController {

	@Autowired
	private ResourceLoader resourceloader;
	
	private TestVo testVO;
	
	@ModelAttribute
	private TestVo test(TestVo vo){
		testVO = vo;
		return testVO;
	}
	
	@RequestMapping("/exSubUrl/downloadtest_real.do")
	public ResponseEntity<Resource> test(@RequestParam HashMap asMap,
										 @ModelAttribute HashMap modelMap,
										 @ModelAttribute TestVo vo
			) throws Exception{
		Resource resource = resourceloader.getResource("classpath:"+"/image/ch.jpg");
		File file = resource.getFile();
		
		System.out.println(asMap.get("inputFirst"));
		System.out.println(asMap.get("inputSecond"));
		System.out.println(modelMap.get("inputFirst"));
		System.out.println(modelMap.get("inputSecond"));
		System.out.println(vo.getInputFirst());
		System.out.println(vo.getInputSecond());
		System.out.println(testVO.getInputFirst());
		System.out.println(testVO.getInputSecond());
		
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