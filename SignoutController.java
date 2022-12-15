package com.fixitytech.shoppy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SignoutController {

	@RequestMapping(value="/Signout" ,method=RequestMethod.GET)
	public String myAccount(HttpServletRequest request,HttpSession hs)
	{
		 hs=request.getSession(true);
		hs.invalidate();
	    
		return "redirect:index";
	}
	
	
}
