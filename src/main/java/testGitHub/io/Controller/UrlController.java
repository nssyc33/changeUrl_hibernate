package testGitHub.io.Controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import testGitHub.io.Service.MakeShortUrlName;

@Controller
public class UrlController {

	@Autowired
	MakeShortUrlName makeShortUrlName;
	
	@Autowired
    ServletContext context;
	
	@RequestMapping("/exSubUrl/view.do")
	public String view(Model md, HttpServletRequest req) throws Exception{		
		md.addAttribute("list", makeShortUrlName.getUrlData());
		return "insertlist";
	}
	
	@RequestMapping("/exSubUrl/insertData.do")
	public String insertUrl(@RequestParam HashMap hm, 
			                 HttpServletRequest req, 
			                 RedirectAttributes ra) throws Exception{
		hm.put("port", req.getServerPort());
		makeShortUrlName.saveUrl(hm);
		return "redirect:/exSubUrl/view.do";
	}	
	
	/**
	 * @param subName
	 * @param req
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/{keys:.+}")
	public String callRealUrl(@PathVariable String keys,  HttpServletRequest req) throws Exception{
		System.out.println("값을 확인 합니다. : "+keys);
		if(context.getAttribute("urlData") == null){
			return "";
		}else{
			ArrayList list = (ArrayList) context.getAttribute("urlData");
//			String asOriUrl = makeShortUrlName.getOriUrl(subName, list);
//			return "redirect:http://"+asOriUrl;
			return "";
		}
	}	
	
//	@RequestMapping("/exSubUrl/insertData.do")
//	public String insertData(@RequestParam HashMap hm, 
//			                 HttpServletRequest req, 
//			                 RedirectAttributes ra) throws Exception{
//		ArrayList list = (ArrayList) context.getAttribute("urlData");
//		if(list == null){
//			list = new ArrayList();
//		}
//		if(!makeShortUrlName.duplicateExistsYn(hm, list)){
//			HashMap hma = new HashMap();
//			String nowTime = new SimpleDateFormat("ss").format(System.currentTimeMillis());
//			int nowSec = Integer.parseInt(nowTime);
//			String asKey = makeShortUrlName.makeShortUrl(list, nowSec);
//			hma.put("oriUrl", hm.get("oriUrl"));
//			hma.put("subUrl", "http://localhost:"+req.getServerPort()+"/"+asKey);
//			hma.put("subKey", asKey);
//			list.add(hma);
//			
//			makeShortUrlName.saveData(hma);
//			
//			ra.addAttribute("dupYn", "N");
//		}else{
//			ra.addAttribute("dupYn", "Y");
//		}
//		return "redirect:/exSubUrl/view.do";
//	}	
}
