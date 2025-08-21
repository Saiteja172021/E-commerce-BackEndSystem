package com.ecommerce.estore.controller;
import com.ecommerce.estore.model.CartItem;
import com.ecommerce.estore.repository.ProductRepository;
import com.ecommerce.estore.model.Product;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserCartController {

    @Autowired
    private ProductRepository productRepo;

    // Display all products for user
    @GetMapping("/products")
    public String userProducts(Model model) {
        List<Product> products = productRepo.findAll();
        model.addAttribute("products", products);
        return "product"; // user_products.html
    }

    // Add to cart (session-based)
    @PostMapping("/cart/add/{id}")
    public String addToCart(@PathVariable Long id, HttpSession session) {
        Product product = productRepo.findById(id).orElse(null);
        if (product == null) return "redirect:/user/products";

        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }

        // Check if product already in cart
        boolean found = false;
        for (CartItem item : cart) {
            if (item.getProductId().equals(id)) {
                item.setQuantity(item.getQuantity() + 1);
                found = true;
                break;
            }
        }
        if (!found) {
            cart.add(new CartItem(id, product.getName(), product.getPrice(), 1));
        }

        session.setAttribute("cart", cart);
        return "redirect:/products";
    }

    // View cart
    @GetMapping("/cart")
    public String viewCart(HttpSession session, Model model) {
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart == null) cart = new ArrayList<>();
        model.addAttribute("cart", cart);
        return "cart";
    }

    // Logout and clear cart
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
    //Order pass
     @GetMapping("/order")
    public String order() {
        return "order";
    }
}
