package com.karlosprojects.foodrecipes.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.karlosprojects.foodrecipes.core.utils.Constants.RECIPES_TABLE
import com.karlosprojects.foodrecipes.domain.model.Recipes

@Entity(tableName = RECIPES_TABLE)
data class RecipeEntity(
    var recipe: Recipes,
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0
) {
}