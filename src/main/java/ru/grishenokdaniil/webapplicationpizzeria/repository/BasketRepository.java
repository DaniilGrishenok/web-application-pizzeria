package ru.grishenokdaniil.webapplicationpizzeria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.grishenokdaniil.webapplicationpizzeria.model.entitys.Basket;
import ru.grishenokdaniil.webapplicationpizzeria.model.entitys.Product;

import java.util.List;
import java.util.Optional;

public interface BasketRepository extends JpaRepository<Basket, Long> {
    Optional<Basket> findByUserId(Long userId);
    List<Basket> findByBasketItemsProduct(Product product);

}
