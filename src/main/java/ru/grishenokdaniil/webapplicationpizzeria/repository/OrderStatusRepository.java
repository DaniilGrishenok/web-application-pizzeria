package ru.grishenokdaniil.webapplicationpizzeria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.grishenokdaniil.webapplicationpizzeria.model.enams.OrderStatus;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long> {


}
