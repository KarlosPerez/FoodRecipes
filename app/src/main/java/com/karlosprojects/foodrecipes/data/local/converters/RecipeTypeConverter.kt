package com.karlosprojects.foodrecipes.data.local.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.karlosprojects.foodrecipes.domain.model.Recipes

@ProvidedTypeConverter
class RecipeTypeConverter {

    var gson = Gson()

    @TypeConverter
    fun stringToRecipe(data: String?): Recipes? {
        val listType = object : TypeToken<Recipes>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun recipeToString(recipe: Recipes?): String? {
        return gson.toJson(recipe)
    }

}