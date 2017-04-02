package com.example.julia.wirtec_iticket;

/**
 * Created by shinichi on 4/1/17.
 */

public class Account {
    private String name, email;

    public Account(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Account() {
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
}
