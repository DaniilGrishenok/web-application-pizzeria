package ru.grishenokdaniil.webapplicationpizzeria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.grishenokdaniil.webapplicationpizzeria.model.entitys.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
