package com.example.ig3_smartcity_android.ui.viewModel

import android.app.Application
import androidx.annotation.NonNull
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.ig3_smartcity_android.dataAccess.configuration.RetrofitConfigurationService
import com.example.ig3_smartcity_android.dataAccess.dto.MealToReceiveDTO
import com.example.ig3_smartcity_android.model.MealToReceive
import com.example.ig3_smartcity_android.model.NetworkError
import com.example.ig3_smartcity_android.services.mappers.MealMapper
import com.example.ig3_smartcity_android.utils.errors.NoConnectivityException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealViewModel(application: Application) :AndroidViewModel(application) {
    private val _meal = MutableLiveData<List<MealToReceive>>()
    val mealToReceive : LiveData<List<MealToReceive>> = _meal

    private val _error  = MutableLiveData<NetworkError?>()
    val error : LiveData<NetworkError?> = _error


    private var mealWebServices = RetrofitConfigurationService.getInstance(application).apiWebServices()
    private var mealMapper = MealMapper

    fun getAllMeals(token : String){
        mealWebServices.getAllMeals("Bearer $token").enqueue(object : Callback<List<MealToReceiveDTO>> {
            override fun onResponse(@NonNull call:Call<List<MealToReceiveDTO>>, @NonNull response: Response<List<MealToReceiveDTO>>){
                if(response.isSuccessful){
                    _meal.value = mealMapper.mapToMeal(response.body())
                    _error.value = NetworkError.NO_ERROR_DETECTED
                }else{
                    _error.value = NetworkError.REQUEST_ERROR
                }
            }

            override fun onFailure(@NonNull call: Call<List<MealToReceiveDTO>>, @NonNull t: Throwable) {
                if(t is NoConnectivityException){
                    _error.value = NetworkError.NO_CONNECTION_ERROR
                }else{
                    println(t)
                    _error.value = NetworkError.TECHNICAL_ERROR
                }
            }
        })
    }
}