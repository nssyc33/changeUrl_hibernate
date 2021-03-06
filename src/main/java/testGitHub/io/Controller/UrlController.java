package testGitHub.io.Controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import testGitHub.io.AnotherService.AnotherService;
import testGitHub.io.AnotherService.NoAnnotationService;
import testGitHub.io.Entity.SessionSubVo;
import testGitHub.io.Entity.SessionVo;
import testGitHub.io.Service.GetUrlName;
import testGitHub.io.Service.MakeShortUrlName;
import testGitHub.io.Service.StaticVariableService;

@MakeAnnoController
@SessionAttributes("sessionVo")
public class UrlController {

	@Autowired
	MakeShortUrlName makeShortUrlName;
	
	@Autowired
	GetUrlName getUrlName;
	
	@Autowired
    ServletContext context;
	
	@Autowired
	AnotherService anotherService;
	
	@Autowired
	StaticVariableService staticVariableService;
	
	@Autowired
	NoAnnotationService noAnnotationService;
	
	@RequestMapping("/exSubUrl/view.do")
	public ModelAndView view(HttpSession session,
							 String testString//--> 들어 오든지 말든지 에러가 안나게 된다.
			) throws Exception{	
		System.out.println("확인합니다. : "+ testString);
		SessionVo sessionVo = new SessionVo();
		SessionSubVo sessionSubVo = new SessionSubVo();
		sessionVo.setAge(39);
		sessionVo.setJob("Web Programmer");
		sessionVo.setMarriedYn(true);
		sessionVo.setName("Seol YoungCheol");
		sessionVo.setSex("Male");
		sessionSubVo.setTest1("value1");
		sessionSubVo.setTest2("value2");
		sessionSubVo.setTest3("value3");
		session.setAttribute("sessionSubVo", sessionSubVo);
		
		ModelAndView md = new ModelAndView();
		md.addObject("sessionVo", sessionVo);
		md.addObject("sessionSubVo", sessionSubVo);
		md.addObject("list", getUrlName.getUrlData());
		md.addObject("changeString", anotherService.changeString("test"));
		md.setViewName("insertlist");
		staticVariableService.StaticTest();
		staticVariableService.StaticPlusTime(System.currentTimeMillis());
		noAnnotationService.callService();
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
		
		
		SessionSubVo sessionSubVo = (SessionSubVo)session.getAttribute("sessionSubVo");
		System.out.println("확인해보시기 바랍니다 : test1 : "+sessionSubVo.getTest1());
		System.out.println("확인해보시기 바랍니다 : test2 : "+sessionSubVo.getTest2());
		System.out.println("확인해보시기 바랍니다 : test3 : "+sessionSubVo.getTest3());
		return "redirect:/exSubUrl/view.do";
	}	
	
	@RequestMapping("/{keys:.+}")
	public String callRealUrl(@PathVariable String keys,  HttpServletRequest req) throws Exception{
		String asOriUrl = getUrlName.getOriginUrl(keys);
		return "redirect:http://"+asOriUrl;
	}
	
	@RequestMapping("/exSubUrl/callRedirectTest.do")
	public String callRedirectTest(HttpServletRequest req, RedirectAttributes rd) throws Exception{
		rd.addAttribute("test1", "values1");
		rd.addAttribute("test2", "values2");
		String as[] = {"test1","test2","test3"};
		List list = Arrays.asList(as);
		System.out.println(list.size());
		rd.addAttribute("test3List", list);
		return "redirect:/exSubUrl/takeRedirectTest.do";
	}
	
	@RequestMapping("/exSubUrl/takeRedirectTest.do")
	public String takeRedirectTest(HttpServletRequest req, @RequestParam List test3List) throws Exception{
		System.out.println("test1's value : "+req.getParameter("test1"));
		System.out.println("test2's value : "+req.getParameter("test2"));
		System.out.println("test3's size : "+test3List.size());
		return "";
	}
	
	private String NVLS(Object ob){
		if(ob == null){
			return "";
		}else{
			return ob.toString();
		}
		
	}
	
}
