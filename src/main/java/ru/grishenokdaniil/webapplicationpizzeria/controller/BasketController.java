package ru.grishenokdaniil.webapplicationpizzeria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BasketController {
    @GetMapping("/basket")
    public String basketController() {
        return "basket";
    }
}
