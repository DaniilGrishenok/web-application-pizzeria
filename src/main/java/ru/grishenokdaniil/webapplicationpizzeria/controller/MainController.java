package ru.grishenokdaniil.webapplicationpizzeria.controller;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.grishenokdaniil.webapplicationpizzeria.model.entitys.Basket;
import ru.grishenokdaniil.webapplicationpizzeria.model.entitys.Product;
import ru.grishenokdaniil.webapplicationpizzeria.model.entitys.User;
import ru.grishenokdaniil.webapplicationpizzeria.repository.BasketRepository;
import ru.grishenokdaniil.webapplicationpizzeria.repository.ProductRepository;
import ru.grishenokdaniil.webapplicationpizzeria.repository.UserRepository;
import ru.grishenokdaniil.webapplicationpizzeria.service.ProductService;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Log4j2
public class MainController {

    private final ProductService productService;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final BasketRepository basketRepository;

    @GetMapping("/")
    public String home(Model model) {
        List<Product> products = (List<Product>) productService.getAllProducts();
        model.addAttribute("products", products);
        return "index";
    }
    @PostMapping("/basket/add/{productID}")
    public ResponseEntity<String> addToCart(@PathVariable Long productID, Principal principal) {
        try {

            String username = principal.getName();
            User user = userRepository.findByEmail(username);

            Product product = productRepository.findById(productID).orElse(null);

            if (product == null) {
                return ResponseEntity.badRequest().body("Invalid product ID");
            }

            Basket basket = basketRepository.findByUserId(user.getId());
            if (basket == null) {
                basket = new Basket();
                basket.setUser(user);
            }

            basket.addItem(product);
            basketRepository.save(basket);

            return ResponseEntity.ok("Product added to cart successfully");
        } catch (Exception e) {
            log.error("Error adding product to cart: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding product to cart");
        }
    }




}
