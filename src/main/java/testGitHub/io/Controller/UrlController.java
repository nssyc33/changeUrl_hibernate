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
	
	@RequestMapping("/{keys:.+}")
	public String callRealUrl(@PathVariable String keys,  HttpServletRequest req) throws Exception{
		String asOriUrl = makeShortUrlName.getOriUrl(keys);
		return "redirect:http://"+asOriUrl;
	}	
}
