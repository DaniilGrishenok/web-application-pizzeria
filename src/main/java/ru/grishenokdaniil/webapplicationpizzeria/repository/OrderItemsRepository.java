package ru.grishenokdaniil.webapplicationpizzeria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.grishenokdaniil.webapplicationpizzeria.model.entitys.OrderItem;

public interface OrderItemsRepository extends JpaRepository<OrderItem, Long> {
}
