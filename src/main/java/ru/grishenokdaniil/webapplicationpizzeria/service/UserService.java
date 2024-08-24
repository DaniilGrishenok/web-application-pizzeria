package ru.grishenokdaniil.webapplicationpizzeria.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.grishenokdaniil.webapplicationpizzeria.model.enams.Role;
import ru.grishenokdaniil.webapplicationpizzeria.model.entitys.User;
import ru.grishenokdaniil.webapplicationpizzeria.repository.UserRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Transactional
    public boolean createUser(User user){
        String email = user.getEmail();
        if(userRepository.findByEmail(user.getEmail())!= null ){
            return false;
        }
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(Role.ROLE_USER);
        userRepository.save(user);
        log.info("Saving new User with email: {}", email);

        return true;
    }
    @Transactional
    public boolean createAdmin(User user){
        String email = user.getEmail();
        if(userRepository.findByEmail(user.getEmail())!= null ){
            return false;
        }
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(Role.ROLE_ADMIN);
        userRepository.save(user);
        log.info("Saving new Admin with email: {}", email);

        return true;
    }


}
