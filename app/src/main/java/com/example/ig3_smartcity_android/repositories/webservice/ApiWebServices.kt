package com.example.ig3_smartcity_android.repositories.webservice

import com.example.ig3_smartcity_android.repositories.dto.LoginUserDTO
import com.example.ig3_smartcity_android.repositories.dto.MealToReceiveDTO
import com.example.ig3_smartcity_android.repositories.dto.UserDTO
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface ApiWebServices {

    //permet de se connecter -->OK
    @POST("V1/user/login")
    fun userLogin(@Body loginUserDTO: LoginUserDTO) : Call<String>


    //permet d'inscrire un utilisateur. -->Token String ->OK
    @POST("V1/user")
    fun registerUser(@Body userDTO: UserDTO) : Call<String>

    //récupère un repas par son id --> pas vraiment besoin pour ce projet
    @GET("/v1/meal/{id}")
    fun getMealById(@Path("id") mealId :Int ) :Call<MealToReceiveDTO>

    //récupère tous les repas. -->OK
    @GET("V1/meal")
    fun getAllMeals(@Header("Authorization") authHeader: String) : Call<List<MealToReceiveDTO>>

    //Ajout d'un repas.
    @Multipart
    @POST("V1/meal")
    fun addMeal(@Part namePart:MultipartBody.Part, @Part descriptionPart:MultipartBody.Part,@Part portionNumberPart:MultipartBody.Part,
                @Part userFkPart:MultipartBody.Part,@Part categoryFkPart:MultipartBody.Part, @Part image: MultipartBody.Part, @Header("Authorization") authHeader: String):Call<String>
}