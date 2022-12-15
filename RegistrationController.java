package com.fixitytech.shoppy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fixitytech.shoppy.dao.UserDAO;
import com.fixitytech.shoppy.model.User;

@Controller
public class RegistrationController {
     
	@Autowired
	UserDAO userDao;
	
	
	@RequestMapping(value="/RegistrationController" ,method=RequestMethod.GET)
	public String register()
	{
		return "Register";
	}
	@RequestMapping(value="/RegistrationController" ,method=RequestMethod.POST)
	public String signup(User user,Model model,HttpServletRequest response,HttpServletRequest request,HttpSession hs)
	{
		
		String c=request.getParameter("cont");
		
		boolean b=userDao.save(user);
	
		
		if(b)
		{
			 hs=request.getSession(true);
			hs.setAttribute("uid", user);
			if(c!=null && c.length()>0)
				return "redirect:"+c;
            else
				return "redirect:index";
			
		}
		else
		{
			model.addAttribute("err"," pls try again later!");
			//doGet(request, response);
			return "redirect:RegistrationController";
		}
		
	}
}

