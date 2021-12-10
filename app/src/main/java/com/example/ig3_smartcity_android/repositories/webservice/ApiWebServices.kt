package com.example.ig3_smartcity_android.repositories.webservice

import com.example.ig3_smartcity_android.repositories.dto.LoginUserDTO
import com.example.ig3_smartcity_android.repositories.dto.MealDTO
import com.example.ig3_smartcity_android.repositories.dto.TokenDTO
import com.example.ig3_smartcity_android.repositories.dto.UserDTO
import retrofit2.Call
import retrofit2.http.*

interface ApiWebServices {

    //permet de se connecter -->OK
    @POST("V1/user/login")
    fun userLogin(@Body loginUserDTO: LoginUserDTO) : Call<String>


    //permet d'inscrire un utilisateur. -->Token String
    // il fallait userDTO soit de type UserDTO et non LoginUserDTO-->fixed.
    @POST("V1/user")
    fun registerUser(@Body userDTO: UserDTO) : Call<String>

    //récupère un repas par son id --> pas vraiment besoin pour ce projet
    @GET("/v1/meal/{id}")
    fun getMealById(@Path("id") mealId :Int ) :Call<MealDTO>

    //@GET("/v1/meal/{id}")
    //fun getMealById(@Path("id") mealId :Int ,@Header("Authorization") authHeader: String) :Call<MealDTO>

    //récupère tous les repas. -->OK
    @GET("V1/meal")
    fun getAllMeals(@Header("Authorization") authHeader: String) : Call<List<MealDTO>>
}