package com.app.ecommerce.shopping.service;

import com.app.ecommerce.shopping.model.ConfirmOrder;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InvoiceService {
    public void generate(List<ConfirmOrder> orders, HttpServletResponse response) throws Exception {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(18);

        Paragraph paragraph = new Paragraph("INVOICE", fontTitle);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(paragraph);

        if(!orders.isEmpty()) {
            document.add(new Paragraph("Customer: " + orders.get(0).getUserEmail()));
            document.add(new Paragraph(" ")); // Spacer
        }

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.addCell("Item Name");
        table.addCell("Price");
        table.addCell("Qty");
        table.addCell("Subtotal");

        for (ConfirmOrder order : orders) {
            table.addCell(order.getItemName());
            table.addCell("$" + order.getPrice());
            table.addCell(String.valueOf(order.getQuantity()));
            table.addCell("$" + (order.getPrice() * order.getQuantity()));
        }
        document.add(table);
        
        if(!orders.isEmpty()) {
            document.add(new Paragraph("Grand Total: $" + orders.get(0).getGrandTotal()));
        }

        document.close();
    }
}
