package ru.grishenokdaniil.webapplicationpizzeria.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.grishenokdaniil.webapplicationpizzeria.model.entitys.*;
import ru.grishenokdaniil.webapplicationpizzeria.repository.BasketRepository;
import ru.grishenokdaniil.webapplicationpizzeria.repository.OrderRepository;
import ru.grishenokdaniil.webapplicationpizzeria.repository.UserRepository;
import ru.grishenokdaniil.webapplicationpizzeria.service.ProductService;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final UserRepository userRepository;
    private final BasketRepository basketRepository;
    private final ProductService productService;
    private final OrderRepository orderRepository;



    @GetMapping("/order")
    public String orderPage(Model model, Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByEmail(username);

        if (user != null) {
            Optional<Basket> basketOptional = basketRepository.findByUserId(user.getId());
            if (basketOptional.isPresent()) {
                Basket basket = basketOptional.get();
                List<BasketItem> basketItems = basket.getBasketItems().stream()
                        .filter(BasketItem::isAddedToOrder)
                        .collect(Collectors.toList());
                double totalAmount = productService.calculateTotalAmount(basketItems);
                model.addAttribute("basketItems", basketItems);
                model.addAttribute("totalAmount", totalAmount);
            }
        }

        return "orderPage";
    }

    @PostMapping("/order/create")
    public String createOrder(@RequestParam("fullName") String fullName,
                              @RequestParam("phone") String phone,
                              @RequestParam("email") String email,
                              @RequestParam("street") String street,
                              @RequestParam("house") String house,
                              @RequestParam("flat") String flat,
                              @RequestParam("CommentForCourier") String commentForCourier,
                              Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByEmail(username);

        if (user != null) {
            Optional<Basket> basketOptional = basketRepository.findByUserId(user.getId());
            if (basketOptional.isPresent()) {
                Basket basket = basketOptional.get();
                List<BasketItem> basketItems = basket.getBasketItems().stream()
                        .filter(BasketItem::isAddedToOrder)
                        .toList();
                Order order = new Order();
                order.setUser(user);
                order.setName(fullName);
                order.setTotalSum(order.getTotalSum());
                order.setNumberPhone(phone);
                order.setEmail(email);
                order.setStreet(street);
                order.setHouse(house);
                order.setFlat(flat);
                order.setDescriptionForCourier(commentForCourier);


                for (BasketItem basketItem : basketItems) {
                    order.addItem(basketItem);
                }
                orderRepository.save(order);
                basketRepository.delete(basket);

                return "redirect:/order/paymentPage";
            }
        }
        return "error";
    }



    @GetMapping("/order/paymentPage")
    public String paymentPage(){
        return "paymentPage";
    }
}
