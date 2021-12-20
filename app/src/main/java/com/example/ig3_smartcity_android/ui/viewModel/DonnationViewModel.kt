package com.example.ig3_smartcity_android.ui.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.ig3_smartcity_android.model.Meal
import com.example.ig3_smartcity_android.model.NetworkError
import com.example.ig3_smartcity_android.model.Token
import com.example.ig3_smartcity_android.repositories.configuration.RetrofitConfigurationService
import com.example.ig3_smartcity_android.services.mappers.MealMapper
import com.example.ig3_smartcity_android.services.mappers.TokenMapper
import com.example.ig3_smartcity_android.utils.errors.NoConnectivityException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DonnationViewModel(application: Application) :AndroidViewModel(application) {

    private val _error : MutableLiveData<NetworkError> = MutableLiveData()
    val error :LiveData<NetworkError> = _error
    private val _jwt : MutableLiveData<Token> = MutableLiveData()
    val jwt : LiveData<Token> = _jwt;
    private var tokenMapper = TokenMapper

    private var apiWebServices = RetrofitConfigurationService.getInstance(application).apiWebServices()
    private var mealMapper = MealMapper

    fun addNewMeal(meal: Meal, token: Token){
        apiWebServices.addMeal(mealMapper.mapToMealDto(meal)!!,"Bearer "+token.token).enqueue(object :Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if(response.isSuccessful){
                    _error.value = NetworkError.NO_ERROR_DETECTED
                    _jwt.value = tokenMapper.mapToToken(response.body()!!)
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