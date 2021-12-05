package com.example.ig3_smartcity_android.repositories.webservice

import com.example.ig3_smartcity_android.repositories.dto.LoginUserDTO
import com.example.ig3_smartcity_android.repositories.dto.MealDTO
import com.example.ig3_smartcity_android.repositories.dto.TokenDTO
import retrofit2.Call
import retrofit2.http.*

interface ApiWebServices {

    //permet de se connecter
    @POST("v1/user/login")
    fun userLogin(@Body loginUserDTO: LoginUserDTO) : Call<String>


    //permet d'inscrire un utilisateur.
    @POST("/v1/user")
    fun registerUser(@Body userDTO: LoginUserDTO) : Call<TokenDTO>

    //récupère un repas par son id
    @GET("/v1/meal/{id}")
    fun getMealById(@Path("id") mealId :Int ,@Header("Authorization") authHeader: String) :Call<MealDTO>


    //récupère tous les repas.
    @GET("/v1/meal")
    fun getAllMeals() : Call<MealDTO>
}