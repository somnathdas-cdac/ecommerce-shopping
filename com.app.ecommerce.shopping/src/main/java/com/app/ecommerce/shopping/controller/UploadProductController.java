package com.app.ecommerce.shopping.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//-----------------------------------------------------------

import com.app.ecommerce.shopping.model.UploadProduct;
import com.app.ecommerce.shopping.repository.AllProductRepository;

@Controller
public class UploadProductController {

    @Autowired
    private AllProductRepository allProductRepository;

    // 1. Show the input form
    @GetMapping("/add-product")
    public String showForm(Model model) {
        model.addAttribute("product", new UploadProduct());
        //return "product-form";
        return "upload_product";
    }

    // 2. Handle the submit button click
    @PostMapping("/save-product")
    public String saveProduct(@ModelAttribute UploadProduct product) {
    	allProductRepository.save(product);
       // return "redirect:/add-product?success"; // Refresh page with success message
    	 return "redirect:/add-product?success"; // Refresh page with success message
    }
}
