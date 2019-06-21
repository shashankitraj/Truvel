package com.example.aakarsh.truvel.model;

public class UserInfo {

    public String name,email,phone;

    public UserInfo() {
    }

    public UserInfo(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
}
