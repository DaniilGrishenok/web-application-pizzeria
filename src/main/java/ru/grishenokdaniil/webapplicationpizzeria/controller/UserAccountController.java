package ru.grishenokdaniil.webapplicationpizzeria.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.grishenokdaniil.webapplicationpizzeria.model.entitys.Order;
import ru.grishenokdaniil.webapplicationpizzeria.model.entitys.OrderItem;
import ru.grishenokdaniil.webapplicationpizzeria.model.entitys.User;
import ru.grishenokdaniil.webapplicationpizzeria.repository.OrderRepository;
import ru.grishenokdaniil.webapplicationpizzeria.repository.UserRepository;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class UserAccountController {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    @GetMapping("/myOrders")
    public String getMyOrders(Model model, Principal principal) {
        // Получаем пользователя по email
        User user = userRepository.findByEmail(principal.getName());

        List<Order> userOrders = orderRepository.findByUser(user);

        double totalPrice = userOrders.stream()
                .mapToDouble(Order::getTotalPrice)
                .sum();

        model.addAttribute("orders", userOrders);
        model.addAttribute("totalPrice", totalPrice);
        return "userOrdersPage";
    }


    @GetMapping("/userAccount")
    public String Account(Model model, Principal principal){
        User user = userRepository.findByEmail(principal.getName());
        model.addAttribute("user", user);

        return "userAccount";
    }
    @PostMapping("/userAccount")
    public String UpdateUserInfo(){
        return "redirect:userAccount";
    }

}
