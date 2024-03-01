package ru.grishenokdaniil.webapplicationpizzeria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.grishenokdaniil.webapplicationpizzeria.model.entitys.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

}