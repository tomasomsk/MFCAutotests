package com.luxoft.mfcautotests.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

@Component
public class User {

    private String login;
    private String password;
    private ArrayList<Role> roles = new ArrayList<>();
    private String Arm;

    public User setLogin(String login) {
        this.login = login;
        return this;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public User setRoles(Role role) {
        roles.add(role);
        return this;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Role> getRoles() {
        Comparator<Role> comparator = Comparator.comparing(Role::toString);
        roles.sort(comparator);
        return roles;
    }
}
