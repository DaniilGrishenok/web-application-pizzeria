package ru.grishenokdaniil.webapplicationpizzeria.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.grishenokdaniil.webapplicationpizzeria.model.entitys.Basket;
import ru.grishenokdaniil.webapplicationpizzeria.model.entitys.Order;
import ru.grishenokdaniil.webapplicationpizzeria.model.entitys.Product;
import ru.grishenokdaniil.webapplicationpizzeria.model.entitys.User;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {


    List<Order> findAll();
    List<Order> findByUser(User user);


    Optional<Order> findById(Long id);
}
