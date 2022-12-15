package com.fixitytech.shoppy;

import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fixitytech.shoppy.dao.ItemDAO;
import com.fixitytech.shoppy.model.CartItem;
import com.fixitytech.shoppy.model.Item;

@Controller
public class IndexController {
   @Autowired
	  ItemDAO itemDao;
	
	@RequestMapping("/index")
	public String  home(HttpSession hs,Model model)
	{
		List<CartItem> cart=(List<CartItem>)hs.getAttribute("cart");
		if(cart==null)
		{
            cart=new Vector<CartItem>();
            hs.setAttribute("cart", cart);
		}
		List<Item> products=itemDao.getItems();
		model.addAttribute("prs", products);
		
		return "index";
		
		
		
	}
	
	
	
}
