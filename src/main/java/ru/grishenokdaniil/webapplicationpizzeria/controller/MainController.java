package ru.grishenokdaniil.webapplicationpizzeria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.grishenokdaniil.webapplicationpizzeria.model.ProductEntity;
import ru.grishenokdaniil.webapplicationpizzeria.service.ProductService;

import java.util.List;

@Controller
public class MainController {

    private final ProductService productService;

    @Autowired

    public MainController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<ProductEntity> products = (List<ProductEntity>) productService.getAllProducts();
        model.addAttribute("products", products);
        return "index";
    }


}
