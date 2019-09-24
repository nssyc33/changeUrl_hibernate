package testGitHub.io.Controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalHandler{
	
	@ExceptionHandler(Exception.class)
	public ModelAndView common(Exception e) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("error");
		mav.addObject("exception", e);
		return mav;
	}
}
