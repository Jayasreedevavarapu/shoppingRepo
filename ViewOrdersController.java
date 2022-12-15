package com.fixitytech.shoppy;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fixitytech.shoppy.dao.OrderDAO;
import com.fixitytech.shoppy.model.Order;

@Controller
public class ViewOrdersController {
	
    @Autowired
	OrderDAO orderDao;
	
	@RequestMapping(value="/MyOrders" ,method=RequestMethod.GET)
	public String myOrders(HttpServletRequest request,HttpSession hs,Model model)
	{
		
		 hs=request.getSession(true);
        String uid=(String)hs.getAttribute("uid");
        
		List<Order> orders=orderDao.viewOrders(uid);
        
        if(orders!=null)
        {
        model.addAttribute("orders",orders);
        return  "ViewOrders";
        
        }
        else
        	return "MyAccount";
           
		
	}
}
