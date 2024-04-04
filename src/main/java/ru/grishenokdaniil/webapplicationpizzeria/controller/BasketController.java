package ru.grishenokdaniil.webapplicationpizzeria.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.grishenokdaniil.webapplicationpizzeria.model.entitys.Basket;
import ru.grishenokdaniil.webapplicationpizzeria.model.entitys.BasketItem;
import ru.grishenokdaniil.webapplicationpizzeria.model.entitys.Product;
import ru.grishenokdaniil.webapplicationpizzeria.model.entitys.User;
import ru.grishenokdaniil.webapplicationpizzeria.repository.BasketRepository;
import ru.grishenokdaniil.webapplicationpizzeria.repository.UserRepository;
import ru.grishenokdaniil.webapplicationpizzeria.service.ProductService;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BasketController {
    private final UserRepository userRepository;
    private final BasketRepository basketRepository;
    private final ProductService productService;

    @GetMapping("/basket")
    public String showBasket(Model model, Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByEmail(username);

        if (user != null) {
            Basket basket = basketRepository.findByUserId(user.getId());

            if (basket != null) {
                List<BasketItem> basketItems = basket.getBasketItems();
                model.addAttribute("basketItems", basketItems);
                double totalAmount = productService.calculateTotalAmount(basketItems);
                model.addAttribute("totalAmount", totalAmount);
            } else {
                model.addAttribute("basketItems", Collections.emptyList());
                model.addAttribute("totalAmount", 0.0);
            }
        }

        return "basket";
    }


    @PostMapping("/basket/remove/{productId}")
    public String deleteProductByBasket(@PathVariable("productId") Long productId, Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByEmail(username);

        if (user != null) {
            Basket basket = basketRepository.findByUserId(user.getId());

            if (basket != null) {
                basket.removeProductById(productId);
                basketRepository.save(basket);
            }
        }

        return "redirect:/basket";
    }

}
