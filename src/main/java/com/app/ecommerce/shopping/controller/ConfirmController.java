package com.app.ecommerce.shopping.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;


//--------------------------
import com.app.ecommerce.shopping.repository.ConfirmSubmitRepository;
import com.app.ecommerce.shopping.model.ConfirmOrder;
import com.app.ecommerce.shopping.service.InvoiceService; 
//--------------------------

@Controller
public class ConfirmController {

    @Autowired
    private ConfirmSubmitRepository confirmRepository; 
    
    @Autowired
    private InvoiceService invoiceService;

    @PostMapping("/checkout")
    public String processCheckout(HttpServletRequest request, HttpSession session) {
        
        String email = (String) session.getAttribute("userEmail");
        if (email == null) return "user/login";
        
        

        // Get the grand total once
        Double grandTotal = Double.parseDouble(request.getParameter("grandTotal"));

        // Loop through the parameters based on the index from the HTML
        
        
        int index = 0;
        while (request.getParameter("items[" + index + "].name") != null) {
            
            String name = request.getParameter("items[" + index + "].name");
            Double price = Double.parseDouble(request.getParameter("items[" + index + "].price"));
            Integer qty = Integer.parseInt(request.getParameter("items[" + index + "].quantity"));

            // Create and Save Entity
            ConfirmOrder order = new ConfirmOrder();
            order.setUserEmail(email);
            order.setItemName(name);
            order.setPrice(price);
            order.setQuantity(qty);
            order.setGrandTotal(grandTotal);

            confirmRepository.save(order);
            
            index++; // Move to the next item in the list
        }

        // Clear the cart from session after saving to DB
        session.removeAttribute("cart");

        //return "redirect:shoping/payment";
        return "shoping/payment";
    }
    
    
    @GetMapping("/downloadInvoice")
    public void downloadInvoice(HttpSession session, HttpServletResponse response) throws Exception {
        String email = (String) session.getAttribute("userEmail");
        if (email == null) return;

        // Fetch latest orders for this user from DB
        List<ConfirmOrder> orders = confirmRepository.findByUserEmail(email);

        response.setContentType("application/pdf");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=invoice_" + email + ".pdf";
        response.setHeader(headerKey, headerValue);

        invoiceService.generate(orders, response);
    }
    
    
    
}
