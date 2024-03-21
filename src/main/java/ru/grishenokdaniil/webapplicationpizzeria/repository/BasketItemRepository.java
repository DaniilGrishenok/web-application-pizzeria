package ru.grishenokdaniil.webapplicationpizzeria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.grishenokdaniil.webapplicationpizzeria.model.entitys.BasketItem;

public interface BasketItemRepository extends JpaRepository<BasketItem, Long> {
}
