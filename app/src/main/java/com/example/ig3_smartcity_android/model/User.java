package com.example.ig3_smartcity_android.model;

public class User {

    private Integer id;
    private String firstname;
    private String lastname;
    private String phone_number;
    private String username;
    private String password;
    private String province;
    private String city;
    private String street_and_number;


    public User(Integer id,String firstname,String lastname,String phone_number,String username,String password,String province,String city,String street_and_number){
        this.city = city;
        this.firstname = firstname;
        this.lastname = lastname;
        this.id = id;
        this.password = password;
        this.phone_number = phone_number;
        this.province = province;
        this.username = username;
        this.street_and_number = street_and_number;
    }
    public Integer getId(){
        return this.id;
    }

    public String getFirstname(){
        return this.firstname;
    }

    public String getLastname(){
        return this.lastname;
    }
    public String getPhone_number(){
        return this.phone_number;
    }
    public String getUsername(){
        return this.username;
    }
    public String getPassword(){
        return this.password;
    }
    public String getProvince(){
        return this.province;
    }
    public String getCity(){
        return this.city;
    }
    public String getStreet_and_number(){
        return this.street_and_number;
    }
}
