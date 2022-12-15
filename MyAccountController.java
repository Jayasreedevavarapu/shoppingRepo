package com.fixitytech.shoppy;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MyAccountController {

	@RequestMapping(value="/MyAccount" ,method=RequestMethod.GET)
	public String myAccount()
	{
		
		return "MyAccount";
	}
	
	
	
	
}




