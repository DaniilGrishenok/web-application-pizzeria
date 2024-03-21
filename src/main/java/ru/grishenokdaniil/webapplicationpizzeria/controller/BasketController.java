package ru.grishenokdaniil.webapplicationpizzeria.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.grishenokdaniil.webapplicationpizzeria.model.entitys.Basket;
import ru.grishenokdaniil.webapplicationpizzeria.model.entitys.Product;
import ru.grishenokdaniil.webapplicationpizzeria.model.entitys.User;
import ru.grishenokdaniil.webapplicationpizzeria.repository.BasketRepository;
import ru.grishenokdaniil.webapplicationpizzeria.repository.UserRepository;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BasketController {
    private final UserRepository userRepository;
    private final BasketRepository basketRepository;
    @GetMapping("/basket")
    public String showBasket(Model model, Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByEmail(username);

        if (user != null) {
            Basket basket = basketRepository.findByUserId(user.getId());

            if (basket != null) {
                List<Product> basketProducts = basket.getProducts();
                model.addAttribute("products", basketProducts);
            } else {

                model.addAttribute("products", Collections.emptyList());
            }
        } else {
            // Обработка случая, когда пользователь не найден
            // Мб, нужно перенаправить пользователя на страницу регистрации или входа
        }
        return "basket";
    }

}
