package com.example.ig3_smartcity_android.services.mappers

import com.example.ig3_smartcity_android.model.MealToReceive
import com.example.ig3_smartcity_android.dataAccess.dto.MealToReceiveDTO
import java.util.*

object MealMapper {
    fun mapToMeal(mealdto: List<MealToReceiveDTO>?): ArrayList<MealToReceive>? {
        if (mealdto == null) {
            return  null
        } else {
            val meals = ArrayList<MealToReceive>()
            for ((id, name, description,image,portion_number) in mealdto) {
                val meal =
                    MealToReceive(id, name, description,image,portion_number)
                meals.add(meal)
            }
            return meals
        }
    }
}