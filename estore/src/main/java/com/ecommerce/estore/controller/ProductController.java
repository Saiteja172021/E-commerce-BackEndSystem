package com.ecommerce.estore.controller;

import com.ecommerce.estore.model.Product;
import com.ecommerce.estore.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    // USER view (read-only)
    @GetMapping("/products")
    @PreAuthorize("hasRole('USER')")
    public String showUserProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "products"; // users.html view
    }

    // ADMIN dashboard view
    @GetMapping("/admin/dashboard")
    @PreAuthorize("hasRole('ADMIN')")
    public String showAdminDashboard(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "admin_dashboard";
    }

    // ADD product form (admin only)
    @GetMapping("/products/new")
    @PreAuthorize("hasRole('ADMIN')")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "product_form";
    }

    // SAVE product (admin only)
    @PostMapping("/products/save")
    @PreAuthorize("hasRole('ADMIN')")
    public String saveProduct(@Valid @ModelAttribute("product") Product product,
                               BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("product", product);
            return "product_form";
        }
        productService.saveProduct(product);
        return "redirect:/admin/dashboard";
    }

    // EDIT product form (admin only)
    @GetMapping("/products/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String editProduct(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        model.addAttribute("product", product);
        return "product_form";
    }

    // DELETE product (admin only)
    @GetMapping("/products/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/admin/dashboard";
    }
}
