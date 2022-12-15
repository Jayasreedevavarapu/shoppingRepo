package com.fixitytech.shoppy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fixitytech.shoppy.dao.UserDAO;
import com.fixitytech.shoppy.model.User;

@Controller
public class ProfileController {
 
	@Autowired
	UserDAO userDao;
	
	
	@RequestMapping(value="/Profile" ,method=RequestMethod.GET)
	public String OrderSuccess(HttpServletRequest request,HttpServletResponse resopnse,HttpSession hs,Model model)
	{
		 hs=request.getSession(true);
        String uid=(String)hs.getAttribute("uid");
		User users=userDao.viewUser(uid);
		if(users!=null)
		{
		model.addAttribute("users", users);
		return "Profile";
		
	   }
     	else
     	{
     		return "redirect:index";
     		
     	}
		
	}
	}

