package ru.grishenokdaniil.webapplicationpizzeria.repository;

import org.springframework.data.repository.CrudRepository;
import ru.grishenokdaniil.webapplicationpizzeria.model.AdminEntity;


public interface AdminRepository extends CrudRepository<AdminEntity, Long> {
}
