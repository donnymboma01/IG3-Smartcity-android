package com.example.ig3_smartcity_android.repositories.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.squareup.moshi.Json;

public class JwtTokenPayloadDTO {

    @Json(name="status")
    @JsonProperty("status")
    private String status;

    @Json(name="id")
    @JsonProperty("id")
    private Integer id;

    @Json(name="firstname")
    @JsonProperty("firstname")
    private String firstname;

    @Json(name="lastname")
    @JsonProperty("lastname")
    private String lastname;

    public JwtTokenPayloadDTO(String status, Integer id, String firstname, String lastname){
        super();
        this.status = status;
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public JwtTokenPayloadDTO(){

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
