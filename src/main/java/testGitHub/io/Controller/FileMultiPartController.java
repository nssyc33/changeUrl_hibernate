package testGitHub.io.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@MakeAnnoController
@SessionAttributes("sessionVo")
public class FileMultiPartController {

	/*@Autowired
	private MultipartFile file;*/
	
	@RequestMapping("/exSubUrl/multiPart.do")
	public ModelAndView File(Model model, 
			           @RequestParam(required = false) String message){
		System.out.println("message 확인 합니다 : "+message);
		ModelAndView mv = new ModelAndView();
		mv.addObject("message", message);
		mv.setViewName("multiPart");
		return mv;
	}
	
	@RequestMapping("/exSubUrl/fileUpload.do")
	public String fileUpload(@RequestParam MultipartFile file,
			                 RedirectAttributes redirect) {
	    System.out.println("getName : " + file.getName());
	    System.out.println("getOriginalFileName : " + file.getOriginalFilename());
	    redirect.addAttribute("message", file.getOriginalFilename()+ " uploading complete");
		return "redirect:/exSubUrl/multiPart.do";
	}
	
}
