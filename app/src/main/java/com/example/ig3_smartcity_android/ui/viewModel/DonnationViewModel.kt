package com.example.ig3_smartcity_android.ui.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.ig3_smartcity_android.model.Meal
import com.example.ig3_smartcity_android.model.NetworkError
import com.example.ig3_smartcity_android.repositories.configuration.RetrofitConfigurationService
import com.example.ig3_smartcity_android.services.mappers.MealMapper
import com.example.ig3_smartcity_android.utils.errors.NoConnectivityException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DonnationViewModel(application: Application) :AndroidViewModel(application) {

    private val _error : MutableLiveData<NetworkError> = MutableLiveData()
    val error :LiveData<NetworkError> = _error

    private var apiWebServices = RetrofitConfigurationService.getInstance(application).apiWebServices()
    private var mealMapper = MealMapper

    fun addNewMeal(meal: Meal){
        apiWebServices.addMeal(mealMapper.mapToMealDto(meal)!!).enqueue(object :Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if(response.isSuccessful){
                    _error.value = NetworkError.NO_ERROR_DETECTED
                    print(response.body())
                }else{
                    _error.value = NetworkError.REQUEST_ERROR
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                if(t is NoConnectivityException){
                    _error.value = NetworkError.NO_CONNECTION_ERROR
                }else{
                    _error.value = NetworkError.TECHNICAL_ERROR
                    print(t)
                }
            }
        })
    }
}