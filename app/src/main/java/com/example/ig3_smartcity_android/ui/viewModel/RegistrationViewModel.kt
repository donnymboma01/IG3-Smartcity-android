package com.example.ig3_smartcity_android.ui.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.ig3_smartcity_android.model.NetworkError
import com.example.ig3_smartcity_android.model.User
import com.example.ig3_smartcity_android.repositories.configuration.RetrofitConfigurationService
import com.example.ig3_smartcity_android.repositories.dto.UserDTO
import com.example.ig3_smartcity_android.services.mappers.MealMapper


class RegistrationViewModel(application: Application) : AndroidViewModel(application)  {

    private val _error :MutableLiveData<NetworkError> = MutableLiveData()
    val error :LiveData<NetworkError> = _error


    private var apiWebServices = RetrofitConfigurationService.getInstance(application).apiWebServices()
    private var userMapper = MealMapper

    fun registerUser(user: User){

    }
}