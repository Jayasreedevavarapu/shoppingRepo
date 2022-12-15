package com.fixitytech.shoppy;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ViewCartController {

	
	
	
	@RequestMapping(value="/cart" ,method=RequestMethod.GET)
	public String viewCart()
	{
		return "cart";
	}
}
