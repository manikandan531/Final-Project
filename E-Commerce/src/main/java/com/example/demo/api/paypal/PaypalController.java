package com.example.demo.api.paypal;


 import org.springframework.beans.factory.annotation.Autowired; 
 import org.springframework.stereotype.Controller; 
 import org.springframework.web.bind.annotation.GetMapping; 
 import org.springframework.web.bind.annotation.ModelAttribute; 
 import org.springframework.web.bind.annotation.PostMapping; 
 import org.springframework.web.bind.annotation.RequestParam;
 import com.paypal.api.payments.Links; import com.paypal.api.payments.Payment;
 import com.paypal.base.rest.PayPalRESTException;
  
 @Controller public class PaypalController 
 {
  
  @Autowired PaypalService service;
  
  	public static final String SUCCESS_URL = "http://localhost:8080/pay/success";
  	public static final String CANCEL_URL = "http://localhost:8080/pay/cancel";
 
  @GetMapping("/")
  public String home() 
  { 
	  return "home"; 
  }
  
  @PostMapping("/pay")
  public String payment(@ModelAttribute("order") Order order) 
  { 
	  try 
	  { Payment payment = service.createPayment(order.getPrice(), order.getCurrency(), order.getMethod(), order.getIntent(),
			  	order.getDescription(),SUCCESS_URL,CANCEL_URL); 
	  for(Links link:payment.getLinks()) { if(link.getRel().equals("approval_url")) { return
  "redirect:"+link.getHref(); 
	  } 
   }
  
  } 
	  catch (PayPalRESTException e)
	  {
  
		  e.printStackTrace();
	  } 
	  return "redirect:/"; 
	 }
  
 @GetMapping(value = CANCEL_URL) 
 public String cancelPay() 
 	{ 
	 return "cancel";
 	}
  
  @GetMapping(value = SUCCESS_URL) 
  public String successPay(@ModelAttribute("paymentId") String paymentId, @ModelAttribute("PayerID") String payerId) 
  { 
	  try 
	  { 
		  Payment payment = service.executePayment(paymentId, payerId);
		  System.out.println(payment.toJSON()); if
		  (payment.getState().equals("approved")) 
		  { 
			  return "success";
			  }
		  }
	  catch
	  (PayPalRESTException e) 
	  {
		  System.out.println(e.getMessage()); 
	  } 
	   return "success"; 
	   
  }
  
  }
 
