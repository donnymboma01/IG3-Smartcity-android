package com.example.ig3_smartcity_android.repositories.webservice;

import com.example.ig3_smartcity_android.repositories.dto.MealDTO;
import com.example.ig3_smartcity_android.repositories.dto.UserDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface WebServives {

    @GET("v1/meal")
    Call<List<MealDTO>> getMeals();

    @GET("v1/meal/{id}")
    Call<MealDTO> getMealById(@Path("id") int id, @Body MealDTO mealDTO);

    @GET("v1/user")
    Call<List<UserDTO>> getUsers();

    @POST("v1/user/login")
    Call<UserDTO> userLogin(@Body UserDTO userDTO);

    @POST("v2/meal")
    Call<MealDTO> addMeal(@Body MealDTO mealDTO);

}
