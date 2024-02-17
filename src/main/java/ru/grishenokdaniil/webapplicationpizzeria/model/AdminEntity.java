package ru.grishenokdaniil.webapplicationpizzeria.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "admins")
public class AdminEntity {

    @Id
    private long adminID;

    private String login;
    private String password;

    public AdminEntity() {

    }

    public AdminEntity(long adminID, String login, String password) {
        this.adminID = adminID;
        this.login = login;
        this.password = password;
    }

}
