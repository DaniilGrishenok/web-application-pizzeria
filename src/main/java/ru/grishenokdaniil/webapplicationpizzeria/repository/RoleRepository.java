package ru.grishenokdaniil.webapplicationpizzeria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.grishenokdaniil.webapplicationpizzeria.model.entitys.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {


}
