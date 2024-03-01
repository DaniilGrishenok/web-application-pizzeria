package ru.grishenokdaniil.webapplicationpizzeria.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

//    @PostMapping("/login")
////    public String loginPost(@RequestParam String username,
//                            @RequestParam String password,
//                            Model model, HttpServletRequest request) {
//        try {
//            request.login(username, password);
//            model.addAttribute("authenticated", true);
//            return "redirect:/userAccount"; // Или другой URL для страницы аккаунта
//        } catch (ServletException e) {
//            model.addAttribute("error", "Invalid username or password");
//            return "login";
//        }
//    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @GetMapping("/userAccount")
    public String Account(){
        return "userAccount";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/login?logout"; // You can customize the redirect URL after logout
    }
    @GetMapping("/yourEndpoint")
    public String yourEndpoint(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Вывод отладочной информации в консоль
        System.out.println("Principal: " + authentication.getPrincipal());
        System.out.println("Authorities: " + authentication.getAuthorities());
        System.out.println("Is authenticated: " + authentication.isAuthenticated());

        // Передача информации в модель для отображения в представлении (если необходимо)
        model.addAttribute("principal", authentication.getPrincipal());
        model.addAttribute("authorities", authentication.getAuthorities());
        model.addAttribute("authenticated", authentication.isAuthenticated());

        return "yourView"; // Замените на имя вашего представления
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

}
