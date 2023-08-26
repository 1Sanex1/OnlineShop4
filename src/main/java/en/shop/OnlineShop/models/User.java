package en.shop.OnlineShop.models;

import en.shop.OnlineShop.util.UserStatus;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


@Entity
@Table(name = "person")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "surname")
    @NotEmpty(message = "Surname shouldn't be empty")
    @Size(min = 2, max = 50, message = "Surname should be from 2 to 50 chars")
    private String surname;

    @Column(name = "name")
    @NotEmpty(message = "Name shouldn't be empty")
    @Size(min = 2, max = 50, message = "Name should be from 2 to 50 chars")
    private String name;

    @Column(name = "email")
    @Email
    @NotEmpty(message = "Email shouldn't be empty")
    @Size(min = 2, max = 50, message = "Email should be from 2 to 50 chars")
    private String email;

    @Column(name = "login")
    @NotEmpty(message = "Login shouldn't be empty")
    @Size(min = 2, max = 50, message = "Login should be from 2 to 50 chars")
    private String login;

    @Column(name = "password")
    @NotEmpty(message = "Password shouldn't be empty")
    private String password;

    @Column(name = "role")
    private String role;

    public User() {
    }

    public User(String surname, String name, String email, String login) {
        this.surname = surname;
        this.name = name;
        this.email = email;
        this.login = login;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


}
