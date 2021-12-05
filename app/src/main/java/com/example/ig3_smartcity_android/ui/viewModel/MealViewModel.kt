package com.example.ig3_smartcity_android.ui.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.ig3_smartcity_android.model.Meal
import com.example.ig3_smartcity_android.model.NetworkError
import com.example.ig3_smartcity_android.repositories.configuration.RetrofitConfigurationService
import com.example.ig3_smartcity_android.repositories.dto.MealDTO
import com.example.ig3_smartcity_android.services.mappers.MealMapper
import com.example.ig3_smartcity_android.utils.errors.NoConnectivityException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealViewModel(application: Application) :AndroidViewModel(application) {
    private val _meal = MutableLiveData<Meal>()
    val meal : LiveData<Meal> = _meal

    private val _error  = MutableLiveData<NetworkError?>()
    val error : LiveData<NetworkError?> = _error

    private var mealWebServices = RetrofitConfigurationService.getInstance(application).apiWebServices()
    private var mealMapper = MealMapper

    fun getAllMeals(){
        mealWebServices.getAllMeals().enqueue(object : Callback<MealDTO> {
            override fun onResponse(call:Call<MealDTO?>, response: Response<MealDTO?>){
                if(response.isSuccessful){
                    _meal.value = mealMapper.mapToMeal(response.body()!!)
                    _error.value = NetworkError.NO_ERROR_DETECTED
                }else{
                    _error.value = NetworkError.REQUEST_ERROR
                }
            }

            override fun onFailure(call: Call<MealDTO>, t: Throwable) {
                if(t is NoConnectivityException){
                    _error.value = NetworkError.NO_CONNECTION_ERROR
                }else{
                    System.out.println(t)
                    _error.value = NetworkError.TECHNICAL_ERROR
                }
            }
        })
    }
}