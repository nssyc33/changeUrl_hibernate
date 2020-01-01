package testGitHub.io.Controller;

import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import testGitHub.io.Entity.SessionVo;
import testGitHub.io.Service.GetUrlName;
import testGitHub.io.Service.MakeShortUrlName;

@Controller
@SessionAttributes("sessionVo")
public class UrlController {

	@Autowired
	MakeShortUrlName makeShortUrlName;
	
	@Autowired
	GetUrlName getUrlName;
	
	@Autowired
    ServletContext context;
	
	@RequestMapping("/exSubUrl/view.do")
	public ModelAndView view(HttpSession session) throws Exception{	
		SessionVo sessionVo = new SessionVo();
		sessionVo.setAge(39);
		sessionVo.setJob("Web Programmer");
		sessionVo.setMarriedYn(false);
		sessionVo.setName("Seol YoungCheol");
		sessionVo.setSex("Male");
		ModelAndView md = new ModelAndView();
		md.addObject("sessionVo", sessionVo);
		md.addObject("list", getUrlName.getUrlData());
		md.setViewName("insertlist");
		return md;
	}
	
	@RequestMapping("/exSubUrl/insertData.do")
	public String insertUrl(@RequestParam HashMap hm, 
			                 HttpServletRequest req,
			                 HttpSession session,
			                 RedirectAttributes ra) throws Exception{
		hm.put("port", req.getServerPort());
		makeShortUrlName.saveUrl(hm);
		SessionVo vo = (SessionVo)session.getAttribute("sessionVo");
		System.out.println(vo.getName());
		return "redirect:/exSubUrl/view.do";
	}	
	
	@RequestMapping("/{keys:.+}")
	public String callRealUrl(@PathVariable String keys,  HttpServletRequest req) throws Exception{
		String asOriUrl = getUrlName.getOriginUrl(keys);
		return "redirect:http://"+asOriUrl;
	}
}
