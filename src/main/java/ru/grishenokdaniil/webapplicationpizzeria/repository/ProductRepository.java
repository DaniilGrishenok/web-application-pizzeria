package ru.grishenokdaniil.webapplicationpizzeria.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;
import ru.grishenokdaniil.webapplicationpizzeria.model.ProductEntity;
import ru.grishenokdaniil.webapplicationpizzeria.service.ProductType;

import java.util.List;

public interface ProductRepository extends CrudRepository<ProductEntity, Long> {

}
