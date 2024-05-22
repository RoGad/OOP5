package com.example.oop5;

public class Users {
    private String name;
    private String lastname;
    private int age;
    private String email;
    private String telegram;
    private String mobile;

    public Users(String name, String lastname, int age, String email, String telegram, String mobile) {
        this.name = name;
        this.lastname = lastname;
        this.age = age;
        this.email = email;
        this.telegram = telegram;
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }
    public String getLastname() {
        return lastname;
    }
    public int getAge() {
        return age;
    }
    public String getEmail() {
        return email;
    }
    public String getTelegram() {
        return telegram;
    }
    public String getMobile() {
        return mobile;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setTelegram(String telegram) {
        this.telegram = telegram;
    }
    public void setMobile(String mobile) {
        this.telegram = telegram;
    }

    @Override
    public String toString() {
        return "Student [name=" + name + ", lastname=" + lastname + ", age=" + age + ", email=" + email + ", telegram=" + telegram + ", mobile=" + mobile + "]";
    }


}
