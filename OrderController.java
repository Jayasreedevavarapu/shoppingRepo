package com.fixitytech.shoppy;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fixitytech.shoppy.dao.OrderDAO;
import com.fixitytech.shoppy.model.CartItem;
import com.fixitytech.shoppy.model.Order;
import com.fixitytech.shoppy.model.OrderItem;

@Controller
public class OrderController {
     
	@Autowired
	OrderDAO orderDao;
	
	@RequestMapping(value="/order" ,method=RequestMethod.GET)
	public String takeOrder(Model model,HttpSession hs,HttpServletRequest request)
	{
		
	             hs=request.getSession(true);
		String uid=(String)hs.getAttribute("uid");
  if(uid!=null)
		{
		List<CartItem> cart=(List<CartItem>)hs.getAttribute("cart");
		double bill=0;
		if(cart!=null)
		{
		 double subAmount=0;
		 List<OrderItem> orderItems=new ArrayList<OrderItem>();
		 for(CartItem citem: cart)
		 {
			subAmount=citem.getPrice()*citem.getQuantity();
			bill=bill+subAmount;
		    OrderItem odItem=new OrderItem(citem.getId(),citem.getName(),citem.getPrice(),citem.getQuantity(),subAmount);
			orderItems.add(odItem);
		 }
			Order order=new Order();
			order.setBill(bill);
			order.setUserId(uid);
			order.setOrderItems(orderItems);
	try {	
		int ch=orderDao.addOrder(order);
		
	if(ch!=-1)
	{
		model.addAttribute("Od", ch);
		hs.removeAttribute("cart");
		return "redirect:OrderSuccess";
	    
		
	}
		
	else
	{
		return "cart"+"?msg=Something went wrong!try again later";
	}
		
		
	}
	catch(Exception e)
	{
		
		e.printStackTrace();
		
	}
	}
		else
			return "Login"+"?cont=cart";
		}



	 
	 
    	 return "index";

	
	 

}
}
