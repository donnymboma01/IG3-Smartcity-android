package com.example.ig3_smartcity_android.services.mappers

import com.example.ig3_smartcity_android.model.Category
import com.example.ig3_smartcity_android.model.Meal
import com.example.ig3_smartcity_android.model.User
import com.example.ig3_smartcity_android.repositories.dto.CategoryDTO
import com.example.ig3_smartcity_android.repositories.dto.MealDTO
import com.example.ig3_smartcity_android.repositories.dto.UserDTO

object MealMapper {

    fun mapToMealDto(meal: Meal):MealDTO?{
        return MealDTO(meal.name,meal.description,meal.price,meal.publication_date,meal.isAvailable,
            mapToUserDto(meal.user), mapToCategoryDto(meal.category))
    }

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

    fun mapToMeal(mealDto :MealDTO):Meal{
        return Meal(mealDto.name,mealDto.description,mealDto.price,mealDto.publication_date,mealDto.isAvailable,
            mapToUser(mealDto.user), mapToCategory(mealDto.category))
    }
}