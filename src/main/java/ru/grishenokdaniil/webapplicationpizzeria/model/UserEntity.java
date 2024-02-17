package ru.grishenokdaniil.webapplicationpizzeria.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "usersDataTable")
public class UserEntity {

    @Id
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public UserEntity() {

    }

    public UserEntity(String phoneNumber, String firstName, String lastName, String email, String password) {
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

}