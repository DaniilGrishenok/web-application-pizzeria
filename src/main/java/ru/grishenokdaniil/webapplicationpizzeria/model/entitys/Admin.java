package ru.grishenokdaniil.webapplicationpizzeria.model.entitys;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "admins")
public class Admin {

    @Id
    private long adminID;

    private String login;
    private String password;

    public Admin() {

    }

    public Admin(long adminID, String login, String password) {
        this.adminID = adminID;
        this.login = login;
        this.password = password;
    }

}
