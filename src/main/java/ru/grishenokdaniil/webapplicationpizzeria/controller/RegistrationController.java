package ru.grishenokdaniil.webapplicationpizzeria.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.grishenokdaniil.webapplicationpizzeria.model.entitys.User;
import ru.grishenokdaniil.webapplicationpizzeria.repository.UserRepository;
import ru.grishenokdaniil.webapplicationpizzeria.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@Controller
@RequiredArgsConstructor
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    private final UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }



    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/";
    }
    @GetMapping("/debug")
    public String debug(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken)) {
            // Если пользователь не анонимный, добавляем информацию о нем в модель
            model.addAttribute("username", authentication.getName());
            model.addAttribute("authorities", authentication.getAuthorities());
            model.addAttribute("authenticated", true);
        } else {
            // Если пользователь анонимный или не аутентифицирован, добавляем информацию об этом в модель
            model.addAttribute("authenticated", false);
        }
        return "debug";
    }

    @PostMapping("/registration")
    public String createUser(User user, Model model) {
        if(!userService.createUser(user)){
            model.addAttribute("error message", "Пользователь с email: "+
                    user.getEmail() + " уже существует! ");
            return "registration";
        }

        return "redirect:/login";
    }
    @PostMapping("/registrationAdmin")
    public String createAdmin(User user, Model model) {
        if(!userService.createAdmin(user)){
            model.addAttribute("error message", "Пользователь с email: "+
                    user.getEmail() + " уже существует! ");
            
            return "registrationAdmin";
        }

        return "redirect:/admin";
    }
    @GetMapping("/registrationAdmin")
    public String registrationAdmin() {
        return "registrationAdmin";
    }
}
