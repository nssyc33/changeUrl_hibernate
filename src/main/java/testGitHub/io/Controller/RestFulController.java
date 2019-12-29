package testGitHub.io.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import testGitHub.io.Entity.TestVo;

@Controller
public class RestFulController {

	
	@RequestMapping(value="/exSubUrl/getData", method=RequestMethod.GET)
	@ResponseBody
	public Map getData(){
		Map result = new HashMap();
		result.put("result", "Y");
		TestVo vo = new TestVo();
		vo.setInputFirst("dataFirst");
		vo.setInputSecond("dataSecond");
		result.put("data", vo);
		return result;	
	}
}
