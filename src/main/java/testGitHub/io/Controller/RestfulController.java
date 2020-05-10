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

import ExLib.io.ExLibrary;
import testGitHub.io.Entity.TestVo;
import testGitHub.io.Entity.TestVoXML;
import testGitHub.io.Interface.PlayService;
import testGitHub.io.Service.CsvParsingService;
import testGitHub.io.Service.XmlService;

@Controller
public class RestfulController {

	@Autowired
	XmlService xmlService;
	
	@Autowired
	CsvParsingService csvParsingService;
	
	@RequestMapping(value="/exSubUrl/getData")
	@ResponseBody
	public Map getData(@RequestBody HashMap<String, Object> asMap,
					   @RequestParam Map rMap	
			) throws Exception{
		System.out.println("데이터 HashMap확인 : "+ asMap.toString());
		System.out.println("데이터 HashMap확인 : "+ asMap.get("sendDataFirst"));
		System.out.println("데이터 HashMap확인 : "+ rMap.get("sendDataFirst"));
		Map result = new HashMap();
		TestVo vo = new TestVo();
		
//		csvParsingService.ioParsing();
//		csvParsingService.nioParsing();
		
//		csvParsingService.fileTransfer();
//		csvParsingService.fileTransfer_useBuffer();
//		csvParsingService.fileTransferNio();
		
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
	
	@RequestMapping(value="/exSubUrl/lambdaTest")
	@ResponseBody
	public Map lambdaTest(@RequestBody Map asMap) throws Exception{
		
//		System.out.println("데이터 HashMap확인 : "+ asMap.toString());
		Map result = new HashMap();
		System.out.println("값 확인합니다. sendDataFirst : "+asMap.get("sendDataFirst"));
		System.out.println("값 확인합니다. sendDataSecond : "+asMap.get("sendDataSecond"));
		PlayService p = () -> { return "test";};
		System.out.println(p.callMessage());
		
		result.put("result", "done");
		return result;	
	}
}
