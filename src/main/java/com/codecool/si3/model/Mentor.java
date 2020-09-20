package com.codecool.si3.model;

public class Mentor {
    private int id;
    private String first_name;
    private String last_name;
    private String nick_name;
    private String phone_number;
    private String email;

    public int getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getNick_name() {
        return nick_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getEmail() {
        return email;
    }

    public String getCity() {
        return city;
    }

    public int getFavourite_number() {
        return favourite_number;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setFavourite_number(int favourite_number) {
        this.favourite_number = favourite_number;
    }

    private String city;
    private int favourite_number;

}
