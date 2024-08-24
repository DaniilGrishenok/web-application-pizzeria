package ru.grishenokdaniil.webapplicationpizzeria.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.grishenokdaniil.webapplicationpizzeria.model.enams.OrderStatus;
import ru.grishenokdaniil.webapplicationpizzeria.model.entitys.Basket;
import ru.grishenokdaniil.webapplicationpizzeria.model.entitys.Order;
import ru.grishenokdaniil.webapplicationpizzeria.model.entitys.Product;
import ru.grishenokdaniil.webapplicationpizzeria.model.entitys.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {


    List<Order> findAll();
    List<Order> findByUser(User user);


    Optional<Order> findById(Long id);


    int countByStatusNotIn(List<OrderStatus> excludedStatuses);

    @Query("SELECT COUNT(o) FROM orders o WHERE o.dateOfCreate BETWEEN :startOfDay AND :endOfDay")
    int countOrdersInThisDay(LocalDateTime startOfDay, LocalDateTime endOfDay);

    List<Order> findOrderByStatusNotIn(List<OrderStatus> excludedStatuses);

    List<Order> findOrdersByEmail(String searchInput);

    List<Order> findOrdersById(String searchInput);

}
