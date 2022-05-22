package com.example.hibernate.controller.mvc.advice;


import com.example.hibernate.component.ShoppingCart;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class CartController {
    @ModelAttribute("shoppingCart")
    public ShoppingCart shoppingCart() {
        return new ShoppingCart();
    }
}
