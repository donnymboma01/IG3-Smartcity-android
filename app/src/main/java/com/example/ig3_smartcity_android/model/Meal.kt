package com.example.ig3_smartcity_android.model

import java.util.*

data class Meal(val name:String,
                val description:String,
                val price :Float,
                val publication_date :Date,
                val isAvailable :Boolean,
                val user: User,
                val category: Category){

}