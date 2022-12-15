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
public class SigninController {
    
	@Autowired
	UserDAO userDao;
	
	
	@RequestMapping(value="/SigninController" ,method=RequestMethod.GET)
	public String login()
	{
		return "Login";
	}
	@RequestMapping(value="/SigninController" ,method=RequestMethod.POST)
	public String signin(User user,Model model,HttpServletRequest response,HttpServletRequest request,HttpSession hs)
	{
		
		String uname=request.getParameter("uname");
		String upassword=request.getParameter("upassword");
		String c=request.getParameter("cont");
		
	if(uname.equals("admin") && upassword.equals("admin"))
		{
             hs=request.getSession(true);
	        model.addAttribute("admin",upassword);
	        return "redirect:AdminController";
	        

     } 
 else
    {
       boolean b=userDao.validate(uname,upassword);
		
		if(b)
		{
			 hs=request.getSession(true);
			hs.setAttribute("uid", uname);
			if(c!=null && c.length()>0)
				return "redirect:"+c;
                
            else
            	return "redirect:index";
			
			
		}
		else
		{
			model.addAttribute("err","invalid username or password");
			return "Login";
			
		}
		
		
		
	}
		
	}
}
