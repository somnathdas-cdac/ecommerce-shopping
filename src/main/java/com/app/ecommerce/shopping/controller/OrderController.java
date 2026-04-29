package com.app.ecommerce.shopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;




import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
//---------------------------------------------------
import com.app.ecommerce.shopping.repository.AllProductRepository;


//--------------------------
import com.app.ecommerce.shopping.repository.OrderRepository;
import com.app.ecommerce.shopping.model.Order;
//--------------------------




@Controller
public class OrderController {

    @Autowired
    private AllProductRepository allProductRepository;
    private OrderRepository orderRepository;
    
    

    
    
    
    
    @GetMapping("/")
    public String index(Model model) {
        // Fetch products (assuming each has name, price, and imageName fields)
       // model.addAttribute("products", allProductRepository.getAllProducts());
    	model.addAttribute("products", allProductRepository.findAll());
        model.addAttribute("content", "index :: index-content");
        return "shoping/main"; 
       // return "index";
    }
    
    
    @GetMapping("/addToCart")
    public String addToCart(@RequestParam("name") String name, 
                            @RequestParam("price") Double price, 
                            HttpSession session, Model model) {
        
    	
    	 // 1. Check if user is logged in
        String userEmail = (String) session.getAttribute("userEmail");
        if (userEmail == null) {
            return "redirect:/login"; // Force login if session expired or missing
        }
    	
    	
    	
        // 1. Get the current cart from the session, or create a new one if it doesn't exist
        List<Map<String, Object>> cart = (List<Map<String, Object>>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }

        // 2. Create a map for the new item and add it to the list
        Map<String, Object> item = new HashMap<>();
        item.put("name", name);
        item.put("price", price);
        item.put("quantity", 1);
        cart.add(item);

        // 3. Save the updated list back to the session
        session.setAttribute("cart", cart);
        
        
        
        
        // Pass the email to the model if you want to display "User's Cart"
        model.addAttribute("userEmail", userEmail);

        return "shoping/cart"; 
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /*
    //ok code........
    @GetMapping("/addToCart")
    public String addToCart(@RequestParam("name") String name, 
                            @RequestParam("price") Double price, 
                            Model model) {
        model.addAttribute("itemName", name);
        model.addAttribute("itemPrice", price);
        return "shoping/cart"; // Redirects to cart.html
        }
        
       */ 
        
        

    /*
    @GetMapping("/")
    public String index(Model model) {
        // "content" tells layout/main.html which fragment to load in the main window
        model.addAttribute("content", "index :: index-content");
        return "shoping/main"; 
    }
    
    */
    
    /*

    //@GetMapping({"/", "/product"})
    @GetMapping("/product")
    public String showProductPage(Model model) {
        // Fixed item details
        model.addAttribute("itemName", "Premium Laptop");
        model.addAttribute("price", 1200.00);
        return "product-page";
    }

    */
    
    
    
    
    /*
    @PostMapping("/submit-order")
    public String submitOrder(@RequestParam String itemName, 
                              @RequestParam int quantity, 
                              @RequestParam double price) {
       
    	
    	Order order = new Order();
        order.setItemName(itemName);
        order.setQuantity(quantity);
        order.setUnitPrice(price);
        order.setTotalPrice(price * quantity);
        
        orderRepository.save(order); // Inserts into DB
        
        //return "redirect:/payment"; // Redirects to payment
        return "payment"; // Redirects to payment
        }
        
      */  
    
}
