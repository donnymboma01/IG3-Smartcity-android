package com.example.ig3_smartcity_android.model;

import java.util.Date;

public class Meal {


    private Integer id;
    private String name;
    private String description;
    private Float price;
    private Date publication_date;
    private Boolean isAvailable;
    private User user;
    private Category category;

    public Meal(Integer id,String name,String description,Float price,Date publication_date,Boolean isAvailable,User user, Category category){
        this.description = description;
        this.id = id;
        this.isAvailable = isAvailable;
        this.name = name;
        this.price = price;
        this.publication_date = publication_date;
        this.user = user;
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Date getPublication_date() {
        return publication_date;
    }

    public void setPublication_date(Date publication_date) {
        this.publication_date = publication_date;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory(){
        return this.category;
    }
    public void setCategory(Category category){
        this.category = category;
    }
}
