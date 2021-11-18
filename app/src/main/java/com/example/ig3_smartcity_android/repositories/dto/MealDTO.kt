package com.example.ig3_smartcity_android.repositories.dto

import java.util.*

data class MealDTO(val name:String,val description:String,val price:Float,val publication_date:Date,val isAvailable:Boolean,val user: UserDTO,val category:CategoryDTO){}
