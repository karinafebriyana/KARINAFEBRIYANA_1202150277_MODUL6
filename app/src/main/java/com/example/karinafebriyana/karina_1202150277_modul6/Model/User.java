package com.example.karinafebriyana.karina_1202150277_modul6.Model;

/**
 * Created by Karina Febriyana on 01/04/2018.
 */

public class User {

    String userId;
    String username;
    String email;

    public User() {
    }

    public User(String userId, String username, String email) {
        this.userId = userId;
        this.username = username;
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}


