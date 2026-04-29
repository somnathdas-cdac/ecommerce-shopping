package com.app.ecommerce.shopping.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.ecommerce.shopping.model.User;

//-------------------------------------------------------

import com.app.ecommerce.shopping.repository.AllProductRepository;
import com.app.ecommerce.shopping.repository.UserRepository;

import jakarta.servlet.http.HttpSession;



@Controller
public class UserController {


    
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AllProductRepository allProductRepository;

    @GetMapping("/login")
    public String login() {
        return "user/login";
    }
 
    @PostMapping("/login")
    public String login(@RequestParam String email, 
                        @RequestParam String password, 
                        HttpSession session, 
                        Model model) {
        
        // 1. Check credentials
        Optional<User> user = userRepository.findByEmailAndPassword(email, password);

        
        
        if (user.isPresent()) {
            // SUCCESS: Store email in session
            session.setAttribute("userEmail", user.get().getEmail());
            
            // Redirect to a URL mapped to index.html
            return "redirect:/index"; 
        } else {
            // FAILURE: Stay on login page and show error
            model.addAttribute("error", "Invalid email or password. Please try again.");
            return "user/login"; // returns src/main/resources/templates/user/login.html
        }
    }

    
    
    
    // Ensure you have a mapping for the index page
    @GetMapping("/index")
    public String showIndex(Model model) {
    	
    //   List<AllProduct> productList = allProductRepository.findAll();
    	 
    //	model.addAttribute("products", productList);
    	model.addAttribute("products", allProductRepository.findAll());
    	
    	model.addAttribute("content", "index :: index-content");
    	
    	// model.addAttribute("content", "index"); 
      // return "index"; // returns src/main/resources/templates/index.html
    	
    	return "shoping/main"; // returns src/main/resources/templates/index.html

    }

    
    
    
    
    
    
    
    
    

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "user/userRegistration";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user) {
        // In a real app, use BCryptPasswordEncoder here
        userRepository.save(user);
        return "redirect:/login?success";
    }
}
