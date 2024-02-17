package ru.grishenokdaniil.webapplicationpizzeria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.grishenokdaniil.webapplicationpizzeria.model.ProductEntity;
import ru.grishenokdaniil.webapplicationpizzeria.model.UserEntity;
import ru.grishenokdaniil.webapplicationpizzeria.repository.ProductRepository;

@Controller
public class AdminController {


    @GetMapping("/admin")
    public String adminPanel() {
        return "adminPanel";
    }




}
