package testGitHub.io.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import testGitHub.io.Entity.TestVo;
import testGitHub.io.Entity.TestVoXML;
import testGitHub.io.Service.XmlService;

@Controller
public class RestfulController {

	@Autowired
	XmlService xmlService;
	
	@RequestMapping(value="/exSubUrl/getData")
	@ResponseBody
	public Map getData(@RequestBody HashMap<String, Object> asMap
			){
		System.out.println("데이터 HashMap확인 : "+ asMap.toString());
		Map result = new HashMap();
		TestVo vo = new TestVo();
		vo.setInputFirst("dataFirst");
		vo.setInputSecond("dataSecond");
		result.put("result", "Y");
		result.put("data", vo);
		return result;	
	}
	
	@RequestMapping(value="/exSubUrl/getDatatoXml",method=RequestMethod.GET)
	@ResponseBody
	public TestVoXML getDatatoXml(){
		List<TestVo> list = xmlService.getDataList();
		TestVoXML tx = new TestVoXML("success", list);
		return tx;
	}
}
