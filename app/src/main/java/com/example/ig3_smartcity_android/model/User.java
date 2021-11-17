package com.example.ig3_smartcity_android.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {

    @PrimaryKey(autoGenerate = true)
    private Integer id;

    @ColumnInfo(name = "firstname")
    private String firstname;

    @ColumnInfo(name = "lastname")
    private String lastname;

    @ColumnInfo(name = "phone_number")
    private String phone_number;

    @ColumnInfo(name = "username")
    private String username;

    @ColumnInfo(name = "password")
    private String password;

    @ColumnInfo(name = "province")
    private String province;

    @ColumnInfo(name = "city")
    private String city;

    @ColumnInfo(name = "street_number")
    private String street_and_number;

    public User(){}

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
