package online_store.controller.mvc;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import online_store.component.ShoppingCart;
import online_store.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/cart")
@SessionAttributes("shoppingCart")
@AllArgsConstructor
@Slf4j
public class ShoppingCartController {

    private final ProductService productService;

    @GetMapping("/list")
    public String showCart() {
        return "cart/list";
    }

    @GetMapping("/add-to-cart")
    public RedirectView addToCart(@RequestParam Long id, @ModelAttribute ShoppingCart cart) {
        productService.findById(id).ifPresent(cart::addProduct);
        log.info("add to cart");
        return new RedirectView("/product");
    }

    @GetMapping("/remove-from-cart")
    public RedirectView removeFromCart(@RequestParam Long id, @ModelAttribute ShoppingCart cart) {
        productService.findById(id).ifPresent(cart::removeProduct);
        return new RedirectView("/cart/list");
    }

}
