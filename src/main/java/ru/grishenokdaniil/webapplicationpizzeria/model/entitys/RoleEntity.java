package ru.grishenokdaniil.webapplicationpizzeria.model.entitys;

import javax.persistence.*;

@Entity(name ="roles")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JoinColumn(name = "id")
    private Long user_id;
    private String roleName;


}
