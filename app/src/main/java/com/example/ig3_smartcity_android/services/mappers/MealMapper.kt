package com.example.ig3_smartcity_android.services.mappers

import com.example.ig3_smartcity_android.model.Category
import com.example.ig3_smartcity_android.model.MealToReceive
import com.example.ig3_smartcity_android.model.User
import com.example.ig3_smartcity_android.repositories.dto.CategoryDTO
import com.example.ig3_smartcity_android.repositories.dto.MealToReceiveDTO
import com.example.ig3_smartcity_android.repositories.dto.UserDTO
import java.util.ArrayList

object MealMapper {
    fun mapToCategoryDto(category :Category):CategoryDTO{
        return CategoryDTO(category.name)
    }

   fun mapToUserDto(user: User):UserDTO{
       return UserDTO(user.firstname,
                    user.lastname,
                    user.phone_number,
                    user.username,
                    user.password,
                    user.province,
                    user.city,
                    user.street_and_number)
   }

    fun mapToUser(userDto :UserDTO):User{
        return User(userDto.firstname,userDto.lastname,userDto.phone_number,userDto.username,userDto.password,userDto.province,userDto.city,userDto.street_and_number)
    }

    fun mapToCategory(categoryDto :CategoryDTO):Category{
        return Category(categoryDto.name)
    }

    //On a besoin d'une liste des répas donc... List<Meal> et non simplement Meal.
    /*fun mapToMeal(mealDto :MealDTO):Meal{
        return Meal(mealDto.name,mealDto.description,mealDto.price,mealDto.publication_date,mealDto.isAvailable,
            mapToUser(mealDto.user), mapToCategory(mealDto.category))
    }*/

    /*fun mapToMeal(mealdto :List<MealDTO?>?) : List<Meal>?{
        if(mealdto == null){
            return null
        }else{
            val meals = ArrayList<Meal>()
            for(mealDTO in mealdto){
                val meal :Meal = Meal(mealDTO.name,mealDTO.description,mealDTO.price,mealDTO.publication_date,mealDTO.isAvailable,
                    mapToUser(mealDTO.user), mapToCategory(mealDTO.category))
                meals.add(meal)
            }
            return meals
        }
    }*/
    fun mapToMeal(mealdto: List<MealToReceiveDTO>?): ArrayList<MealToReceive>? {
        if (mealdto == null) {
            return  null
        } else {
            val meals = ArrayList<MealToReceive>()
            for ((name, description,image,portion_number) in mealdto) {
                val meal =
                    MealToReceive(name, description,image,portion_number)
                meals.add(meal)
            }
            return meals
        }
    }
}