package ru.grishenokdaniil.webapplicationpizzeria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.grishenokdaniil.webapplicationpizzeria.model.entitys.Basket;
import ru.grishenokdaniil.webapplicationpizzeria.model.entitys.Product;

import java.util.List;

public interface BasketRepository extends JpaRepository<Basket, Long> {
    Basket findByUserId(Long userId);
    List<Basket> findByBasketItemsProduct(Product product);

}
