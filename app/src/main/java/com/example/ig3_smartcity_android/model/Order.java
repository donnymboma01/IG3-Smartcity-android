package com.example.ig3_smartcity_android.model;

import java.util.Date;

public class Order {

    private Integer order_id;
    private Date order_date;
    private User user;
    private Meal meal;

    public Order(Integer order_id,Date order_date,User user,Meal meal){
        this.meal = meal;
        this.order_date = order_date;
        this.order_id = order_id;
        this.user = user;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }
}
